package CardGames;

/**
 * Este tipo enumerado contém os naipes de uma carta de jogar.
 * O tipo NONE é reservado para cartas especiais como o Joker.
 * 
 * @author POO 2019/2020
 * @version maio/2020
 */

public enum Suit {
    NONE, SPADES, HEARTS, DIAMONDS, CLUBS;

    @Override
    public String toString() {
        switch (this) {
            case SPADES:
                return "espadas";
            case HEARTS:
                return "copas";
            case DIAMONDS:
                return "ouros";
            case CLUBS:
                return "paus";
            default:
                return "";
        }
    } 
}
