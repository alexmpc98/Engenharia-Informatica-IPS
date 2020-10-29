/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.Player;
import BlockuDoku.Backend.PlayerManager;
import BlockuDoku.Backend.Score;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
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
public class PersonalPointsScreen {
    
    private BorderPane bp;
    private Scene scene;
    
    /**
     * Constructor que inicializa o ecrã de pontuações pessoais. 
     * 
     * @param primaryStage Stage
     * @param playerManager PlayerManager
     * @param player Player
     */
    public PersonalPointsScreen(Stage primaryStage, PlayerManager playerManager, Player player) {
        bp = new BorderPane();
        
        // Criação da barra de Menu
        MenuBar menuBar = new MenuBar();
        Menu options = new Menu("Opções");
        MenuItem exit = new MenuItem("Sair");

        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                scene = new Scene(new HomeScreen(player.getName(), primaryStage, playerManager, player).buildScreen(), 850, 600);
                primaryStage.setScene(scene);
            }
        });

        Image exitToMenu = new Image("file:assets/exit.png");
        ImageView exitImgView = new ImageView(exitToMenu);
        exitImgView.setFitWidth(15);
        exitImgView.setFitHeight(15);
        exit.setGraphic(exitImgView);

        options.getItems().addAll(exit);
        menuBar.getMenus().addAll(options);
        bp.setTop(menuBar);

        BorderPane borderPaneParent = new BorderPane();
        VBox vboxHeader = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        VBox vboxPoints = new VBox();
        scrollPane.setContent(vboxPoints);

        Text hearderTxt = new Text("Hitórico de pontuações:");
        hearderTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        hearderTxt.setFill(Color.DARKBLUE);
        vboxHeader.getChildren().add(hearderTxt);
        ArrayList<Score> scoreList = player.getScoreList();
        if (scoreList.size() > 0) {
            for (Score score : scoreList) {
                Text pointsTxt = new Text(score.toString());
                pointsTxt.setFont(Font.font("Verdana", 16));
                pointsTxt.setFill(Color.BLACK);
                vboxPoints.setStyle("-fx-word-wrap: break-word;");
                vboxPoints.getChildren().add(pointsTxt);
            }
            vboxPoints.setPadding(new Insets(0, 0, 0, 220));
        } else {
            Text pointsTxt = new Text("Sem pontuações anteriores!");
            pointsTxt.setFont(Font.font("Verdana", 16));
            pointsTxt.setFill(Color.BLACK);
            vboxPoints.setStyle("-fx-word-wrap: break-word;");
            vboxPoints.getChildren().add(pointsTxt);
            vboxPoints.setPadding(new Insets(0, 0, 0, 310));
        }

        vboxHeader.setAlignment(Pos.CENTER);
        vboxHeader.setPadding(new Insets(20, 0, 20, 0));

        vboxPoints.setAlignment(Pos.CENTER_LEFT);
        
        scrollPane.setStyle("-fx-background-color:transparent;");
        borderPaneParent.setTop(vboxHeader);
        borderPaneParent.setCenter(scrollPane);
        bp.setCenter(borderPaneParent);
    }

    /**
     * Este método devolve o borderPane com o ecrã de pontuações pessoais.
     * 
     * @return Ecrã de pontuações pessoais (BorderPane)
     */
    public BorderPane buildScreen() {
        return this.bp;
    }

}
