import java.util.ArrayList;
public class SuecaDeck extends Deck{
 
    public SuecaDeck() {
        super();
        for (Suit suit : Suit.values()) {
            for (int i = 1; i < 8; i++) { 
                if (suit != Suit.NONE) {
                    Card card = new NumberedCard(i, suit);
                    super.addCard(card);
                }
            }
            for (FaceName faceName : FaceName.values()) {
                if (suit != Suit.NONE) {
                    Card card = new FaceCard(faceName, suit);
                    super.addCard(card);
                }
            }
        }
    }
}
