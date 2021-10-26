using System;
using System.Collections.Generic;
using System.Text;

namespace Lab__1
{
    class Quadrado : Figura,IMover
    {
        public float lado { get; set; }
        public Quadrado(float x,float y, float lado) : base(x,y)
        {
            this.lado = lado;
        }
        public override float GetArea()
        {
            return lado * lado;
        }
        void IMover.Deslocar(int dx, int dy)
        {
            base.x = dx;
            base.y = dy;
        }

        public override string ToString()
        {
            return "(Quadrado) - Lado -> " + this.lado + base.ToString();
        }

    }
}
