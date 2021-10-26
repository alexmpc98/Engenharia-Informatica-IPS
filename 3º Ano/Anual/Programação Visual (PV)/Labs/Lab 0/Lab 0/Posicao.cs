using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Posicao
    {
        char x;
        int y;
        public Posicao(char x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public Posicao()
        {
            this.x = 'a';
            this.y = 1;
        }
        public char getX()
        {
            return this.x;
        }
        public int getY()
        {
            return this.y;
        }
        public override string ToString()
        {
            return this.x + this.y.ToString();
        }
    }
}
