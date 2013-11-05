using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace ConsoleApplication2
{
    public class Hash
    {

        public static class Konstante 
        {
            public static int verzija;
            public static byte[] izlaz;
            public static long velicinaIzlaza;
            public static int Blok = (int)(velicinaIzlaza/verzija);
            public static byte[] lanac = new byte[(Konstante.verzija-1)/8+1];
            public static byte[] rasprsenaTablica;
        }

        public static void Izracunaj(byte[] tablica, long velicina, byte[] hash, int velHasha) 
        {
            if (velHasha <= 256)
                Konstante.verzija = 512;
            else
                Konstante.verzija = 1024;


            Konstante.izlaz = Padding( Konstante.verzija, tablica, velicina);

            Konstante.lanac = Lanac (Konstante.lanac, Konstante.verzija, velHasha);
            for (int i = 0; i < Konstante.Blok; i++) 
                Konstante.lanac = Kompresija.Izracunaj(Konstante.izlaz, Konstante.lanac, Konstante.verzija);


            Izlaz.Izracunaj(Konstante.lanac, Konstante.verzija, Konstante.rasprsenaTablica, velHasha);
        }

        public static byte[] Padding(int v, byte[] tablica, long len) 
        {
            long b = (Konstante.velicinaIzlaza-1)/8;
            int w = (int)(v - (len + 65)%v);
            
            Konstante.velicinaIzlaza = len + 65 + w;
            Konstante.izlaz = new byte[b + 1];

            for (int i = 0; i < (b * sizeof(byte)); i++)
                Konstante.izlaz[i] = 0;

            long bit = len+1;
            long a = (bit - 1) / 8;

            for (int i = 0; i < (bit - 2) / 8; i++) 
                Konstante.izlaz[ i ] = tablica[ i ];

            Konstante.izlaz[ a ]|=(byte)( 1 << (7 - ((int)bit - 1)) % 8);

            bit += w + 1;
            int MATEA = (int)(Konstante.velicinaIzlaza/v);

            for (int i = 0; i < 64; i++) 
            {
                byte dulj = (byte)(MATEA>>(63-i));
                byte shift = (byte)(1 & dulj);
                byte ponovno = (byte)(7-((bit - 1)%8));
                Konstante.izlaz[ a ] |= (byte)(ponovno<<shift);
            }

            return Konstante.izlaz;
        }
        
        public static void Ispisi(byte[] tablica, int tip)
        {
            for(int i = 0; i<(tip/8); i++)
                Console.Write(tablica[i]);
            Console.Write("\n");
        }

        public static byte[] Lanac(byte[] LanacFja, int verzija, int vel) 
        {
            for (int i = 0; i < (((verzija - 1) / 8 + 1) * sizeof(byte)); i++)
                LanacFja[ i ] = 0;
            for (int i = 0; i < 4; i++) 
            {
                LanacFja[(verzija - 1) / 8 + 1] = (byte)((vel >> (i * 8)) & 0xff); 
            }
                return LanacFja;
        }
    }
}
