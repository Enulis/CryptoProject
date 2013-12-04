using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ProbaZaProjekt
{
    public partial class Form1 : Form
    {
        string a;
        string b;
        int c;
        Pokreni NOVI = new Pokreni();
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (rbtn1.Checked)
            {
                a = Pokreni.Pokazi(txt.Text, 256);
                textBox1.Text = a;
            }
            else 
            {
                a = Pokreni.Pokazi(txt.Text, 512);
                textBox1.Text = a;
            }
            //Pokreni pokreni = new Pokreni();
            //a = pokreni.Pokazi(txt.Text, 256);
            //pokreni.Show(txt.Text, combo.Text);
            //pokreni.Show();
        }


        private void textBox1_TextChanged_1(object sender, EventArgs e)
        {
            string pomocni = a;
            textBox1.Text = a;
            textBox1.Show();
        }

        private void rbtn1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void Rbtn2_CheckedChanged(object sender, EventArgs e)
        {

        }

      
    }
}
