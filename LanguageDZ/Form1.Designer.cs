namespace LanguageDZ
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
            background = new PictureBox();
            miamiButton = new Button();
            mainLabel = new Label();
            parisButton = new Button();
            berlinButton = new Button();
            ((System.ComponentModel.ISupportInitialize)background).BeginInit();
            SuspendLayout();
            // 
            // background
            // 
            background.Image = ResourceEN.Background;
            background.Location = new Point(-63, 0);
            background.Name = "background";
            background.Size = new Size(802, 389);
            background.TabIndex = 0;
            background.TabStop = false;
            // 
            // miamiButton
            // 
            miamiButton.Location = new Point(340, 156);
            miamiButton.Name = "miamiButton";
            miamiButton.Size = new Size(100, 40);
            miamiButton.TabIndex = 1;
            miamiButton.Text = "miami";
            miamiButton.UseVisualStyleBackColor = true;
            miamiButton.Click += miamiButton_Click;
            // 
            // mainLabel
            // 
            mainLabel.AutoSize = true;
            mainLabel.BackColor = Color.Transparent;
            mainLabel.Font = new Font("Microsoft Sans Serif", 15F, FontStyle.Regular, GraphicsUnit.Point);
            mainLabel.Location = new Point(250, 71);
            mainLabel.Name = "mainLabel";
            mainLabel.Size = new Size(260, 25);
            mainLabel.TabIndex = 2;
            mainLabel.Text = "where you want to be today?";
            // 
            // parisButton
            // 
            parisButton.Location = new Point(178, 156);
            parisButton.Name = "parisButton";
            parisButton.Size = new Size(100, 40);
            parisButton.TabIndex = 3;
            parisButton.Text = "paris";
            parisButton.UseVisualStyleBackColor = true;
            parisButton.Click += parisButton_Click;
            // 
            // berlinButton
            // 
            berlinButton.Location = new Point(503, 156);
            berlinButton.Name = "berlinButton";
            berlinButton.Size = new Size(100, 40);
            berlinButton.TabIndex = 4;
            berlinButton.Text = "berlin";
            berlinButton.UseVisualStyleBackColor = true;
            berlinButton.Click += berlinButton_Click;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(738, 388);
            Controls.Add(berlinButton);
            Controls.Add(parisButton);
            Controls.Add(mainLabel);
            Controls.Add(miamiButton);
            Controls.Add(background);
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)background).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private PictureBox background;
        private Button miamiButton;
        private Label mainLabel;
        private Button parisButton;
        private Button berlinButton;
    }
}