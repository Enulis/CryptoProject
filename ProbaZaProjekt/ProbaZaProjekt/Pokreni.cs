using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;


namespace ProbaZaProjekt
{
    public class Pokreni
    {/*
        public string Vrati(string a) 
        {
            return a;
        }*/
       
        public static string Pokazi(string tekst, int ver)
        {
            
            const byte[] rasprsena = null;
            //Console.WriteLine("Unesite tekst kojeg zelite sazeti: ");
            //string tekst = Console.ReadLine();
            //Console.WriteLine("Koju verziju zelite? 256 ili 512?");
            int verzija = Convert.ToInt32(ver);

            int duljina = tekst.Length;
            byte[] medjuspremnik = new byte[duljina];
            for (int i = 0; i < duljina; i++)
            {
                medjuspremnik[i] = (byte)(tekst[i]);
                //Console.WriteLine(medjuspremnik[i]);
            }

            //Console.ReadLine();

            Hash tablica = new Hash();
            //Console.WriteLine("Koji");
            string result = tablica.Izracunaj(medjuspremnik, duljina * 8, rasprsena, verzija);
            return result;
            //Vrati(); 
        }
    }
}