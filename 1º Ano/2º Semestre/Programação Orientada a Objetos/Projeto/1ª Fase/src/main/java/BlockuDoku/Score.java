/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 *
 * @author Alexandre e Sérgio
 */
public class Score implements Serializable {
    private LocalDateTime dateTime;
    private int points;
    
    /**
     * Constructor que inicializa a pontuação (Score).
     * 
     */
    public Score() {
        this.dateTime = LocalDateTime.now();
        this.points = 0;
    }
    
    /**
     * Este método adiciona pontos.
     * 
     * @param points int
     */
    public void addPoints(int points) {
        this.points += points;
    }
    
    /**
     * Este método retorna os pontos.
     * 
     * @return Pontos (int)
     */
    public int getPoints() {
        return this.points;
    }
    
    /**
     * Este método retorna a data e a hora. 
     * 
     * @return Date e hora (LocalDateTime)
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
