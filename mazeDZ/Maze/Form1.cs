using System.Drawing;
using System.Windows.Forms;

namespace Maze
{
    public partial class Form1 : Form
    {
        private Labirint l;

        public Form1()
        {
            InitializeComponent();
            Options();
            StartGame();
        }

        public void Options()
        {
            Text = "Maze";

            BackColor = Color.FromArgb(255, 92, 118, 137);

            int sizeX = 30;
            int sizeY = 30;

            Width = sizeX * 16 + 16;
            Height = sizeY * 16 + 40;
            StartPosition = FormStartPosition.CenterScreen;
        }

        public void StartGame() {
            l = new Labirint(this, 30, 30);
            l.Show();
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            l.KeyPressed(e.KeyCode);
            l.UpdatePanel();
        }
    }
}
