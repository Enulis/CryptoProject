using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace ProbaZaProjekt
{
    public class Izlaz
    {

        public static string Izracunaj(byte[] ulaz, int verzija, byte[] hash, int duljina)
        {
            RadSPermutacijama rad = new RadSPermutacijama();
            EkskluzivniILI XOR = new EkskluzivniILI();

            int a = (verzija - 1) / 8 + 1;
            hash = new byte[(duljina - 1) / 8 + 1];
            byte[] izlazIzP = new byte[a];
            byte[] izlazIzXOR = new byte[a];

            for (int i = 0; i < ((duljina - 1) / 8 + 1); i++)
                hash[i] = 0;

            izlazIzP = rad.PermutacijaP(ulaz, izlazIzP, verzija);
            izlazIzXOR = XOR.Racunaj(izlazIzP, ulaz, verzija);

            int bit = verzija - duljina;

            for (int i = 0; i < duljina; i++, bit++)
            {
                hash[i / 8] |= (byte)((1 & (izlazIzXOR[bit / 8] >> (bit % 8))) << (i % 8));
                Console.WriteLine(hash[i / 8]);
            }
            //Console.WriteLine("This is the length of hash table");
            //Console.WriteLine(hash.Length);
            /*
            for (int i = 0; i < hash.Length; i++)
            {
                Console.WriteLine(hash[i]);
            }
            Console.ReadLine();*/
            String poruka = PretvoriByteUString(hash);
            //Console.WriteLine(poruka);
            //Console.ReadLine();
            return poruka;
        }
        public static string PretvoriByteUString(byte[] ulazniNiz)
        {
            StringBuilder izlazniNiz = new StringBuilder(2 * ulazniNiz.Length);
            foreach (byte b in ulazniNiz)
            {
                string rijec = Convert.ToString(b, 16);

                if (b < 0x10)
                    izlazniNiz.Append("0").Append(rijec);
                else
                    izlazniNiz.Append(rijec);
            }
            string izlaz = izlazniNiz.ToString();
            return izlaz;
        }

        /*public static void Ispisi(byte[] poruka, int duljina)
        {
            for (int i = 0; i < duljina / 8; i++)
                System.Console.WriteLine(poruka[i]);
        }*/
    }
}
