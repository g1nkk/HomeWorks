using System;
using System.Windows;
namespace MostDifficultDZ{public partial class MainWindow : Window { string[] texts = new string[] { "hello", "hewwo", "hi", "zdarova", "?" }; public MainWindow(){InitializeComponent();}private void Button_Click(object sender, RoutedEventArgs e){while(true){MessageBox.Show(texts[new Random().Next(texts.Length)]);}}}}
