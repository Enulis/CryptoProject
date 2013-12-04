using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProbaZaProjekt
{
    class Kompresija
    {

        public static class Konstante
        {
            public static byte[] izlaz;
            public static byte[] ulazP;
            public static byte[] izlazP;
            public static byte[] izlazQ;
        }


        public static byte[] Izracunaj(byte[] blok, byte[] lanac, int verzija)
        {
            EkskluzivniILI XOR = new EkskluzivniILI();
            RadSPermutacijama rad = new RadSPermutacijama();
            int a = (verzija - 1) / 8 + 1;



            Konstante.ulazP = new byte[a];
            Konstante.izlazP = new byte[a];
            Konstante.izlazQ = new byte[a];

            Konstante.ulazP = XOR.Racunaj(blok, lanac, verzija);
            Konstante.izlazP = rad.PermutacijaP(Konstante.ulazP, Konstante.izlazP, verzija);
            Konstante.izlazQ = rad.PermutacijaQ(blok, Konstante.izlazQ, verzija);
            Konstante.izlaz = XOR.Racunaj(Konstante.izlazP, lanac, verzija);
            Konstante.izlaz = XOR.Racunaj(Konstante.izlaz, Konstante.izlazQ, verzija);

            return Konstante.izlaz;
        }
/*
        public static void Ispisi(byte[] poruka, int len)
        {
            for (int i = 0; i < (len / 8); i++)
                System.Console.WriteLine(poruka[i]);
        }*/
    }
}
