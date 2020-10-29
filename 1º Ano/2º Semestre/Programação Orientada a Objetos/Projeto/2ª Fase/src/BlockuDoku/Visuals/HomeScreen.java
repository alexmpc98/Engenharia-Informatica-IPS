/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.Exceptions.LoadException;
import BlockuDoku.Backend.Exceptions.SaveException;
import BlockuDoku.Backend.Game;
import BlockuDoku.Backend.GameFileHandler;
import BlockuDoku.Backend.GameMode;
import BlockuDoku.Backend.PlayerFileHandler;
import BlockuDoku.Backend.PlayerManager;
import BlockuDoku.Backend.Player;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre e Sérgio
 */
public class HomeScreen {

    private GridPane root;
    private Scene scene;
    private Game game;
    private File file;
    
    /**
     * Constructor que inicializa o ecrã de inicio. 
     * 
     * @param name String
     * @param primaryStage Stage
     * @param playerManager PlayerManager
     * @param player Player
     */
    public HomeScreen(String name, Stage primaryStage, PlayerManager playerManager, Player player) {
        this.root = new GridPane();
        this.root.setVgap(20);
        this.root.setPadding(new Insets(10, 10, 10, 10));

        Text nameTxt = new Text(name);
        Text welcomeTxt = new Text("Bem vindo ");
        Text gladTxt = new Text(", gosto em vê-lo/a!");

        welcomeTxt.setFill(Color.CORNFLOWERBLUE);
        welcomeTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        gladTxt.setFill(Color.CORNFLOWERBLUE);
        gladTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        nameTxt.setFill(Color.DARKBLUE);
        nameTxt.setFont(Font.font("Verdana", 18));

        TextFlow textFlowPane = new TextFlow();
        textFlowPane.getChildren().addAll(welcomeTxt, nameTxt, gladTxt);

        ImageView img1 = new ImageView();
        ImageView img2 = new ImageView();
        ImageView img3 = new ImageView();
        ImageView img4 = new ImageView();
        ImageView img5 = new ImageView();

        Image playGame = new Image("file:assets/playgame.png");
        Image playSavesGame = new Image("file:assets/playsavedgame.png");
        Image pointsImg = new Image("file:assets/points.png");
        Image ranking = new Image("file:assets/ranking.png");
        Image exitIMG = new Image("file:assets/exit.png");

        img1.setImage(playGame);
        img2.setImage(playSavesGame);
        img3.setImage(pointsImg);
        img4.setImage(ranking);
        img5.setImage(exitIMG);

        Button startNewGame = new Button("Começar novo jogo", img1);
        Button openGame = new Button("Abrir um jogo guardado", img2);
        Button showPersonalPoints = new Button("As minhas pontuações", img3);
        Button topTen = new Button("Ranking (Top 10)", img4);
        Button exit = new Button("Sair do jogo", img5);

        startNewGame.setFont(Font.font("Verdana", 14));
        openGame.setFont(Font.font("Verdana", 14));
        showPersonalPoints.setFont(Font.font("Verdana", 14));
        topTen.setFont(Font.font("Verdana", 14));
        exit.setFont(Font.font("Verdana", 14));

        startNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameMode selection = gameModeDialog();
                if (selection != null) {
                    Game game = new Game(selection, player);
                    if (game.getRoundBlocks().size() <= 0) {
                        game.generateRoundBlocks();
                    }
                    scene = new Scene(new GameScreen(primaryStage, game, player, playerManager).buildScreen(), 850, 600);
                    primaryStage.setScene(scene);
                }
            }
        });

        openGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                file = new FileManager(primaryStage, true).getFile();
                if (file != null) {
                    try {
                        game = GameFileHandler.loadGame(file);
                        game.setPlayer(player);
                        scene = new Scene(new GameScreen(primaryStage, game, player, playerManager).buildScreen(), 850, 600);
                        primaryStage.setScene(scene);
                    } catch (LoadException ex) {
                        new DrawAlert(
                                "Sem jogos guardados!",
                                "Não possui jogos guardados.",
                                Alert.AlertType.ERROR
                        ).setAlert();
                    }
                }
            }
        });

        topTen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene = new Scene(new Top10Screen(primaryStage, playerManager, player).buildScreen(), 850, 600);
                primaryStage.setScene(scene);
            }
        });

        showPersonalPoints.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene = new Scene(new PersonalPointsScreen(primaryStage, playerManager, player).buildScreen(), 850, 600);
                primaryStage.setScene(scene);
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    PlayerFileHandler.savePlayerManager("players.bin", playerManager);
                } catch (SaveException ex) {
                    new DrawAlert(
                            "Erro de gravação!",
                            "Erro ao guardar os dados de utilizador.",
                            Alert.AlertType.ERROR
                    ).setAlert();
                }
                scene = new Scene(new LoginScreen(primaryStage).buildScreen(), 650, 450);
                primaryStage.setScene(scene);
            }
        });

        HBox hbox1 = new HBox();
        hbox1.setMinWidth(350);
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(startNewGame, openGame);

        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(10));
        hbox2.setSpacing(10);
        hbox2.setMinWidth(350);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(showPersonalPoints, topTen, exit);

        HBox hbox3 = new HBox();
        hbox3.setMinWidth(350);
        hbox3.setAlignment(Pos.CENTER);
        hbox3.getChildren().add(textFlowPane);

        this.root.add(hbox1, 1, 3);
        this.root.add(hbox2, 1, 4);
        this.root.add(hbox3, 1, 1);
        this.root.setAlignment(Pos.CENTER);
    }

    /**
     * Este método devolve o Gridpane com o ecrã de inicio.
     * 
     * @return Ecrã de inicio (GridPane)
     */
    public GridPane buildScreen() {
        return this.root;
    }
    
    
    /**
     * Este método cria um dialogo de escolha de modo de jogo.
     *
     * @return Modo de jogo (GameMode)
     */
    private GameMode gameModeDialog() {
        ChoiceDialog<String> dialogo;
        String[] gameModes = {"Básico", "Avançado"};
        List<String> dadosDialogo = Arrays.asList(gameModes);
        GameMode output = null;
        dialogo = new ChoiceDialog<String>(dadosDialogo.get(0), dadosDialogo);
        dialogo.setTitle("Escolher modo de jogo");
        dialogo.setHeaderText("Escolha o modo de jogo");
        Optional<String> resultado = dialogo.showAndWait();
        if (resultado.isPresent()) {
            output = resultado.get() == "Básico" ? GameMode.BASIC : GameMode.ADVANCED;
        }
        return output;
    }
    
}
