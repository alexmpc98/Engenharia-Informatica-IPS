using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Torre : Peca
    {
        public Torre(Posicao position, Cor color) : base(position, color,"Torre")
        {

        }
        public Torre() : base()
        {

        }
        public override void Deslocar(int dx, int dy)
        {
            Posicao Position;
            if(dx != 0 && dy != 0)
            {
                Position = new Posicao(base.getPosition().getX(), base.getPosition().getY());
                base.setPosition(Position);
            }
            else if(dx != 0 && dy == 0)
            {
                Char newChar = (Char)(Convert.ToUInt16(base.getPosition().getX()) + dx);
                Position = new Posicao(newChar, base.getPosition().getY());
                base.setPosition(Position);
            }
            else if(dx == 0 && dy != 0)
            {
                Position = new Posicao(base.getPosition().getX(), dy);
                base.setPosition(Position);
            }
            
        }
        public override string ToString()
        {
            return "T " + base.ToString();
        }
    }
}
