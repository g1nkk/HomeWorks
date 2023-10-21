using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.Json;
using System.Threading;
using System.Windows;
using System.Windows.Media;

namespace NetworkProgram;

public partial class ServerWindow : Window
{
    private bool isStartServer;
    private Socket? listenSocket; // "слушающий" сокет - ожидает запросы
    private IPEndPoint? endPoint; // точка(endPoint), которую "слушает" сокет, на эту точку приходят запросы
    private readonly LinkedList<ChatMessage> messages; // коллекция сообщений всех пользователей

    public ServerWindow()
    {
        InitializeComponent();
        messages = new LinkedList<ChatMessage>();
        CheckUIStatusState();
    }

    private void Window_Closing(object sender, CancelEventArgs e)
    {
        listenSocket?.Close(); // останавливаем сервер при закрытии окна
    }


    private void SwitchServerBtn_Click(object sender, RoutedEventArgs e)
    {
        if (listenSocket is null) // включаем
        {
            try
            {
                var ip = IPAddress.Parse(textBoxHost.Text);
                var port = Convert.ToInt32(textBoxPort.Text);

                endPoint = new IPEndPoint(ip, port);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

            listenSocket = new Socket(
                AddressFamily.InterNetwork, // IPv4
                SocketType.Stream, // двусторонний
                ProtocolType.Tcp 
            );

            // слушаем!
            new Thread(StartServer).Start();
        }
        else // выключаем
        {
            listenSocket.Close(); // создаёи конфликт, закрываем рабочий сокет
        }

        isStartServer = !isStartServer;
        CheckUIStatusState();
    }

    private void StartServer()
    {
        if (listenSocket is null || endPoint is null)
        {
            MessageBox.Show("Попытка запуска без инициализации данных!");
            return;
        }

        try
        {
            listenSocket.Bind(endPoint); // связываем сокет к endpointer, если endpoint(порт) занят, то будет исключение
            listenSocket.Listen(); // 10 запросов - максимальная очередь
            Dispatcher.Invoke(() => serverLog.Text += "Сервер запущен\n");

            var buffer = new byte[1024]; // буффер приёма данных
            while (true) // бесконечный процесс слушания - постоянная работа сервера
            {
                // ожидание запроса, эта инструкция блокирует поток до прихода запроса
                var socket = listenSocket.Accept();

                // этот код выполняется когда сервер получил запрос
                MemoryStream memoryStream = new();
                do
                {
                    var n = socket.Receive(buffer);
                    memoryStream.Write(buffer, 0, n);
                } while (socket.Available > 0);

                var str = Encoding.UTF8.GetString(memoryStream.ToArray());

                ServerResponse serverResponse = new();
                ClientRequest? clientRequest = null;
                try
                {
                    clientRequest = JsonSerializer.Deserialize<ClientRequest>(str);
                }
                catch
                {
                }

                var needLog = true;
                if (clientRequest is null)
                {
                    str = "Error decoding JSON: " + str;
                    serverResponse.Status = "400 Bad request";
                }
                else
                {
                    if (clientRequest.Command.Equals("Message"))
                    {
                        clientRequest.ChatMessage.Moment = DateTime.Now;

                        messages.AddLast(clientRequest.ChatMessage);

                        str = clientRequest.ChatMessage.ToString();
                        serverResponse.Status = "200 OK";
                    }
                    else if (clientRequest.Command.Equals("Check"))
                    {
                        serverResponse.Status = "200 OK";
                        serverResponse.Messages = messages;
                        needLog = false;
                    }
                }

                if (needLog)
                    Dispatcher.Invoke(() => serverLog.Text += $"({clientRequest!.ChatMessage.Moment}) {str}\n");

                socket.Send(Encoding.UTF8.GetBytes(JsonSerializer.Serialize(serverResponse)));
                socket.Close();
            }
        }
        catch (Exception)
        {
            listenSocket = null;
            Dispatcher.Invoke(() => serverLog.Text += "Сервер остановлен\n");
        }
    }


    private void CheckUIStatusState()
    {
        CheckStateServerButton();
        CheckStateStatusLabel();
    }

    private void CheckStateServerButton()
    {
        if (isStartServer)
        {
            btnSwitchServer.Content = "Выключить";
            btnSwitchServer.Background = Brushes.Pink;
        }
        else
        {
            btnSwitchServer.Content = "Включить";
            btnSwitchServer.Background = Brushes.Green;
        }
    }

    private void CheckStateStatusLabel()
    {
        if (isStartServer)
        {
            statusLabel.Content = "Включено";
            statusLabel.Background = Brushes.Green;
        }
        else
        {
            statusLabel.Content = "Выключено";
            statusLabel.Background = Brushes.Pink;
        }
    }
}