/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

import java.util.Comparator;

/**
 *
 * @author Alexandre, Sergio
 */
public class CardSuitValueComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        int suitCompare = card1.getSuit().compareTo(card2.getSuit());
        int valueCompare = ((Integer) card1.getValue()).compareTo(((Integer) card2.getValue()));
      
        if (suitCompare == 0) {        
            return valueCompare;
        } else {
            return suitCompare;
        }
    }

}
