using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace Lab1
{
    abstract class Card : IComparable<Card>
    {
        private Suit suit;
        int value;

        public Card()
        {
            this.suit = Suit.None;
        }
        public Card(Suit suit)
        {
            this.suit = suit;
        }
        public Card(Suit suit, int value)
        {
            this.suit = suit;
            this.value = value;
        }

        public Suit GetSuit() => this.suit;
        public int GetValue() => this.value;

        public void SetSuit(Suit newSuit)
        {
            this.suit = newSuit;
        }

        public void SetValue(int newValue)
        {
            this.value = newValue;
        }
        public abstract string Name {get;}
      
        public int CompareTo([AllowNull] Card other)
        {
            return other.value - this.value;
        }
    }
}
