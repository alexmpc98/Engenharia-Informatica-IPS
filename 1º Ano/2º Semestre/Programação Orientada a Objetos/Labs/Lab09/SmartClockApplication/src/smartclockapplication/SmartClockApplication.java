/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclockapplication;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre & Sérgio
 */

public class SmartClockApplication extends Application {
    public static final int WINDOW_WIDTH = 270;
    public static final int WINDOW_HEIGHT = 270;
    
    @Override
    public void start(Stage primaryStage){
        Group root = new Group();  
        createWatches(root);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.getIcons().add(new Image("file:resources/viber.png"));
        primaryStage.setTitle("Phone Copy");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
    
    public void createWatches(Group group){
        // Nível 2 ********************************************
        SmartWatchPane smartWatch = new SmartWatchPane(group);    
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            launch(args);
    } 
}
