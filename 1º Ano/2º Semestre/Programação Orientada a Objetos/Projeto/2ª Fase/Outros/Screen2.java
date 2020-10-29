/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.GameMode;
import BlockuDoku.Backend.PlayerFileHandler;
import BlockuDoku.Backend.GameFileHandler;
import BlockuDoku.Backend.ExceptionManager;
import BlockuDoku.Backend.PlayerManager;
import BlockuDoku.Backend.Game;
import BlockuDoku.Backend.Block;
import BlockuDoku.Backend.GameManager;
import BlockuDoku.Backend.Player;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Screen2 {

    /**
     * Constructor que inicializa o ecrã vazio.
     *
     */
    public Screen2() {
    }

    /**
     * Este método imprime no ecrã o menu inicial do jogo e recebe e retorna a
     * escolha do utilizador.
     *
     * @param player Player
     * @return Opção do menu (int)
     */
    public int displayApplicationMenu(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nOlá " + player.getName());
        System.out.println("|*********************************|");
        System.out.println("|           BlockuDoku            |");
        System.out.println("|*********************************|");
        System.out.println("| 1 - Iniciar novo jogo           |");
        System.out.println("| 2 – Abrir jogo                  |");
        System.out.println("| 3 – Mostrar pontuações pessoais |");
        System.out.println("| 4 – Ranking (TOP 10)            |");
        System.out.println("| 0 - Sair                        |");
        System.out.println("|*********************************|");
        System.out.print("Opção: ");
        int option = scanner.nextInt();
        return option;
    }

    /**
     * Este método imprime no ecrã o menu de escolha do modo de jogo e recebe e
     * retorna a escolha do utilizador.
     *
     * @return Opção do menu (int)
     */
    public int displayGameMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n|***************************************|");
        System.out.println("|               BlockuDoku              |");
        System.out.println("|***************************************|");
        System.out.println("| 1 - Iniciar novo jogo - modo básico   |");
        System.out.println("| 2 – Iniciar novo jogo - modo avançado |");
        System.out.println("| 0 - Sair                              |");
        System.out.println("|***************************************|");
        System.out.print("Opção: ");
        int option = scanner.nextInt();
        return option;
    }

    /**
     * Este método imprime no ecrã o pedido da próxima jogada (seleção do bloco,
     * coluna e linha) e recebe e retorna a escolha do utilizador.
     *
     * @return Próxima jogada (String)
     */
    public String displayNewPlay() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique a próxima jogada (Bloco-ColunaLinha): ");
        String option = scanner.next();
        return option;
    }

    /**
     * Este método imprime no ecrã o pedido de inserção do nome de utilizador e
     * recebe e retorna o nome introduzido pelo utilizador.
     *
     * @return Nome de utilizador (String)
     */
    public String displayEnterUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o seu nome: ");
        String option = scanner.next();
        return option;
    }

    /**
     * Este método imprime no ecrã o top 10 de jogadores.
     *
     * @param players (List(Player))
     */
    public void displayTopPlayers(List<Player> players) {
        System.out.println("\nRanking TOP 10: \n");
        for (Player player : players) {
            System.out.print(player+"\n");
        }
        System.out.println("\n");
    }

    /**
     * Este método mostra os menus.
     *
     */
    public void displayMenus() {
        String playerName = displayEnterUsername();
        Player player;
        PlayerManager playerManager;
        GameManager gameManager = handleGameManager();
        if (PlayerFileHandler.fileExists("players", ".bin") != false) {
            playerManager = PlayerFileHandler.loadPlayerManager("players.bin");
            if (playerManager == null) {
                playerManager = new PlayerManager();
            }
            player = playerManager.getPlayerByName(playerName);
            if (player == null) {
                player = new Player(playerName);
                playerManager.addPlayer(player);
                try {
                    PlayerFileHandler.savePlayerManager("players.bin", playerManager);
                } catch (ExceptionManager em) {
                    System.out.println(em);
                }
            }
        } else {
            playerManager = new PlayerManager();
            player = new Player(playerName);
            playerManager.addPlayer(player);
            try {
                PlayerFileHandler.savePlayerManager("players.bin", playerManager);
            } catch (ExceptionManager em) {
                System.out.println(em);
            }
        }
        applicationMenu:
        while (true) {
            switch (displayApplicationMenu(player)) {
                case 1:  // Iniciar novo jogo
                    gameMenu:
                    while (true) {
                        GameMode gameMode = GameMode.BASIC;
                        switch (displayGameMenu()) {
                            case 1:  // Iniciar novo jogo - modo básico
                                gameMode = GameMode.BASIC;
                                break;
                            case 2:  // Iniciar novo jogo - modo avançado
                                gameMode = GameMode.ADVANCED;
                                break;
                            case 0:  // Sair
                                break gameMenu;
                            default: // Opção Inválida
                                System.out.println("Opção Inválida");
                                break;
                        }
                        Game game = new Game(gameMode, player);
                        startGame(game, gameManager, playerManager);
                    }
                    break;
                case 2:  // Abrir jogo
                    Game game = gameManager.getGameByName(player.getName());
                    if (game != null) {
                        startGame(game, gameManager, playerManager);
                    } else {
                        System.out.println("Não possui jogos guardados");
                    }
                    break;
                case 3:  // Mostrar pontuações pessoais
                    System.out.println(player.getScoreHistory());
                    break;
                case 4:  // Ranking (TOP 10)
                    displayTopPlayers(playerManager.getTopPlayers(10));
                    break;
                case 0:  // Sair
                    try {
                        PlayerFileHandler.savePlayerManager("players.bin", playerManager);
                    } catch (ExceptionManager em) {
                        System.out.println(em);
                    }
                    break applicationMenu;
                default: // Opção Inválida
                    System.out.println("Opção Inválida");
                    break;
            }
        }
    }

    /**
     * Este método verifica se existe um gestor de jogos guardado no ficheiro de
     * jogos e consoante essa verificação cria um gameManager ou vai buscar o
     * guardado e retorna esse gestor de jogos.
     *
     * @return Gestor de jogos ou null (GameManager)
     */
    public GameManager handleGameManager() {
        GameManager gameManager = null;
        if (GameFileHandler.fileExists("games", ".bin") != false) {
            gameManager = GameFileHandler.loadGameManager("games.bin");
            if (gameManager == null) {
                gameManager = new GameManager();
            }
            /*else {
                System.out.println(gameManager.getGames());
            }*/
        } else {
            gameManager = new GameManager();
        }
        return gameManager;
    }

    /**
     * Este metodo recebe uma instância de game e começa a rodada.
     *
     * @param game Game
     * @param gameManager GameManager
     * @param playerManager PlayerManager
     */
    public void startGame(Game game, GameManager gameManager, PlayerManager playerManager) {
        String gameOption = "";
        Block lastBlock = null;
        gameRound:
        while (true) {
            if (lastBlock != null) {
                //System.out.println(game.addPoints(lastBlock));
                if (game.addPointsToScore(lastBlock)) {
                    System.out.print(game.getBoard().getBoardDraw());
                    System.out.println("Pontos: " + game.getScore().getPoints());
                }
            }
            System.out.print(game.getBoard().getBoardDraw());
            System.out.println("Pontos: " + game.getScore().getPoints());
            if (game.getRoundBlocks().size() <= 0) {
                game.generateRoundBlocks();
            }
            System.out.println(game.getRoundBlocksToDraw());
            gameOption = displayNewPlay().toUpperCase().replaceAll("\\s+", "");
            switch (gameOption) {
                case "CANCEL":  // Cancelar jogo
                    break gameRound;
                case "SAVE":   // Guardar jogo;
                    if (game.saveGame(gameManager) == true) {
                        if (playerManager.verifyIfScoreExist(game.getScore(), game.getPlayer()) != true) {
                            //System.out.print(game.getScore());
                            game.getPlayer().addScore(game.getScore());
                            try {
                                PlayerFileHandler.savePlayerManager("players.bin", playerManager);
                            } catch (ExceptionManager em) {
                                System.out.println(em);
                            }
                        }
                        System.out.println(game.getPlayer().getTotalPoints());
                        System.out.println("Jogo guardado com sucesso!");
                        break gameRound;
                    } else {
                        break;
                    }
                default:      // Próxima rodada
                    //lastBlock = game.play(gameOption);
                    break;
            }
        }
    }
}
