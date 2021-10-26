using System;

namespace Lab_0
{
    class Program
    {
        static void Main(string[] args)
        {
            Posicao p1 = new Posicao();
            Posicao p2 = new Posicao('b', 1);
            Console.WriteLine(p1.ToString());
            Console.WriteLine(p2.ToString());
            Peao peao = new Peao(p1,Peca.Cor.Preto);
            Torre torre = new Torre(p2, Peca.Cor.Branco);
            Console.WriteLine(peao.ToString());
            Console.WriteLine(torre.ToString());
            Peca[] arrayPecas = new Peca[2];
            arrayPecas[0] = peao;
            arrayPecas[1] = torre;
            foreach (Peca p in arrayPecas)
            {
                Console.WriteLine(" Nome : " + p.getNome());
            }

            
        }
    }
}
