using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ParentChildDZ
{
    public partial class Form2 : Form
    {
        Form1 parent;

        public Form2(Form1 parentForm)
        {
            InitializeComponent();

            parentForm.TextChanged += textBox1_TextChanged;
            parent = parentForm;
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            parent.textBox1.Text = textBox1.Text;
        }
    }
}
