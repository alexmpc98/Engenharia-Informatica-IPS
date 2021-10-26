using System;
using System.Collections.Generic;
using System.Text;

namespace Lab_0
{
    abstract class Peca : IMover
    {
        Posicao Position;
        public enum Cor { Branco, Preto, None};
        private Cor Color;
        private string Nome = "desconhecida";
        public Peca()
        {
            Position = new Posicao();
            Color = Cor.None;
        }
        public Peca(Posicao newPos,Cor newColor, string Nome)
        {
            this.Position = newPos;
            this.Color = newColor;
            this.Nome = Nome;
        }
        public abstract void Deslocar(int dx, int dy);

        public override string ToString()
        {
            return Color.ToString() + " / " + Position.ToString();
        }
        public string getNome()
        {
            return this.Nome;
        }
        public Posicao getPosition()
        {
            return this.Position;
        }
        public void setPosition(Posicao newPos)
        {
            this.Position = newPos;
        }
    }
}
