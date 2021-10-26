using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Bispo : Peca
    {
        public override void Deslocar(int dx, int dy)
        {

        }
        public Bispo(Posicao position, Cor newColor) : base(position, newColor, "Bispo")
        {

        }
        public Bispo() : base()
        {

        }
        public override string ToString()
        {
            return "B " + base.ToString();
        }
    }
}
