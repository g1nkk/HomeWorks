namespace timerDZ
{
    public partial class Form1 : Form
    {
        private DateTime StartTime;
        private int clickCount;


        private int recordClickCount;

        //background color
        int currentColorIndex = 0;
        int red = 1;
        int green = 1;
        int blue = 1;
        bool add = true;

        public Form1()
        {
            InitializeComponent();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            // новый год
            StartTime = DateTime.Now;
            DateTime newYearDate = new DateTime(DateTime.Now.Year, 12, 31);

            // таймер для нового года
            var timer = new System.Windows.Forms.Timer();
            timer.Interval = 1000;
            timer.Tick += (s, args) => UpdateCountdown(newYearDate);
            timer.Start();

            // таймер заголовка
            var headerTimer = new System.Windows.Forms.Timer();
            headerTimer.Interval = 100;
            headerTimer.Tick += (s, args) => UpdateHeaderTimer();
            headerTimer.Start();

            // таймер цвета фона
            var colorTimer = new System.Windows.Forms.Timer();
            colorTimer.Interval = 1;
            colorTimer.Tick += (s, args) => UpdateBackgroundColor();
            colorTimer.Start();

            // таймер кликрв
            var clickTimer = new System.Windows.Forms.Timer();
            clickTimer.Interval = 20000; //20 сек отсчет
            clickTimer.Tick += (s, args) => ShowClickResult();
            clickTimer.Start();
        }

        private void UpdateCountdown(DateTime targetDateTime)
        {
            TimeSpan remainingTime = targetDateTime - DateTime.Now;

            if (remainingTime.TotalMilliseconds <= 0)
            {
                countdownLabel.Text = "с новым годом";
                return;
            }

            int days = remainingTime.Days;
            int hours = remainingTime.Hours;
            int minutes = remainingTime.Minutes;
            int seconds = remainingTime.Seconds;

            countdownLabel.Text = $"{days} дней, {hours:00}:{minutes:00}:{seconds:00}";
        }

        private void UpdateHeaderTimer()
        {
            int elapsedMilliseconds = (int)(DateTime.Now - StartTime).TotalMilliseconds;
            Text = elapsedMilliseconds.ToString();
        }

        private void UpdateBackgroundColor()
        {
            switch (currentColorIndex)
            {
                case 0:
                    if (add)
                        red++;
                    else
                        red--;

                    if (red == 255 || red == 0) currentColorIndex = 1;
                    break;
                case 1:
                    if (add)
                        green++;
                    else
                        green--;

                    if (green == 255 || green == 0) currentColorIndex = 2;
                    break;
                case 2:
                    if (add)
                        blue++;
                    else
                        blue--;

                    if (blue == 255 || blue == 0)
                    {
                        currentColorIndex = 0;
                        reverse();
                    }
                    break;
            }

            BackColor = Color.FromArgb(red, green, blue);
        }

        void reverse()
        {
            if (add) add = false;
            else add = true;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            clickCount++;
            button1.Text = clickCount.ToString();
        }

        private void ShowClickResult()
        {
            // проверк достигнут ли новый рекорд
            if (clickCount > recordClickCount)
                recordClickCount = clickCount;

            // результаты
            MessageBox.Show($"Количество кликов: {clickCount}\nМаксимальный рекорд: {recordClickCount}");

            clickCount = 0;
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }
    }
}
