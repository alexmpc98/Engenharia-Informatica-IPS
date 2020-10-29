/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.Player;
import BlockuDoku.Backend.PlayerManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Top10Screen {

    private Scene scene;
    private BorderPane bp;
    private VBox vboxPoints;

    /**
     * Constructor que inicializa o ecrã do top 10 pontuações. 
     * 
     * @param primaryStage Stage
     * @param playerManager PlayerManager
     * @param player Player
     */
    public Top10Screen(Stage primaryStage, PlayerManager playerManager, Player player) {

        this.scene = primaryStage.getScene();

        bp = new BorderPane();

        // Criação da barra de Menu
        MenuBar menuBar = new MenuBar();
        Menu options = new Menu("Opções");
        MenuItem reset = new MenuItem("Reiniciar ranking");
        MenuItem exit = new MenuItem("Sair");

        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                scene = new Scene(new HomeScreen(player.getName(), primaryStage, playerManager, player).buildScreen(), 850, 600);
                primaryStage.setScene(scene);
            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {                
                reset(playerManager);
            }
        });

        Image exitToMenu = new Image("file:assets/exit.png");
        ImageView exitImgView = new ImageView(exitToMenu);
        exitImgView.setFitWidth(15);
        exitImgView.setFitHeight(15);
        exit.setGraphic(exitImgView);

        Image resetImg = new Image("file:assets/reset.png");
        ImageView resetImgView = new ImageView(resetImg);
        resetImgView.setFitWidth(15);
        resetImgView.setFitHeight(15);
        reset.setGraphic(resetImgView);

        options.getItems().addAll(reset);
        options.getItems().addAll(exit);
        menuBar.getMenus().addAll(options);
        bp.setTop(menuBar);

        VBox vboxParent = new VBox();
        VBox vboxHeader = new VBox();
        vboxPoints = new VBox();
        vboxParent.getChildren().addAll(vboxHeader, vboxPoints);

        Text hearderTxt = new Text("Ranking TOP 10:");
        hearderTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        hearderTxt.setFill(Color.DARKBLUE);
        vboxHeader.getChildren().add(hearderTxt);
        for (Player playerF : playerManager.getTopPlayers(10)) {
            Text pointsTxt = new Text(playerF.toString());
            pointsTxt.setFont(Font.font("Verdana", 16));
            pointsTxt.setFill(Color.BLACK);
            this.vboxPoints.setStyle("-fx-word-wrap: break-word;");
            this.vboxPoints.getChildren().add(pointsTxt);
        }

        vboxParent.setAlignment(Pos.CENTER);
        vboxParent.setPadding(new Insets(-100, 0, 0, 0));
        vboxHeader.setAlignment(Pos.CENTER);
        vboxHeader.setPadding(new Insets(-100, 0, 0, 0));
        vboxPoints.setAlignment(Pos.CENTER_LEFT);
        vboxPoints.setPadding(new Insets(0, 0, 0, 310));
        bp.setCenter(vboxParent);
    }
    
    /**
     * Este método reinicia o top 10 de pontuações.
     * 
     * @param playerManager PlayerManager
     */
    public void reset(PlayerManager playerManager) {
        playerManager.resetTopPlayers();
        this.vboxPoints.getChildren().clear();
        for (Player playerF : playerManager.getTopPlayers(10)) {
            Text pointsTxt = new Text(playerF.toString());
            pointsTxt.setFont(Font.font("Verdana", 16));
            pointsTxt.setFill(Color.BLACK);
            this.vboxPoints.setStyle("-fx-word-wrap: break-word;");
            this.vboxPoints.getChildren().add(pointsTxt);
        }
    }

    /**
     * Este método devolve o borderPane com o ecrã do top 10 pontuações.
     * 
     * @return Ecrã do top 10 pontuações (BorderPane)
     */
    public BorderPane buildScreen() {
        return this.bp;
    }

}
