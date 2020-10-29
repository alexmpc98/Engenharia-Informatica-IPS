/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.Block;
import BlockuDoku.Backend.Exceptions.PlacingBlockException;
import BlockuDoku.Backend.Game;
import BlockuDoku.Backend.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.animation.FadeTransition;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Alexandre e Sérgio
 */
public class DrawBoard {

    private GridPane board;
    private ArrayList<Character> boardHeaders;
    private Square selectedPosition;
    private ObservableList<String[]> data;
    private boolean animate;

    /**
     * Constructor que inicializa um novo desenho do tabuleiro (DrawBoard).
     *
     * @param numberOfColumns int
     * @param numberOfRows int
     * @param game Game
     * @param blockuDokuBlocksToPlay DrawBlocksToPlay
     * @param pointsProperty IntegerProperty
     */
    public DrawBoard(int numberOfColumns, int numberOfRows, Game game, DrawBlocksToPlay blockuDokuBlocksToPlay, IntegerProperty pointsProperty) {
        this.board = new GridPane();
        this.animate = false;
        this.boardHeaders = new ArrayList<>();
        this.data = FXCollections.observableArrayList();
        this.data.addAll(Arrays.asList(game.getBoard().getBoard()));
        this.data.addListener((ListChangeListener<String[]>) c -> {
            while (c.next()) {
                drawBlocks(numberOfColumns, numberOfRows, this.data, this.animate);
            }
        });

        //Adicionar todos os headers possiveis
        for (char chr = 'a'; chr <= 'z'; chr++) {
            boardHeaders.addAll(Collections.nCopies(1, Character.toUpperCase(chr)));
        }
        // Criar o tabuleiro com botões em branco
        for (int row = 2; row < numberOfColumns + 2; row++) {
            for (int col = 2; col < numberOfRows + 2; col++) {
                Button newBt = new Button();
                // OnClick no tabuleiro  
                newBt.setMinWidth(50);
                newBt.setMinHeight(30);
                newBt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (blockuDokuBlocksToPlay.getClicked() != null) {
                            try {
                                selectedPosition = new Square(board.getColumnIndex(newBt) - 2, board.getRowIndex(newBt) - 1);
                                Block roundBlock = game.play(blockuDokuBlocksToPlay.getSelectedBlockLetter(), selectedPosition);
                                blockuDokuBlocksToPlay.getBlockGroup().getChildren().remove(blockuDokuBlocksToPlay.getClicked());
                                if (game.addPointsToScore(roundBlock)) {
                                    animate = true;
                                } else {
                                    animate = false;
                                }
                                if (game.getRoundBlocks().size() <= 0) {
                                    game.incrementRound();
                                    blockuDokuBlocksToPlay.resetClicked();
                                    game.generateRoundBlocks();
                                    blockuDokuBlocksToPlay.createBlockSpace(3);
                                }
                                pointsProperty.set(game.getScore().getPoints());
                                data.removeAll();
                                data.addAll(game.getBoard().getBoard());
                            } catch (PlacingBlockException ex) {
                                new DrawAlert(
                                        "Jogada impossivel!",
                                        "Não é possivel posicionar o bloco.",
                                        Alert.AlertType.ERROR
                                ).setAlert();
                            }
                        }
                    }
                });
                newBt.setStyle("-fx-background-color: white;-fx-border-color:black;-fx-opacity: 1.0");
                board.add(newBt, row, col);
            }
        }
        board.setPadding(new Insets(50, 0, 0, 50));
        drawBlocks(numberOfColumns, numberOfRows, this.data, false);
    }

    /**
     * Este método devolve o desenho do tabuleiro.
     *
     * @return Desenho do tabuleiro (GridPane)
     */
    public GridPane getBoardPane() {
        return this.board;
    }

    /**
     * Este método devolve a posição selecionada do tabuleiro.
     *
     * @return Posição selecionada (Square)
     */
    public Square getSelectedPosition() {
        return this.selectedPosition;
    }

    /**
     * Este método recebe o número de colunas e desenha as letras respetivas na
     * janela.
     *
     * @param numberOfHeaders int
     */
    public void addHeaders(int numberOfHeaders) {
        for (int i = 1; i <= numberOfHeaders; i++) {
            Text txt = new Text();
            txt.setText(String.valueOf(this.boardHeaders.get(i - 1)));
            txt.setFont(Font.font("Verdana", 20));
            getBoardPane().add(txt, i + 1, 1);
            getBoardPane().setHalignment(txt, HPos.CENTER);
        }
    }

    /**
     * Este método recebe o número de linhas e desenha os numeros na janela.
     *
     * @param numbersOfRows int
     */
    public void addRowNumbers(int numbersOfRows) {
        // Aplicar os números ao tabuleiro dinamicamente
        for (int i = 1; i <= numbersOfRows; i++) {
            Text numberTxt = new Text();
            numberTxt.setText(String.valueOf(i));
            numberTxt.setFont(Font.font("Verdana", 20));
            getBoardPane().add(numberTxt, 1, i + 1);
            getBoardPane().setHalignment(numberTxt, HPos.CENTER);
            getBoardPane().setMargin(numberTxt, new Insets(3, 7, 3, 7));
        }
    }

    /**
     * Este método recebe um gridpane, uma coluna e uma linha e devolve o node
     * que se encontra nessa posição.
     *
     * @param gridPane Gridpane
     * @param col int
     * @param row int
     * @return Node (Node)
     */
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * Este método recebe um gridpane, uma coluna e uma linha e devolve o node
     * que se encontra nessa posição.
     *
     * @param gridPane Gridpane
     * @param col int
     * @param row int
     * @return Node (Node)
     */
    private void drawBlocks(int numberOfColumns, int numberOfRows, ObservableList<String[]> data, boolean animate) {
        for (int row = 2; row < numberOfColumns + 2; row++) {
            for (int col = 2; col < numberOfRows + 2; col++) {
                Button button = ((Button) getNodeFromGridPane(board, col, row));
                if (data.get(row - 2)[col - 2].equals("#")) {
                    if (animate == true) {
                        FadeTransition transition = new FadeTransition(Duration.millis(1000.0), button);
                        transition.setFromValue(0.0);
                        transition.setToValue(1.0);
                        transition.setCycleCount(1);
                        transition.setAutoReverse(false);
                        transition.play();
                    }
                    button.setStyle("-fx-background-color: blue;-fx-border-color:black;-fx-opacity: 1.0");
                    button.setDisable(true);
                } else {
                    button.setStyle("-fx-background-color: white;-fx-border-color:black;-fx-opacity: 1.0");
                    button.setDisable(false);
                }
            }
        }
    }

    /**
     * Este método adicionar um string ao header do tabuleiro.
     *
     * @param header String
     */
    public void addToHeader(String header) {
        if (header.length() == 1) {
            this.boardHeaders.add(header.charAt(0));
        }
    }

}
