using System.Globalization;
using System.Resources;
using System.Threading;
using System.Windows.Forms;

namespace LanguageDZ
{
    public partial class Form1 : Form
    {
        private ResourceManager resourceManager;
        public Form1()
        {
            InitializeComponent();

            resourceManager = new ResourceManager("LanguageDZ.Resources", typeof(Form1).Assembly);
        }

        private void miamiButton_Click(object sender, EventArgs e)
        {
            ChangeLanguage("ResourceEN");
        }

        private void parisButton_Click(object sender, EventArgs e)
        {
            ChangeLanguage("ResourceFR");
        }

        private void berlinButton_Click(object sender, EventArgs e)
        {
            ChangeLanguage("ResourceGR");
        }

        private void ChangeLanguage(string cultureCode)
        {
            Thread.CurrentThread.CurrentUICulture = new CultureInfo(cultureCode);

            background = (PictureBox)resourceManager.GetObject("Background");
            mainLabel.Text = resourceManager.GetString("Text");
        }
    }
}