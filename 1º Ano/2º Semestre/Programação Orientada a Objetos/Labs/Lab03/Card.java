public class Card
{
    private Suit suit;
    private int value;
    private NumberedCard number;
    
    public Card(Suit suit) {
      /*switch(suit){
         case SPADES:
            this.suit = Suit.SPADES;
         case HEARTS:
            this.suit = Suit.HEARTS;
         case DIAMONDS:
            this.suit = Suit.DIAMONDS;
         case CLUBS:
            this.suit = Suit.CLUBS; 
      }*/
      if(suit == Suit.SPADES) {
            this.suit = Suit.SPADES;
      } else if(suit == Suit.HEARTS) {
            this.suit = Suit.HEARTS;
      } else if(suit == Suit.DIAMONDS) {
            this.suit = Suit.DIAMONDS;
      } else if(suit == Suit.CLUBS) {
            this.suit = Suit.CLUBS; 
      } 
    }
    
    public Card(Suit suit, int value) {   
      if(suit == Suit.SPADES) {
            this.suit = Suit.SPADES;
      } else if(suit == Suit.HEARTS) {
            this.suit = Suit.HEARTS;
      } else if(suit == Suit.DIAMONDS) {
            this.suit = Suit.DIAMONDS;
      } else if(suit == Suit.CLUBS) {
            this.suit = Suit.CLUBS; 
      } 
      this.value = value > 0 ? value : 0;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value > 0 ? value : 0;
    }
    
    public Suit getSuit() {
        return this.suit;
    }
    
    public String getName() {
        return "";
    }
    
    public int getNumber() {
        return 0;
    }
    
    public FaceName getFaceName() {
        return null;
    }
    
}
