/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Screen extends Application {

    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        
        this.scene = new Scene(new LoginScreen(primaryStage).buildScreen(), 650, 450);

        primaryStage.getIcons().add(new Image("file:assets/blockuDoku.jpg"));
        primaryStage.setTitle("BlockuDoku!");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
  

    /**
     * Este é o método main do screen.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
