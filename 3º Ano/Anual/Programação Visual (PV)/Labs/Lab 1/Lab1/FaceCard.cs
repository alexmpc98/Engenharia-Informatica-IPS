using System;
using System.Collections.Generic;
using System.Text;

namespace Lab1
{
    class FaceCard : Card
    {
        private FaceName faceName;

        public FaceCard(FaceName faceName, Suit suit, int value) : base(suit, value) => this.faceName = faceName;

        public FaceCard(FaceName faceName, Suit suit) : base(suit) => this.faceName = faceName;

        public FaceCard() : base(Suit.None) => this.faceName = FaceName.None;
        
        public override string Name => this.faceName.ToString();
        public FaceName FaceName => this.faceName;

        public override string ToString() => "Carta -> " + this.faceName.ToNameString() + " de " + base.GetSuit().ToNameString() + "\n";

    }
}
