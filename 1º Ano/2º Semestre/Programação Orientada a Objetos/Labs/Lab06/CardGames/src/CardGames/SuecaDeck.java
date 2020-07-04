package CardGames;

/**
 * Representa um baralho de cartas usado no jogo da sueca.
 * 
 * @author POO 2019/2020
 * @version abr/2020
 */
// Nível 5
public class SuecaDeck extends Deck {

    private final static int ACE_VALUE = 11;
    private final static int SEVEN_VALUE = 10;
    private final static int QUEEN_VALUE = 2;
    private final static int JACK_VALUE = 3;
    private final static int KING_VALUE = 4;

    public SuecaDeck() {

        createCards();
    }

    private void createCards() {
        clear();
        for (Suit suit : Suit.values()) {
            if (suit == Suit.NONE) {
                continue;
            }
            addCard(new NumberedCard(1, suit, ACE_VALUE));
            for (int i = 2; i <= 6; i++) {
                addCard(new NumberedCard(i, suit, 0));
            }
            addCard(new NumberedCard(7, suit, SEVEN_VALUE));
            addCard(new FaceCard(FaceName.QUEEN, suit, QUEEN_VALUE));
            addCard(new FaceCard(FaceName.JACK, suit, JACK_VALUE));
            addCard(new FaceCard(FaceName.KING, suit, KING_VALUE));
        }
    }
}
