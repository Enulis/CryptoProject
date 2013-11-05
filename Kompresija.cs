using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication2
{
    class Kompresija
    {
        public static class Konstante 
        {
            public static byte[] izlaz;
            public static byte[] ulazP;
            public static byte[] izlazP;
            public static byte[] izlazQ;
            public static byte[] izlazXOR;
        }

        public static byte[] Izracunaj(byte[] blok, byte[] lanac, int verzija) 
        {
            int a = (verzija - 1) / 8 + 1;
            Konstante.ulazP = new byte[ a ];
            Konstante.izlazP = new byte[ a ];
            Konstante.izlazQ = new byte[ a ];

            Konstante.ulazP = EkskluzivniILI.Racunaj(blok, lanac);
            Konstante.izlazP = RadSPermutacijama.PermutacijaP(Konstante.ulazP, Konstante.izlazP, verzija);
            Konstante.izlazQ = RadSPermutacijama.PermutacijaQ(blok, Konstante.izlazQ, verzija);
            Konstante.izlaz = EkskluzivniILI.Racunaj(Konstante.izlazP, lanac);
            Konstante.izlaz = EkskluzivniILI.Racunaj(Konstante.izlaz, Konstante.izlazQ);

            return Konstante.izlaz;
        }

        public static void Ispisi(byte[] poruka, int len) 
        {
            for (int i = 0; i < (len / 8); i++)
                System.Console.WriteLine(poruka[i]);
        }
    }
}
