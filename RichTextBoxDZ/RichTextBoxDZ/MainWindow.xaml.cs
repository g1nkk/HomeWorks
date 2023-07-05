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

namespace RichTextBoxDZ
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void BoldButton_Click(object sender, RoutedEventArgs e)
        {
            ToggleFontWeight(FontWeights.Bold);
        }

        private void ItalicButton_Click(object sender, RoutedEventArgs e)
        {
            ToggleFontStyle(FontStyles.Italic);
        }

        private void UnderlineButton_Click(object sender, RoutedEventArgs e)
        {
            ToggleTextDecoration(TextDecorations.Underline);
        }

        private void ClearButton_Click(object sender, RoutedEventArgs e)
        {
            RichTextBox.Selection.ClearAllProperties();
        }

        private void Font15Button_Click(object sender, RoutedEventArgs e)
        {
            ChangeFontSize(15);
        }

        private void Font30Button_Click(object sender, RoutedEventArgs e)
        {
            ChangeFontSize(30);
        }

        private void RedColorButton_Click(object sender, RoutedEventArgs e)
        {
            ChangeFontColor(Colors.Red);
        }

        private void GreenColorButton_Click(object sender, RoutedEventArgs e)
        {
            ChangeFontColor(Colors.Green);
        }

        private void BlueColorButton_Click(object sender, RoutedEventArgs e)
        {
            ChangeFontColor(Colors.Blue);
        }

        private void ToggleFontWeight(FontWeight fontWeight)
        {
            if (RichTextBox.Selection.GetPropertyValue(TextElement.FontWeightProperty) is FontWeight currentFontWeight &&
                currentFontWeight == fontWeight)
            {

            }
            else
            {
                RichTextBox.Selection.ApplyPropertyValue(TextElement.FontWeightProperty, fontWeight);
            }
        }

        private void ToggleFontStyle(FontStyle fontStyle)
        {
            if (RichTextBox.Selection.GetPropertyValue(TextElement.FontStyleProperty) is FontStyle currentFontStyle &&
                currentFontStyle == fontStyle)
            {

            }
            else
            {
                RichTextBox.Selection.ApplyPropertyValue(TextElement.FontStyleProperty, fontStyle);
            }
        }

        private void ToggleTextDecoration(TextDecorationCollection textDecoration)
        {
            if (RichTextBox.Selection.GetPropertyValue(Inline.TextDecorationsProperty) is TextDecorationCollection currentTextDecoration &&
                currentTextDecoration.Equals(textDecoration))
            {

            }
            else
            {
                RichTextBox.Selection.ApplyPropertyValue(Inline.TextDecorationsProperty, textDecoration);
            }
        }

        private void ChangeFontSize(double fontSize)
        {
            RichTextBox.Selection.ApplyPropertyValue(TextElement.FontSizeProperty, fontSize);
        }

        private void ChangeFontColor(Color fontColor)
        {
            RichTextBox.Selection.ApplyPropertyValue(TextElement.ForegroundProperty, new SolidColorBrush(fontColor));
        }
    }
}
