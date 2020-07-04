
package CardGames;

/**
 * Representa uma carta de jogar.
 * 
 * @author POO 2019/2020
 * @version maio/2020
 */

public abstract class Card implements Comparable<Card> {

    private Suit suit;  // naipe
    private int value;  // depende do jogo, não é o número da carta 

    public Card(Suit suit) {
        this.suit = suit;
    }

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public abstract String getName();
    
    @Override
    public int compareTo(Card card){
         return ((Integer)this.value).compareTo(((Integer)card.value));
    }
}
