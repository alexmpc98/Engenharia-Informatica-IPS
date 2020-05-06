public class NumberedCard extends Card
{
    private int number;
    
    public NumberedCard(int number, Suit suit) {
        super(suit);
        this.number = number > 0 ? number : 0;
    }
    
    public NumberedCard(int number, Suit suit, int value) {
        super(suit,value > 0 ? value : 0);
        this.number = number > 0 ? number : 0;  
    }
    
    @Override
    public int getNumber() {
        return this.number;
    }
    
    public String getName() {
        return this.number == 1 ? "Ás" : String.valueOf(this.number);
    }
    
    //@Override
    public String toString() {
        return getName() + " de " + String.valueOf(getSuit());
    }
    
}
