/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alexandre e Sérgio
 */
public class PlayerManager implements Serializable {

    private ArrayList<Player> players;

    /**
     * Constructor que inicializar o PlayerManager.
     *
     */
    public PlayerManager() {
        this.players = new ArrayList<>();
    }

    /**
     * Este método recebe um nome e devolve o jogador ou null se não existir um
     * jogador como o nome.
     *
     * @param name String
     * @return Jogador ou Null (Player)
     */
    public Player getPlayerByName(String name) {
        List<Player> equalPlayers = this.players.stream().filter(p -> p.getName().equals(name.replaceAll("\\s+", ""))).collect(Collectors.toList());
        if (equalPlayers.size() > 0) {
            return equalPlayers.get(0);
        } else {
            return null;
        }
    }

    /**
     * Este método recebe uma pontuação e um jogador e verifica se já existe a
     * pontuação.
     *
     * @param score Score
     * @param player Player
     * @return Verdadeiro ou falso (boolean)
     */
    public boolean verifyIfScoreExist(Score score, Player player) {
        for (Score scoreObj : player.getScoreList()) {
            if (scoreObj.getDateTime() == score.getDateTime()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Este método recebe um limit e retorna os 10 melhores jogadores.
     *
     * @param limit int
     * @return Lista de jogadores (List(Player))
     */
    public List<Player> getTopPlayers(int limit) {
        ArrayList<Player> playerList = this.players;
        Collections.sort(playerList, Comparator.comparing(Player::getTotalPoints).reversed());
        List<Player> filteredPlayers = playerList
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
        if (filteredPlayers.size() > 0) {
            return filteredPlayers;
        } else {
            return null;
        }
    }

    /**
     * Este método recebe reinicia o ranking dos 10 melhores jogadores.
     *
     */
    public void resetTopPlayers() {
        for (Player player : this.players) {
            player.resetScoreList();
        }
    }

    /**
     * Este método recebe um jogador e adiciona-o a lista de jogadores.
     *
     * @param player Player
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Este método retorna uma lista de jogadores.
     *
     * @return Lista de jogares (ArrayList(Player))
     */
    public ArrayList<Player> getPlayers() {     
        return this.players;
    }
}
