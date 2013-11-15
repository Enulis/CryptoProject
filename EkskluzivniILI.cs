using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace GROESTLImplementation
{
    class EkskluzivniILI//MORA SE ISPRAVITI POZIV FUNKCIJE JER SE TREBA KORISTITI DULJINA TJ. VERZIJA HASHA A NE DULJINA PRVOG ODNOSNO DRUGOG POLJA !!!
    {
        public byte[] Racunaj(byte[] prvi, byte[] drugi, int duljina)
        {
            //int duljina = prvi.Length;
            byte[] resultBuffer = new byte[duljina];
            for (int i = 0; i < ((duljina - 1)/8 + 1); i++)
                resultBuffer[i] = (byte)(prvi[i] ^ drugi[i]);
            return resultBuffer;
        }
    }
}
