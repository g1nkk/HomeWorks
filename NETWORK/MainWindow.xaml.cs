using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
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

namespace NetworkProgram
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void ServerBtn_Click(object sender, RoutedEventArgs e)
        {
            new ServerWindow().Show();
        }

        private void ClientBtn_Click(object sender, RoutedEventArgs e)
        {
            new ClientWindow().Show();
        }

        private void GmailBtn_Click(object sender, RoutedEventArgs e)
        {
            Hide();
            new EmailWindow().ShowDialog();
            Show();
        }

        private void GmailConfirmBtn_Click(object sender, RoutedEventArgs e)
        {
            Hide();
            new AuthWindow().ShowDialog();
            Show();
        }
    }
}
