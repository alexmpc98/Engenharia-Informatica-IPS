using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Peao : Peca
    {
        public Peao(Posicao position, Cor newColor) : base(position, newColor, "Peao")
        {

        }
        public Peao() : base()
        {

        }
        public override void Deslocar(int dx, int dy)
        {
            Posicao Position = new Posicao(base.getPosition().getX(), dy);
            base.setPosition(Position);
        }

        public override string ToString()
        {
            return base.ToString();
        }
    }

}
