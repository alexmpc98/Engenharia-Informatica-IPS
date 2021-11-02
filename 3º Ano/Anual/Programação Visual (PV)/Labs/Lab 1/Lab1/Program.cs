using System;

namespace Lab1
{
    class Program
    {
        static void Main(string[] args)
        {
            Card numberedCard = new NumberedCard();
            Card faceCard = new FaceCard();
            Card numberedCard2 = new NumberedCard(10,Suit.Hearts);
            Card faceCard2 = new FaceCard(FaceName.Queen,Suit.Spades);
            Console.WriteLine(numberedCard.ToString());
            Console.WriteLine(faceCard.ToString());
            Console.WriteLine(numberedCard2.ToString());
            Console.WriteLine(faceCard2.ToString());
            Deck deck = new Deck();
            deck.AddCard(faceCard2);
            deck.AddCard(numberedCard2);
            Console.WriteLine("1 " + deck.ToString());
            Console.WriteLine("2 " + deck.GetRandomCard().ToString());
            Console.WriteLine("3 " + deck.TopCard().ToString());
            Console.WriteLine("4 " + deck.DrawCard().ToString());
            Console.WriteLine("5 " + deck.ToString());
            deck.PutCard(numberedCard2);
            Console.Write("6 " + deck.ToString());
            Console.WriteLine("7 " + deck.RemoveCard(numberedCard2));
            Console.WriteLine("8 " + deck.ToString());

            Deck newDeck = new SuecaDeck();
            Console.WriteLine("9 " + newDeck.ToString());
            newDeck.Shuffle();
            Console.WriteLine("10 " + newDeck.ToString());
            newDeck.SortByValue();
            Console.WriteLine("11 " + newDeck.ToString());
        }
    }
}
