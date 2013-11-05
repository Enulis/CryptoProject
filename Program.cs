using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace ConsoleApplication2
{
    public static class Program
    {
        static void Main(string[] args)
        {
           if (args.Length < 3 ){
               Console.WriteLine("Niste zadali sve potrebne argumente!"); //Provjeriti jesu li zadani svi argumenti
           }

           int verzija = Convert.ToInt32(args[2]);
           byte[] hash = new byte[20000]; 
           FileStream file = new FileStream(args[1],FileMode.Open,FileAccess.Read);

           try
           {
               long duljina = file.Length;
               byte[] medjuspremnik = new byte[duljina];
               int bytesRead = file.Read(medjuspremnik, 0, medjuspremnik.Length);

               Hash.Izracunaj(medjuspremnik, duljina * 8, hash, verzija);
               Hash.Ispisi(hash,verzija);
           }
           finally
           {
               file.Close();
           }

        }
    }
}

	