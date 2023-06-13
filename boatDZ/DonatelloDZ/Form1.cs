namespace DonatelloDZ
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            ClientSize = new Size(400, 400);
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            var rand = new Random();

            // небо
            g.FillRectangle(Brushes.Black, 0, 0, 400, 230);

            for (int i = 0; i < 5; i++)
            {
                int add = rand.Next(400);

                Point[] star = { new Point(50+add, 30+add/2), new Point(60 + add, 25 + add/2), new Point(65 + add, 10 + add / 2),
                    new Point(70 + add, 25 + add / 2),new Point(80 + add, 30 + add / 2), new Point(70 + add, 35 + add / 2),
                    new Point(65 + add, 47 + add / 2), new Point(60 + add, 35 + add / 2) };

                g.FillPolygon(Brushes.White, star);

                Point[] star1 = { new Point(50-add, 30-add/2), new Point(60 - add, 25 - add / 2), new Point(65 - add, 10 - add / 2),
                    new Point(70 - add, 25 - add / 2),new Point(80 - add, 30 - add / 2), new Point(70 - add, 35 - add / 2),
                    new Point(65 - add, 47 - add / 2), new Point(60 - add, 35 - add / 2) };

                g.FillPolygon(Brushes.White, star1);
            }


            // вода
            g.FillRectangle(Brushes.Aquamarine, 0, 220, 400, 400);



            // тело лодки
            Point[] topPoints = { new Point(100, 200), new Point(150, 250), new Point(250, 250), new Point(300, 200) };
            g.FillPolygon(Brushes.DarkOrchid, topPoints);

            // окнa на теле
            g.FillRectangle(Brushes.AliceBlue, 150, 215, 20, 20);
            g.FillRectangle(Brushes.AliceBlue, 190, 215, 20, 20);
            g.FillRectangle(Brushes.AliceBlue, 230, 215, 20, 20);

            // Рисование мачты
            g.FillRectangle(Brushes.Brown, 190, 90, 10, 112);

            // Рисование паруса
            Point[] sailPoints = { new Point(200, 90), new Point(250, 150), new Point(200, 200) };
            g.FillPolygon(Brushes.White, sailPoints);



        }
    }
}