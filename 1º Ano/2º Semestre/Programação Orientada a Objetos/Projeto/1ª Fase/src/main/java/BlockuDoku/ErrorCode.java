package BlockuDoku;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alexandre e Sérgio
 */
public enum ErrorCode {

    /**
     * O nome do jogador não pode ser null.
     */
    PLAYER_NAME_CANT_BE_NULL,

    /**
     * Não é possivel posicionar o block no tabuleiro.
     */
    CANT_PLACE_BLOCK,

    /**
     * O ficheiro não pode ser null ou vazio.
     */
    FILE_CANT_BE_NULL_OR_EMPTY,
    
    /**
     * Não é possivel guardar o jogo.
     */
    CANT_SAVE_GAME,
    
    /**
     * Não é possivel guardar o jogador.
     */
    CANT_SAVE_PLAYER;
    

    /**
     * Recebe o error code e retorna a respetiva string.
     * @return String
     */
    @Override
    public String toString() {
        switch (this) {
            case PLAYER_NAME_CANT_BE_NULL:
                return "O nome do jogador tem de ser fornecido";
            case CANT_PLACE_BLOCK:
                return "O bloco não pode ser posicionado na posição escolhida";
            case FILE_CANT_BE_NULL_OR_EMPTY:
                return "O ficheiro para imprimir não ser vazio ou nulo";
            case CANT_SAVE_GAME:
                return "Não é possivel guardar o jogo";
            default:
                return "";
        }
    }
}
