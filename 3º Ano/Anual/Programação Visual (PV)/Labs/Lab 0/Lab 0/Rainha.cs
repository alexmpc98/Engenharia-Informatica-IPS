using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Rainha: Peca
    {
        public override void Deslocar(int dx, int dy)
        {

        }
        public Rainha(Posicao position, Cor newColor) : base(position, newColor, "Rainha")
        {

        }
        public Rainha() : base()
        {

        }
        public override string ToString()
        {
            return "D " + base.ToString();
        }
    }
}
