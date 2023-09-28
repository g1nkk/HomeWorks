using System;
using System.Collections.Generic;
using System.Diagnostics;
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
using System.Windows.Threading;

namespace threadTest
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private PerformanceCounter cpuCounter;
        private PerformanceCounter memoryCounter;
        private DispatcherTimer _timer;

        public MainWindow()
        {
            InitializeComponent();

            _timer = new DispatcherTimer();
            _timer.Interval = new TimeSpan(1000000);
            _timer.Tick += UpdateTaskManagerInfo;
            _timer.Start();

            cpuCounter = new PerformanceCounter("Processor", "% Processor Time", "_Total");
            memoryCounter = new PerformanceCounter("Memory", "Available MBytes");

            UpdateTaskManagerInfo(null, null);
        }


        private async void UpdateTaskManagerInfo(object? obj, EventArgs args)
        {
            await Task.Run(() =>
            {
                float cpuUsage = cpuCounter.NextValue();

                float memoryUsage = memoryCounter.NextValue();
                
                int threadCount = Process.GetCurrentProcess().Threads.Count;

                Dispatcher.Invoke(() =>
                {
                    cpuUsageLabel.Content = $"CPU Usage: {cpuUsage}%";
                    memoryUsageLabel.Content = $"Memory Usage: {memoryUsage} MB";
                    threadCountLabel.Content = $"Thread Count: {threadCount}";
                });
            });
        }
    }
}