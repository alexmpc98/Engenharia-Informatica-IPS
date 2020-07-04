/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapplication;

import Model.PriorityLevel;
import Model.Task;
import Model.TaskTypes;
import Model.Tasks;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre
 */
public class ToDoApplication extends Application {
    Tasks task;
    Task task1;
    Task task2;
    Task task3;
    public static final int WINDOW_WIDTH = 750;
    public static final int WINDOW_HEIGHT = 450;
            
    @Override
    public void start(Stage primaryStage) { 
        Group root = new Group();
        this.task = new Tasks();
        
        this.task1 = new Task(TaskTypes.PROJECT,"POO","Projeto de POO",PriorityLevel.HIGH,null,false);
        this.task2 = new Task(TaskTypes.PROJECT,"ATAD","Projeto de ATAD",PriorityLevel.MEDIUM,null,false);
        this.task3 = new Task(TaskTypes.PROJECT,"CD","Projeto de CD",PriorityLevel.LOW,null,false); 
        this.task.add(task1);
        this.task.add(task2);
        this.task.add(task3);
        
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        ViewToDoApp viewToDoApp = new ViewToDoApp();
        
        Node menu = viewToDoApp.createMenu();
        Node borderPane = viewToDoApp.createBorderPane();
        //Node listView = viewToDoApp.createListView();
        viewToDoApp.getChildren().addAll(menu,borderPane);
        
        root.getChildren().add(viewToDoApp);
        
        
        primaryStage.setTitle("Lista de Tarefas Académicas");
        primaryStage.getIcons().add(new Image("file:resources/uni.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
