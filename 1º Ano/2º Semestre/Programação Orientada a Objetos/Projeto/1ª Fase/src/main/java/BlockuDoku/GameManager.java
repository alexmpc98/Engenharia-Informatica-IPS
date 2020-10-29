/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alexandre e Sérgio
 */
public class GameManager implements Serializable {

    private ArrayList<Game> games;

    /**
     * Constructor que inicializa um GameManager.
     * 
     */
    public GameManager() {
        this.games = new ArrayList<>();
    }

    /**
     * Este método recebe um nome de um jogador e devolve o jogo guardado.
     * 
     * @param name String
     * @return jogo (Game)
     */
    public Game getGameByName(String name) {
        Game game = games.stream().filter(p -> p.getPlayer().getName().equals(name)).reduce((a, b) -> b).orElse(null);        
        return game;       
    }
    
    /**
     * Este método recebe um jogo e devolve os seus jogos guardados que existem.
     * 
     * @param game Game
     * @return Lista de jogos (List(Game))
     */
    public List<Game> getGameByObject(Game game) {
        List<Game> filteredGames = games.stream().filter(cGame -> cGame == game).collect(Collectors.toList());
        if (filteredGames.size() > 0) {
            return filteredGames;
        } else {
            return null;
        }   
    }
    
      
    /**
     * Este método recebe um jogo e adiciona-o a lista de jogos.
     * 
     * @param game Game
     */
    public void addGame(Game game) {
        this.games.add(game);
    }
    
    /**
     * Este método retorna uma lista de jogos.
     * 
     * @return Lista de jogos (ArrayList(Game))
     */
    public ArrayList<Game> getGames() {
        return this.games;
    }
    
}
