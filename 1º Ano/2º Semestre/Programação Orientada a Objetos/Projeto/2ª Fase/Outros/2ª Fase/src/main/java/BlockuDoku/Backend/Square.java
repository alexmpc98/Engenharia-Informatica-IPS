/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Backend;

import java.util.Objects;
import java.io.Serializable;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Square implements Comparable<Square>, Serializable {

    private int x;
    private int y;

    /**
     * Construtor que recebe um valor X e um valor Y e inicializa um quadrado.
     *
     * @param x int
     * @param y int
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Este método retorna o valor de X.
     *
     * @return Valor de X (int)
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * Este método retorna o valor de Y.
     *
     * @return Valor de Y (int)
     */
    public int getY() {
        return this.y;
    }

    /**
     * Este método compara a igualdade de dois objectos (squares).
     * 
     * @param object Object
     * @see Square#hashCode()
     * @return Verdadeiro ou falso (boolean)
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Square order = (Square) object;
        return this.y == order.y;
    }

    /**
     * Este método retorna o hashcode do objecto (necessário para implementação
     * do método equals).
     *
     * @return Hashcode (int)
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.y);
    }

    /**
     * Este método que recebe um quadrado e compara, retornando esta comparação.
     *
     * @param square Square
     * @return Comparação do quadrado (int)
     */
    @Override
    public int compareTo(Square square) {
        return ((Integer) this.y).compareTo(((Integer) square.y));
    }

    /**
     * Este método retorn o valor X e Y do quadrado.
     *
     * @return Valor X e Y do quadrado (String)
     */
    @Override
    public String toString() {
        return "(X:" + this.x + " | Y:" + this.y + ")";
    }
}
