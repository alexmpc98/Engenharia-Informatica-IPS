/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alexandre, Sergio
 */
public class GameScores {
    private List<ValuedElement<Player>> playerList;
    
    public GameScores () {
        this.playerList = new LinkedList<>();
    }
    
    public void insertScore(Player player, int score) {
        this.playerList.add(new ValuedElement(player, score));
    }
    
    @Override
    public String toString() {
        String string = "------ Scores ------\n";
        Collections.sort(this.playerList, Collections.reverseOrder());
        for (int i = 0; i < 10; i++) {
            if (i < this.playerList.size()) 
                string += i+1 + " - " + this.playerList.get(i).getElement().getName() + ":  " + this.playerList.get(i).getValue() + "\n";
            else
                string += i+1 + " - \n";
        }
        return string;
    }
}
