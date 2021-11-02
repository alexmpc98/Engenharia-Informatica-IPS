using System;
using System.Collections.Generic;
using System.Text;

namespace Lab1
{
    class NumberedCard : Card
    {
        private int number;  // número da carta (1 a 10)

        public NumberedCard(int number, Suit suit) : base(suit)
        {
            this.number = number;
        }

        public NumberedCard(int number, Suit suit, int value) : base(suit,value)
        {
            this.number = number;
        }
        public NumberedCard() : base(Suit.None)
        {
            this.number = 0;
        }

        public int Number() => this.number;

        public override string Name
        { get { 
            switch (Number())
            {
                case 1:
                    return "ás";
                    break;
                case 2:
                    return "dois";
                    break;
                case 3:
                    return "três";
                    break;
                case 4:
                    return "quatro";
                    break;
                case 5:
                    return "cinco";
                    break;
                case 6:
                    return "seis";
                    break;
                case 7:
                    return "sete";
                    break;
                case 8:
                    return "oito";
                    break;
                case 9:
                    return "nove";
                    break;
                case 10:
                    return "dez";
                    break;
                default:
                    return "";
                    break;
                }
            }
        }

        public override string ToString() => "Carta -> " + Name + " de " + base.GetSuit().ToNameString() + "\n";

    }
}
