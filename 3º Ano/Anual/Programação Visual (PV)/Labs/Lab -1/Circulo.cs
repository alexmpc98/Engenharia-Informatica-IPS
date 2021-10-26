using System;
using System.Collections.Generic;
using System.Text;

namespace Lab__1
{
    class Circulo : Figura,IMover
    {
        public float raio { get; set; }
        public Circulo(float x,float y, float raio) : base(x, y)
        {
            this.raio = raio;
        }
        public override float GetArea()
        {
            return raio * raio * (float)Math.PI;
        }
        void IMover.Deslocar(int dx, int dy)
        {
            base.x = dx;
            base.y = dy;
        }
        public override string ToString()
        {
            return "(Circulo) - Raio -> " + this.raio + base.ToString();
        }
    }
}
