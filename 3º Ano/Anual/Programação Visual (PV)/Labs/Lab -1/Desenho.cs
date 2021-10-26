using System;
using System.Collections.Generic;
using System.Text;

namespace Lab__1
{
    
    class Desenho : IMover
    {
        public List<Figura> fList;

        public Desenho()
        {
            this.fList = new List<Figura>();
        }
        public void AdicionarFigura(Figura figura)
        {
            fList.Add(figura);
        }
        void IMover.Deslocar(int dx, int dy)
        {
            foreach(Figura f in this.fList)
            {
                f.x = dx;
                f.y = dy;
            }
        }
        public float GetArea()
        {
            float totalArea = 0;
            foreach(Figura f in this.fList)
            {
                totalArea += f.GetArea();
            }
            return totalArea;
        }
        public void RemoverFigura(int indice)
        {
            fList.RemoveAt(indice);
        }
        public override string ToString()
        {
            string figuresString = "Figuras: ";
            foreach(Figura f in this.fList)
            {
                figuresString += f.ToString() + "/";
            }
            return figuresString;
        }
    }
}
