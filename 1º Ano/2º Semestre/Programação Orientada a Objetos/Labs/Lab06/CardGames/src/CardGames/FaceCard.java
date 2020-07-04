package CardGames;

/**
 * Representa uma carta de jogar com uma figura.
 * 
 * @author POO 2019/2020
 * @version maio/2020
 */

public class FaceCard extends Card {
    private FaceName faceName;

    public FaceCard(FaceName faceName, Suit suit, int value) {
        super(suit, value);
        this.faceName = faceName;
    }

    public FaceCard(FaceName face, Suit suit) {
        super(suit);
        this.faceName = face;
    }

    public FaceName getFaceName() {
        return faceName;
    }

    @Override
    public String getName() {
        return faceName.toString(); 
    }

    @Override
    public String toString() {
        return faceName + " de " + getSuit();
    }
}
