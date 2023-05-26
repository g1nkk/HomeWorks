namespace timerDZ
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            countdownLabel = new Label();
            button1 = new Button();
            label1 = new Label();
            label2 = new Label();
            SuspendLayout();
            // 
            // countdownLabel
            // 
            countdownLabel.AutoSize = true;
            countdownLabel.Font = new Font("Segoe UI", 13F, FontStyle.Bold, GraphicsUnit.Point);
            countdownLabel.Location = new Point(390, 108);
            countdownLabel.Name = "countdownLabel";
            countdownLabel.Size = new Size(153, 25);
            countdownLabel.TabIndex = 0;
            countdownLabel.Text = "countdownLabel";
            // 
            // button1
            // 
            button1.Location = new Point(251, 53);
            button1.Name = "button1";
            button1.Size = new Size(75, 36);
            button1.TabIndex = 1;
            button1.Text = "0";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Segoe UI", 13F, FontStyle.Bold, GraphicsUnit.Point);
            label1.Location = new Point(12, 108);
            label1.Name = "label1";
            label1.Size = new Size(353, 25);
            label1.TabIndex = 3;
            label1.Text = "РЕБЯТА ДО НОВОГО ГОДА ОСТАЛОСЬ";
            label1.Click += label1_Click;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(158, 23);
            label2.Name = "label2";
            label2.Size = new Size(276, 15);
            label2.TabIndex = 4;
            label2.Text = "У тебя есть 20 секунд чтобы побить свой рекорд!";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(592, 142);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(button1);
            Controls.Add(countdownLabel);
            Name = "Form1";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Form1";
            Load += MainForm_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label countdownLabel;
        private Button button1;
        private Label label1;
        private Label label2;
    }
}