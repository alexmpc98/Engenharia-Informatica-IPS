package CardGames;

/**
 * Este tipo enumerado contém as figuras possíveis de uma carta de jogar.
 *
 * @author POO 2019/2020
 * @version maio/2020
 */
public enum FaceName {
    JACK, QUEEN, KING;

    @Override
    public String toString() {
        switch (this) {
            case JACK:
                return "valete";
            case QUEEN:
                return "dama";
            case KING:
                return "rei";
            default:
                return "";
        }
    }
}
