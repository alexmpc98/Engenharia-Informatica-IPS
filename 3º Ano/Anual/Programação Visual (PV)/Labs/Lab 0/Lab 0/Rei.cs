using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Rei : Peca
    {
        public override void Deslocar(int dx, int dy)
        {

        }
        public Rei(Posicao position, Cor newColor) : base(position, newColor, "Rei")
        {

        }
        public Rei() : base()
        {

        }
        public override string ToString()
        {
            return "R " + base.ToString();
        }
    }
}
