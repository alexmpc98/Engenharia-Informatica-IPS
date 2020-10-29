/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.Exceptions.ErrorCode;
import BlockuDoku.Backend.Exceptions.LoadException;
import BlockuDoku.Backend.Exceptions.SaveException;
import BlockuDoku.Backend.Player;
import BlockuDoku.Backend.PlayerFileHandler;
import BlockuDoku.Backend.PlayerManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre e Sérgio
 */
public class LoginScreen {

    private Scene scene;
    private GridPane root;
    private PlayerManager playerManager;
    private Player player;  

    /**
     * Constructor que inicializa o ecrã de login. 
     * 
     * @param primaryStage Stage
     */
    public LoginScreen(Stage primaryStage) {
        Button btn = new Button("Entrar");
        btn.setDefaultButton(true);
        btn.setFont(new Font("Arial", 24));
        Label presentationLbl = new Label("Bem vindo ao BlockuDoku");
        presentationLbl.setFont(new Font("Arial", 24));
        Label insertName = new Label("Insira o seu nome:");
        insertName.setFont(new Font("Arial", 15));
        TextField txtField = new TextField();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!txtField.getText().trim().isEmpty()) {
                    String playerName = txtField.getText();
                    if (PlayerFileHandler.fileExists("players", ".bin") != false) {
                        try {
                            playerManager = PlayerFileHandler.loadPlayerManager("players.bin");
                        } catch (LoadException ex) {
                            playerManager = new PlayerManager();
                        }
                        player = playerManager.getPlayerByName(playerName);
                        if (player == null) {
                            player = new Player(playerName);
                            playerManager.addPlayer(player);
                            try {
                                PlayerFileHandler.savePlayerManager("players.bin", playerManager);
                            } catch (SaveException ex) {
                                new DrawAlert(
                                        "Erro de gravação!",
                                        ErrorCode.CANT_SAVE_PLAYER.toString(),
                                        Alert.AlertType.ERROR
                                ).setAlert();
                            }
                        }
                    } else {
                        playerManager = new PlayerManager();
                        player = new Player(playerName);
                        playerManager.addPlayer(player);
                        try {
                            PlayerFileHandler.savePlayerManager("players.bin", playerManager);
                        } catch (SaveException ex) {
                            new DrawAlert(
                                    "Erro de gravação!",
                                    "Erro ao guardar os dados de utilizador.",
                                    Alert.AlertType.ERROR
                            ).setAlert();
                        }
                    }
                    scene = new Scene(new HomeScreen(playerName, primaryStage, playerManager, player).buildScreen(), 750, 450);
                    primaryStage.setScene(scene);
                } else {
                    new DrawAlert(
                            "Campo de Nome vazio!",
                            "Tem de inserir um nome antes de prosseguir.",
                            Alert.AlertType.ERROR
                    ).setAlert();
                }
            }
        });
        btn.setStyle("-fx-background-color: \n"
                + "    #000000,\n"
                + "    linear-gradient(#7ebcea, #2f4b8f),\n"
                + "    linear-gradient(#426ab7, #263e75),\n"
                + "    linear-gradient(#395cab, #223768);\n"
                + "    -fx-background-insets: 0,1,2,3;\n"
                + "    -fx-background-radius: 3,2,2,2;\n"
                + "    -fx-padding: 12 30 12 30;\n"
                + "    -fx-text-fill: white;\n"
                + "    -fx-font-size: 12px;");

        this.root = new GridPane();
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(presentationLbl);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(insertName, txtField);
        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(btn);

        hbox1.setMinWidth(350);
        hbox1.setAlignment(Pos.CENTER);

        hbox2.setPadding(new Insets(10));
        hbox2.setSpacing(10);
        hbox2.setMinWidth(350);
        hbox2.setAlignment(Pos.CENTER);

        hbox3.setPadding(new Insets(10));
        hbox3.setSpacing(40);
        hbox3.setMinWidth(350);
        hbox3.setAlignment(Pos.CENTER);

        this.root.setVgap(20);
        this.root.setPadding(new Insets(10, 10, 10, 10));
        this.root.add(hbox1, 1, 1);
        this.root.add(hbox2, 1, 3);
        this.root.add(hbox3, 1, 4);
        this.root.setAlignment(Pos.CENTER); 
    }

    /**
     * Este método devolve o borderPane com o ecrã de login.
     * 
     * @return Ecrã de login (BorderPane)
     */
    public GridPane buildScreen() {
        return this.root;
    }

}
