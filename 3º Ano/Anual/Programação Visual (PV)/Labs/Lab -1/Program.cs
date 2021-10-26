using System;

namespace Lab__1
{
    class Program
    {
        static void Main(string[] args)
        {
            IMover circulo = new Circulo(2, 2, 2);
            IMover quadrado = new Quadrado(2, 2, 2);
            quadrado.Deslocar(1,2);
            Console.WriteLine(quadrado.ToString());
            Console.WriteLine(circulo.ToString());
            Desenho desenho = new Desenho();
            desenho.AdicionarFigura((Figura)quadrado);
            desenho.AdicionarFigura((Figura)circulo);
            Console.WriteLine(desenho.ToString());
            Console.WriteLine("Area Total -> "  + desenho.GetArea());
            desenho.RemoverFigura(1);
            Console.WriteLine(desenho.ToString());
            Console.WriteLine("Area Total -> " + desenho.GetArea());
        }
    }
}
