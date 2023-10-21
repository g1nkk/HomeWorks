using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Net.Mime;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace NetworkProgram
{
    public partial class EmailWindow : Window
    {
        public EmailWindow()
        {
            InitializeComponent();
            StartSettingsTextBox();
        }

        private SmtpClient? GetSmtpClient()
        {
            string? host = App.GetConfiguration("smtp:host");
            if (host is null)
            {
                MessageBox.Show("Error getting host...");
                return null;
            }

            string? portString = App.GetConfiguration("smtp:port");
            if (portString is null)
            {
                MessageBox.Show("Error getting port...");
                return null;
            }

            int port;
            try
            {
                port = Convert.ToInt32(portString);
            }
            catch
            {
                MessageBox.Show("Error parsing port...");
                return null;
            }

            string? email = App.GetConfiguration("smtp:email");
            if (email is null)
            {
                MessageBox.Show("Error getting email...");
                return null;
            }

            string? password = App.GetConfiguration("smtp:password");
            if (password is null)
            {
                MessageBox.Show("Error getting password...");
                return null;
            }

            string? sslString = App.GetConfiguration("smtp:ssl");
            if (sslString is null)
            {
                MessageBox.Show("Error getting ssl...");
                return null;
            }

            bool ssl;
            try
            {
                ssl = Convert.ToBoolean(sslString);
            }
            catch
            {
                MessageBox.Show("Error parsing ssl...");
                return null;
            }

            if (!textBoxTo.Text.Contains("@"))
            {
                MessageBox.Show("Введите правильный email");
                return null;
            }

            return new(host, port)
            {
                EnableSsl = ssl,
                Credentials = new NetworkCredential(email, password)
            };
        }


        private void BtnSendButton1_Click(object sender, RoutedEventArgs e)
        {
            SmtpClient? smtpClient = GetSmtpClient();
            if (smtpClient is null)
            {
                return;
            }

            smtpClient.Send(
                App.GetConfiguration("smtp:email")!,
                textBoxTo.Text,
                textBoxSubject.Text,
                textBoxContent.Text
            );
            MessageBox.Show("Отправлено!");
        }


        private void BtnSendButton2_Click(object sender, RoutedEventArgs e)
        {
            SmtpClient? smtpClient = GetSmtpClient();
            if (smtpClient is null)
            {
                return;
            }

            // html разметка
            MailMessage mailMessage = new(
                App.GetConfiguration("smtp:email")!,
                textBoxTo.Text,
                textBoxSubject.Text,
                textBoxHtml.Text)
            {
                IsBodyHtml = true
            };

            // картинка
            ContentType pngType = new("image/png"); // указываем, какой тип ресурса отправляем
            mailMessage.Attachments.Add(new Attachment("python.png", pngType));

            // mp3
            ContentType mp3Type = new("audio/mpeg"); // указываем, какой тип ресурса отправляем
            mailMessage.Attachments.Add(new Attachment("jump.mp3", mp3Type));

            smtpClient.Send(mailMessage);
            MessageBox.Show("Отправлено!");
        }


        private void StartSettingsTextBox()
        {
            textBoxHtmlHW.Text =
                $"<h2>Добрый день!</h2> Ваш код подтверждения: <b style='color:tomato'>{App.GetRandomNumber(100000, 999999)}</b>";
        }

        private void BtnSendButtonHW_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                SmtpClient? smtpClient = GetSmtpClient();
                if (smtpClient is null)
                {
                    return;
                }

                MailMessage mailMessage = new(
                    App.GetConfiguration("smtp:email")!,
                    textBoxTo.Text,
                    textBoxSubject.Text,
                    textBoxHtmlHW.Text)
                {
                    IsBodyHtml = true
                };

                ContentType txtType = new("text/plain");
                mailMessage.Attachments.Add(new Attachment("privacy.txt", txtType));

                ContentType docType = new("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                mailMessage.Attachments.Add(new Attachment("privacy.docx", docType));

                smtpClient.Send(mailMessage);
                MessageBox.Show("Отправлено!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}