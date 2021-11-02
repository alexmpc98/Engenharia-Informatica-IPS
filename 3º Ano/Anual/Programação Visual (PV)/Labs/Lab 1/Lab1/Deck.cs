using System;
using System.Collections.Generic;
using System.Text;

namespace Lab1
{
    class Deck
    {
        private List<Card> cards;
        private Random random = new Random();

        public Deck()
        {
            cards = new List<Card>();
        }
        public Deck(List<Card> cards)
        {
            this.cards = new List<Card>();
            if (cards != null)
            {
                foreach (Card card in cards)
                {
                    this.cards.Add(card);
                }
            }
        }

        public void AddCard(Card card)
        {
            if (card != null)
            {
                cards.Add(card);
            }
        }
        public bool RemoveCard(Card card)
        {
            return cards.Remove(card);
        }

        public void Clear()
        {
            cards.Clear();
        }

        public bool hasNext(int index)
        {
            if (this.cards[index + 1] != null)
            {
                return true;
            }
            return false;
        }
        public Card Next(int index)
        {
            if (hasNext(index))
            {
                return this.cards[index + 1];
            }
            return null;
        }

        public void Remove()
        {

        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder(" ", 100);
            foreach (Card card in cards)
            {
                sb.Append(card.ToString());
            }

            return sb.ToString();
        }
        public Card GetRandomCard()
        {
            if (cards == null)
            {
                return null;
            }
            int randomIndex = random.Next(cards.Count - 1);
            Card card = cards[randomIndex];
            cards.RemoveAt(randomIndex);
            return card;
        }

        public void SortByValue()
        {
            cards.Sort();
        }

        public void Shuffle()
        {
            Random rnd = new Random();
            for (int i=0; i < this.cards.Count; i++)
            {
                int r = rnd.Next(0, i);
                Card cardValue = cards[r];
                cards[r] = cards[i];
                cards[i] = cards[r];
            }
        }

        public List<Card> Cards
        {
            get
            {
                return new List<Card>(this.cards);
            }
        }
        public Card TopCard()
        {
            return cards[cards.Count - 1];
        }

        public Card DrawCard()
        {
            Card card = cards[cards.Count - 1];
            cards.Remove(card);
            return card;
        }
        public Card PutCard(Card card)
        {
            AddCard(card);
            return card;
        }
    }
}
