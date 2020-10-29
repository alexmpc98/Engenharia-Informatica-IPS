/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Game implements Serializable {

    private ArrayList<Block> gameModeBlocks;
    private ArrayList<Block> currentBlocks;
    private HashMap<String, Block> roundBlocks;
    private GameMode gameMode;
    private Board board;
    private Score score;
    private int round;
    private Player player;

    /**
     * Contrutor da class game, que inicializa uma instância de jogo.
     *
     * @param gameMode GameMode Enum
     * @param player Player
     */
    public Game(GameMode gameMode, Player player) {
        this.round = 0;
        this.gameMode = gameMode;
        this.roundBlocks = new HashMap<String, Block>();
        this.gameModeBlocks = new ArrayList<Block>();
        this.currentBlocks = new ArrayList<Block>();
        this.board = new Board();
        this.score = new Score();
        this.player = player;
        ArrayList<Square> squares = new ArrayList<Square>();
        switch (gameMode) {
            case ADVANCED:
                createAdvancedBlocks(squares);
                createBasicBlocks(squares);
                break;
            case BASIC:
                createBasicBlocks(squares);
                break;
        }
    }

    /**
     * Este método retorna o tabuleiro de jogo.
     *
     * @return Tabuleiro de jogo (Board)
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Este método retorna o jogador.
     *
     * @return Jogador (Player)
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Este método retorna um HashMap com os blocos da ronda.
     *
     * @return Blocos da ronda (HashMap)
     */
    public HashMap<String, Block> getRoundBlocks() {
        return this.roundBlocks;
    }

    /**
     * Este método retorna a pontuação do jogo.
     *
     * @return Pontuação (Score)
     */
    public Score getScore() {
        return this.score;
    }
    
    /**
     * Este método retorna a rodada atual.
     *
     * @return Rodada (int)
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Este método gere cada ronda e retorna o bloco escolhido pelo utilizador.
     *
     * @param input String
     * @return Bloco escolhido (Block)
     */
    public Block play(String input) {
        this.round++;
        String[] commands = input.toUpperCase().split("-", 2);
        String blockLetter = commands[0];
        String column = commands[1].substring(0, 1);
        int row = Integer.valueOf(commands[1].substring(1, 2));
        Block block = this.roundBlocks.get(blockLetter);
        try {
            this.currentBlocks.add(block);
            this.board.addBlock(block, column, row);
            this.roundBlocks.remove(blockLetter);
        } catch (ExceptionManager em) {
            System.out.println(em);
        }
        return block;
    }

    /**
     * Este método recebe um gestor de jogo e adiciona o jogo ao gestor de jogos
     * e guarda-os no ficheiro.
     *
     * @param gameManager GameManager
     * @return Verdadeiro ou false (boolean)
     */
    public boolean saveGame(GameManager gameManager) {
        if (gameManager != null) {
            try {
                if (gameManager.getGameByObject(this) == null) {
                    gameManager.addGame(this);
                }                
                GameFileHandler.saveGameManager("games.bin", gameManager);
                return true;
            } catch (ExceptionManager em) {
                System.out.println(em);
            }
        }
        return false;
    }

    /**
     * Este método gera os blocos da ronda.
     *
     */
    public void generateRoundBlocks() {
        int index = 0;
        String[] letter = {"A", "B", "C"};
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            index = random.nextInt(this.gameModeBlocks.size());
            this.roundBlocks.put(letter[i], this.gameModeBlocks.get(index));
        }
    }

    /**
     * Este método verifica se as condições para obter pontuação são cumpridas e
     * se sim adiciona a pontuação ao score do jogo e retorna true.
     *
     * @param block Block
     * @return Indica se os requisitos de limpeza foram cumpridos (boolean)
     */
    public boolean addPointsToScore(Block block) {
        boolean clearStatus = false;
        int modePoints = block.getBlockGameMode() == GameMode.BASIC ? 1 : 2;
        //System.out.println(block.getSquares().size()+"|"+modePoints);
        this.score.addPoints(block.getSquares().size() * modePoints);
        ArrayList<Integer> rows = this.board.checkRowPoints();
        ArrayList<Integer> columns = this.board.checkColumnPoints();
        ArrayList<Square> squares = this.board.checkSquarePoints();
        if (rows.size() > 0 || columns.size() > 0 || squares.size() > 0) {
            clearStatus = true;
        }
        for (int cRow : rows) {
            this.score.addPoints(this.board.clearRow(cRow) * 4);
        }
        for (int cColumn : columns) {
            this.score.addPoints(this.board.clearColumn(cColumn) * 4);
        }
        for (Square cSquare : squares) {
            this.score.addPoints(10);
            this.score.addPoints(this.board.clearSquare(cSquare) * 4);
        }
        return clearStatus;
    }

    /**
     * Este método retorna uma string com os blocos da ronda.
     *
     * @return Blocos da ronda (String)
     */
    public String getRoundBlocksToDraw() {
        String output = "";
        String[] letter = {"A", "B", "C"};
        output += "\nBlocos a jogar:\n\n";
        for (Map.Entry<String, Block> entry : this.roundBlocks.entrySet()) {
            output += "Bloco " + entry.getKey() + "\n";
            output += entry.getValue().getBlockToDraw();
        }
        return output;
    }

    /**
     * Este método cria os blocos para o modo avançado.
     *
     * @param squares ArrayList(Square)
     */
    public void createAdvancedBlocks(ArrayList<Square> squares) {
        GameMode blockGameMode = GameMode.ADVANCED;
        /* Bloco I1 */
        squares.clear();
        squares.add(new Square(1, 1));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco I2 */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco I3 */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        squares.add(new Square(1, 3));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco L - mínimo */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        squares.add(new Square(2, 1));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco L - máximo */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        squares.add(new Square(1, 3));
        squares.add(new Square(2, 1));
        squares.add(new Square(3, 1));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco T Estendido */
        squares.clear();
        squares.add(new Square(1, 3));
        squares.add(new Square(2, 1));
        squares.add(new Square(2, 2));
        squares.add(new Square(2, 3));
        squares.add(new Square(3, 3));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco Q Estendido */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        squares.add(new Square(1, 3));
        squares.add(new Square(2, 1));
        squares.add(new Square(2, 2));
        squares.add(new Square(2, 3));
        squares.add(new Square(3, 1));
        squares.add(new Square(3, 2));
        squares.add(new Square(3, 3));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
    }

    /**
     * Este método cria os blocos para o modo básico.
     *
     * @param squares ArrayList(Square)
     */
    public void createBasicBlocks(ArrayList<Square> squares) {
        GameMode blockGameMode = GameMode.BASIC;
        /* Bloco I */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        squares.add(new Square(1, 3));
        squares.add(new Square(1, 4));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco Q */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        squares.add(new Square(2, 1));
        squares.add(new Square(2, 2));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco T */
        squares.clear();
        squares.add(new Square(1, 2));
        squares.add(new Square(2, 1));
        squares.add(new Square(2, 2));
        squares.add(new Square(3, 2));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco L */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(1, 2));
        squares.add(new Square(1, 3));
        squares.add(new Square(2, 1));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco J */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(2, 1));
        squares.add(new Square(2, 2));
        squares.add(new Square(2, 3));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco S */
        squares.clear();
        squares.add(new Square(1, 1));
        squares.add(new Square(2, 1));
        squares.add(new Square(2, 2));
        squares.add(new Square(3, 2));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
        /* Bloco Z */
        squares.clear();
        squares.add(new Square(1, 2));
        squares.add(new Square(2, 1));
        squares.add(new Square(2, 2));
        squares.add(new Square(3, 1));
        this.gameModeBlocks.add(new Block(squares, blockGameMode));
    }

    /**
     * Este método devolve uma string com o modo de jogo, os pontos e o jogador.
     *
     * @return Dados do jogo (String)
     */
    @Override
    public String toString() {
        return "GameMode: " + this.gameMode + " | Points: " + this.score.getPoints() + " | Player: " + this.player;
    }
}
