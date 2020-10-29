/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Backend;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Player implements Serializable {

    private String name;
    private ArrayList<Score> scoreList;

    /**
     * Constructor que recebe um nome e inicializa um jogador.
     *
     * @param name String
     */
    public Player(String name) {
        this.name = name;
        this.scoreList = new ArrayList<>();
    }

    /**
     * Este método retorna o nome do jogador.
     *
     * @return Nome do jogador (String)
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Este método retorna a lista de pontuações.
     *
     * @return Lista de pontuações (ArrayList(Score))
     */
    public ArrayList<Score> getScoreList() {
        return this.scoreList;
    }
    
    /**
     * Este método limpa a lista de pontuações.
     *
     */
    public void resetScoreList() {
       this.scoreList.clear();
    }

    /**
     * Este método adiciona a pontuação da lista de pontuações.
     *
     * @param score Score
     */
    public void addScore(Score score) {
        this.scoreList.add(score);
    }

    /**
     * Este método retorna o total de pontos do utilizador.
     *
     * @return Total de pontos (int)
     */
    public int getTotalPoints() {
        int total = 0;
        for (Score score : this.scoreList) {
            total += score.getPoints();
        }
        return total;
    }

    /**
     * Este método a melhor pontuação do jogador.
     *
     * @return Melhor pontuação (Score)
     */
    public Score getBestScore() {
        Score bestScore;
        if (this.scoreList.size() > 0) {
            bestScore = this.scoreList.get(0);
        } else {
            bestScore = null;
        }
        for (Score score : this.scoreList) {
            if (score.getPoints() > bestScore.getPoints()) {
                bestScore = score;
            }
        }
        return bestScore;
    }

    /**
     * Este método retorna o histórico de pontuações do utilizador.
     *
     * @return Histórico de pontuações (String)
     */
    public String getScoreHistory() {
        String output = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        if (this.scoreList.size() > 0) {
            output = "\nHitórico de pontuações: \n";
            for (Score score : this.scoreList) {
                output += "Data e hora: " + score.getDateTime().format(formatter) + " | Pontos: " + score.getPoints()+"\n";
            }
        } else {
            output = "Sem pontuações anteriores!";
        }
        return output;
    }

    /**
     * Este método retorna o nome do respetivo jogador e a pontuação em string.
     *
     * @return Nome do jogador e pontuação (String)
     */
    public String toString() {
        Score bestScore = getBestScore();       
        return "Name: " + this.name + " | " + "Pontuação: " + (bestScore == null ? 0 : bestScore.getPoints());
    }
}
