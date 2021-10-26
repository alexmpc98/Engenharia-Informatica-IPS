using System;
using System.Collections.Generic;
using System.Text;

namespace Lab__1
{
    abstract class Figura : IMover
    {
        public float x { get; set; }
        public float y { get; set; }
        public Figura(float x,float y)
        {
            this.x = x;
            this.y = y;
        }
        public abstract float GetArea();
        void IMover.Deslocar(int dx, int dy)
        {
            this.x = dx;
            this.y = dy;
        }
        public override string ToString()
        {
            return " X -> " + this.x + " Y -> " + this.y;
        }
    }
}
