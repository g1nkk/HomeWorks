using System;

namespace buttonLeaningDZ
{
    public partial class Form1 : Form
    {
        private List<Rectangle> rectangles;
        private bool isDrawing;

        Random random = new Random();
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_MouseEnter(object sender, EventArgs e)
        {
            MoveButtonToRandomPosition();
        }

        private void MoveButtonToRandomPosition()
        {
            int maxX = ClientSize.Width - button1.Width;
            int maxY = ClientSize.Height - button1.Height;
            int newX = random.Next(0, maxX);
            int newY = random.Next(0, maxY);
            button1.Location = new Point(newX, newY);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            isDrawing = !isDrawing;
            button2.Text = isDrawing ? "Stop Drawing" : "Start Drawing";
        }

        private void MainForm_MouseClick(object sender, MouseEventArgs e)
        {
            if (isDrawing)
            {
                Rectangle rectangle = new Rectangle(e.X, e.Y, 100, 50);
                rectangles.Add(rectangle);
                Refresh();
            }
        }

        private void MainForm_Paint(object sender, PaintEventArgs e)
        {
            foreach (Rectangle rectangle in rectangles)
            {
                e.Graphics.DrawRectangle(Pens.Red, rectangle);
            }
        }
    }
}