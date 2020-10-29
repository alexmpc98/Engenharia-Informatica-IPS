/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.Exceptions.SaveException;
import BlockuDoku.Backend.Game;
import BlockuDoku.Backend.GameFileHandler;
import BlockuDoku.Backend.Player;
import BlockuDoku.Backend.PlayerFileHandler;
import BlockuDoku.Backend.PlayerManager;
import java.io.File;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre e Sérgio
 */
public class GameScreen {
    
    private BorderPane bp;
    private Scene scene;

    /**
     * Constructor que inicializa o ecrã de jogo. 
     * 
     * @param primaryStage Stage
     * @param game Game
     * @param player Player
     * @param playerManager PlayerManager
     */
    public GameScreen(Stage primaryStage, Game game, Player player, PlayerManager playerManager) {
        bp = new BorderPane();
        GridPane grid = new GridPane();
        DrawBlocksToPlay blockuDokuBlocksToPlay = new DrawBlocksToPlay(game);
        blockuDokuBlocksToPlay.createBlockSpace(3);

        IntegerProperty pointsProperty = new SimpleIntegerProperty();
        pointsProperty.set(game.getScore().getPoints());

        // Criação da barra de Menu
        MenuBar menuBar = new MenuBar();
        Menu options = new Menu("Opções");
        MenuItem save = new MenuItem("Guardar");
        MenuItem exit = new MenuItem("Sair");

        save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                File file = new FileManager(primaryStage, false).getFile();
                if (file != null) {
                    try {
                        GameFileHandler.saveGame(file, game);
                        if (playerManager.verifyIfScoreExist(game.getScore(), game.getPlayer()) != true) {
                            game.getPlayer().addScore(game.getScore());
                            try {
                                PlayerFileHandler.savePlayerManager("players.bin", playerManager);
                            } catch (SaveException ex) {
                                new DrawAlert(
                                        "Erro de gravação!",
                                        "Erro ao guardar a pontuação do jogo.",
                                        Alert.AlertType.ERROR
                                ).setAlert();
                            }
                        }
                    } catch (SaveException ex) {
                        new DrawAlert(
                                "Erro de gravação!",
                                "Erro ao guardar o jogo.",
                                Alert.AlertType.ERROR
                        ).setAlert();
                    }
                }
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                scene = new Scene(new HomeScreen(player.getName(), primaryStage, playerManager, player).buildScreen(), 850, 600);
                primaryStage.setScene(scene);
            }
        });

        Image saveGame = new Image("file:assets/playsavedgame.png");
        ImageView saveGameView = new ImageView(saveGame);
        saveGameView.setFitWidth(15);
        saveGameView.setFitHeight(15);
        save.setGraphic(saveGameView);

        Image exitToMenu = new Image("file:assets/exit.png");
        ImageView exitImgView = new ImageView(exitToMenu);
        exitImgView.setFitWidth(15);
        exitImgView.setFitHeight(15);
        exit.setGraphic(exitImgView);

        options.getItems().addAll(save, exit);
        menuBar.getMenus().addAll(options);
        bp.setTop(menuBar);

        // Criação do tabuleiro do BlockuDoku
        DrawBoard BlockuDokuBoard = new DrawBoard(9, 9, game, blockuDokuBlocksToPlay, pointsProperty);
        BlockuDokuBoard.addHeaders(9);
        BlockuDokuBoard.addRowNumbers(9);
        bp.setCenter(BlockuDokuBoard.getBoardPane());

        // Pontos 
        HBox hbox1 = new HBox();
        pointsProperty.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                hbox1.getChildren().clear();
                Text pointsTxt = new Text("Pontos: " + String.valueOf(game.getScore().getPoints()));
                pointsTxt.setFont(Font.font("Verdana", 18));
                pointsTxt.setFill(Color.DARKBLUE);
                hbox1.getChildren().add(pointsTxt);
            }
        });

        String actualPoints = "Pontos: " + String.valueOf(game.getScore().getPoints());
        Text pointsTxt = new Text(actualPoints);
        pointsTxt.setFont(Font.font("Verdana", 18));
        pointsTxt.setFill(Color.DARKBLUE);
        hbox1.getChildren().add(pointsTxt);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(-100, 0, 0, 40));
        grid.setAlignment(Pos.CENTER);

        bp.setLeft(hbox1);
        bp.setBottom(blockuDokuBlocksToPlay.getBlockGroup());
    }
    
    /**
     * Este método devolve o BorderPane com o ecrã de jogo.
     * 
     * @return Ecrã de jogo (BorderPane)
     */
    public BorderPane buildScreen() {
        return this.bp;
    }

}
