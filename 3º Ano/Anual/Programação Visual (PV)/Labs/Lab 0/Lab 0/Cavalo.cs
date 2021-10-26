using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Cavalo : Peca
    {
        public override void Deslocar(int dx, int dy)
        {
            
        }
        public Cavalo(Posicao position, Cor newColor) : base(position, newColor, "Cavalo")
        {

        }
        public Cavalo() : base()
        {

        }
        public override string ToString()
        {
            return "C " + base.ToString();
        }
    }
}
