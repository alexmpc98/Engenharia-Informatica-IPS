using System;
using System.Collections.Generic;
using System.Text;

namespace Lab1
{
    class SuecaDeck : Deck
    {
        private static int ACE_VALUE = 11;
        private static int SEVEN_VALUE = 10;
        private static int QUEEN_VALUE = 2;
        private static int JACK_VALUE = 3;
        private static int KING_VALUE = 4;

        public SuecaDeck()
        {
            createCards();
        }
        private void createCards()
        {
            base.Clear();
            foreach (Suit suit in Enum.GetValues(typeof(Suit)))
            {
                if (suit == Suit.None)
                {
                    continue;
                }
                base.AddCard(new NumberedCard(1, suit, ACE_VALUE));
                for (int i = 2; i <= 6; i++)
                {
                    base.AddCard(new NumberedCard(i, suit, 0));
                }
                base.AddCard(new NumberedCard(7, suit, SEVEN_VALUE));
                base.AddCard(new FaceCard(FaceName.Queen, suit, QUEEN_VALUE));
                base.AddCard(new FaceCard(FaceName.Jack, suit, JACK_VALUE));
                base.AddCard(new FaceCard(FaceName.King, suit, KING_VALUE));
            }
        }

    }
}
