package CardGames;

/**
 * Representa de uma carta de jogar com um número.
 *
 * @author POO 2019/2020
 * @version maio/2020
 */

public class NumberedCard extends Card {

    private int number;  // número da carta (1 a 10)

    public NumberedCard(int number, Suit suit) {
        super(suit);
        this.number = number;
    }

    public NumberedCard(int number, Suit suit, int value) {
        super(suit, value);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String getName() {
        switch (number) {
            case 1:
                return "ás";
            case 2:
                return "dois";
            case 3:
                return "três";
            case 4:
                return "quatro";
            case 5:
                return "cinco";
            case 6:
                return "seis";
            case 7:
                return "sete";
            case 8:
                return "oito";
            case 9:
                return "nove";
            case 10:
                return "dez";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return getName() + " de " + getSuit();
    }
}
