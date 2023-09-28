using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace threadTest
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private CancellationTokenSource cancellationTokenSource;
        private int threadCount = 3; 

        public MainWindow()
        {
            InitializeComponent();
        }

        private async void StartButton_Click(object sender, RoutedEventArgs e)
        {
            cancellationTokenSource = new CancellationTokenSource();
            await StartThreadsAsync();
        }

        private void StopButton_Click(object sender, RoutedEventArgs e)
        {
            cancellationTokenSource?.Cancel();
        }

        private async Task StartThreadsAsync()
        {
            for (int i = 1; i <= threadCount; i++)
            {
                int threadNumber = i;
                Task.Run(() => RunThread(threadNumber, cancellationTokenSource.Token));
            }
        }

        private void RunThread(int threadNumber, CancellationToken cancellationToken)
        {
            for (int i = 1; i <= 100; i++)
            {
                if (cancellationToken.IsCancellationRequested)
                {
                    return;
                }
                
                Thread.Sleep(100);

                Application.Current.Dispatcher.Invoke(() =>
                {
                    UpdateProgressBar(threadNumber, i);
                });

                Thread.Sleep(TimeSpan.FromMilliseconds(50));
            }
        }

        private void UpdateProgressBar(int threadNumber, int value)
        {
            ProgressBar progressBar = null;
            switch (threadNumber)
            {
                case 1:
                    progressBar = progressBar1;
                    break;
                case 2:
                    progressBar = progressBar2;
                    break;
                case 3:
                    progressBar = progressBar3;
                    break;
            }

            progressBar.Value = value;
        }
    }
}