using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    class Tabuleiro
    {
        Peca[,] arrayPecas;

        public Tabuleiro()
        {
            arrayPecas = new Peca[8,8];
            Peca torre = new Torre();
            Peca bispo = new Bispo();
            Peca peao = new Peao();
            Peca rainha = new Rainha();
            Peca rei = new Rei();
            Peca cavalo = new Cavalo();
            arrayPecas[0,0] = torre;
            arrayPecas[0,1] = cavalo;
            arrayPecas[0, 2] = bispo;
            arrayPecas[0, 3] = rei;
            arrayPecas[0, 4] = rainha;
            arrayPecas[0, 5] = bispo;
            arrayPecas[0, 6] = cavalo;
            arrayPecas[0, 7] = torre;
            for(int i=0; i<8; i++)
            {
                arrayPecas[i, 1] = peao;
            }
        }
        public void Mostrar()
        {
            foreach(Peca peca in arrayPecas)
            {
                Console.WriteLine(peca.getNome());
            }
        }
    }
}
