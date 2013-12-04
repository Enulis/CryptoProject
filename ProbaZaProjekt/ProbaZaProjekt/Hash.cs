using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace ProbaZaProjekt
{
    public class Hash
    {

        public int VelBloka(long len, int v)
        {
            return (int)(len + 65 + (v - (len + 65) % v));
        }

        public string Izracunaj(byte[] tablica, long velicina, byte[] hash, int velHasha)
        {
            int verzija;
            int vel;
            byte[] izlaz = null;

            if (velHasha <= 256)
            {
                //Console.WriteLine("Koji");
                verzija = 512;
                vel = 63;
            }
            else
            {
                verzija = 1024;
                vel = 127;
            }
            byte[] lanac = new byte[vel + 1];

            izlaz = Padding(verzija, tablica, velicina, izlaz);

            lanac = Lanac(lanac, verzija, velHasha);


            int Blok = VelBloka(velicina, verzija);
            //Console.WriteLine("Velicina Bloka");
            //Console.WriteLine(Blok);


            for (int i = 0; i < Blok; i++)
            {
                lanac = Kompresija.Izracunaj(izlaz, lanac, verzija);
                //Console.WriteLine(i);

            }
            //Console.ReadLine();
            string a = Izlaz.Izracunaj(lanac, verzija, hash, velHasha);
            return a;
        }

        public byte[] Padding(int v, byte[] tablica, long len, byte[] izlaz)
        {
            long velicinaIzlaza;

            //Console.WriteLine("je");

            int w = (int)(v - (len + 65) % v);

            velicinaIzlaza = len + 65 + w;
            long b = (velicinaIzlaza - 1) / 8;
            izlaz = new byte[b + 1];

            //Console.WriteLine("tebi");
            for (int i = 0; i < (b * sizeof(byte)); i++)
                izlaz[i] = 0;

            long bit = len + 1;
            long a = (bit - 1) / 8;

            for (int i = 0; i < (bit - 2) / 8 + 1; i++)
            {
                izlaz[i] = tablica[i];
                //Console.WriteLine(izlaz[i]);
            }

            //Console.WriteLine("zasto");
            izlaz[a] |= (byte)(1 << (7 - ((int)bit - 1)) % 8);

            bit += w + 1;
            int MATEA = 1;
            //Console.WriteLine("ne");
            //Console.Write("Zasto NE RADIS KONJU JEDAN I KOJI JE TEBI JESI NORMALAN???");
            for (int i = 0; i < 64; i++, bit++)
            {
                byte dulj = (byte)(MATEA >> (63 - i));
                // Console.WriteLine(dulj);// 0
                byte shift = (byte)(1 & dulj);
                // Console.WriteLine(shift); // 7, 6, 5, 4, 3, 2, 1, 0 
                byte ponovno = (byte)(7 - ((bit - 1) % 8));
                // Console.WriteLine(ponovno); // 7
                //Console.WriteLine(izlaz[a]);
                izlaz[a] |= (byte)(ponovno << shift);
            }
            /* for (int i = 0; i < izlaz.Length; i++)
             {
                 Console.WriteLine(izlaz[i]);
             }*/
            //ispiše da su sve 0
            return izlaz;
        }
        /*
        public void Ispisi(byte[] tablica, int tip)
        {
            for (int i = 0; i < (tip / 8); i++)
                Console.Write(tablica[i]);
            Console.Write("\n");
        }
        */
        public byte[] Lanac(byte[] LanacFja, int verzija, int vel)
        {
            int velicina;
            if (verzija == 256)
                velicina = 63;
            else
                velicina = 127;

            LanacFja = new byte[velicina + 1];

            for (int i = 0; i < ((velicina + 1) * sizeof(byte)); i++)
                LanacFja[i] = 0;

            for (int i = 0; i < 4; i++)
            {
                LanacFja[velicina - i] = (byte)((vel >> (i * 8)) & 0xff);
                //Console.WriteLine();

            }
            //Console.WriteLine("IsPIS");
            //Console.WriteLine(LanacFja.Length);
            //Console.WriteLine("IsPIS");
            /*for (int i = 0; i < velicina + 1; i++) 
            {
                Console.WriteLine(LanacFja[i]);
            }*/
            return LanacFja;
        }
    }
}
