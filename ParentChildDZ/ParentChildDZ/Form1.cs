namespace ParentChildDZ
{
    public partial class Form1 : Form
    {
        Form2 form2;

        public Form1()
        {
            InitializeComponent();
            form2 = new Form2(this);
            form2.Show();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            form2.textBox1.Text = textBox1.Text;
        }
    }
}