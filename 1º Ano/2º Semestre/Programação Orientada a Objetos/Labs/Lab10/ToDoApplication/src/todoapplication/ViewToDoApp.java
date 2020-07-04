/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapplication;

import components.TaskTypesCombobox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alexandre
 */
public class ViewToDoApp extends VBox{
    public ViewToDoApp(){
        
    }
    Node createMenu(){
        MenuBar mb = new MenuBar();
        Menu menu1 = new Menu("Opções");
        CheckMenuItem checkMenuItem = new CheckMenuItem("Omitir tarefas concluídas");
        MenuItem menuItem = new MenuItem("Sair");
        
        menuItem.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                Platform.exit();
            }
        }); 
        menu1.getItems().addAll(checkMenuItem,menuItem);
        mb.getMenus().add(menu1);
        return mb;
    }
    Node createBorderPane(){
        BorderPane borderPane = new BorderPane();
        borderPane.setMinHeight(750);
        borderPane.setMinWidth(750);
        
        
        StackPane stack = new StackPane();
        HBox hbox1 = new HBox();
        Label lbl = new Label("Tipos de tarefas");
        TaskTypesCombobox comboBox = new TaskTypesCombobox();
        hbox1.setPadding(new Insets(10));
        hbox1.setSpacing(20);
        hbox1.getChildren().addAll(lbl,comboBox);
        hbox1.setAlignment(Pos.CENTER_LEFT);
        

        HBox hbox2 = new HBox();
        Button button1 = new Button("Adicionar tarefa");
        Button button2 = new Button("Editar tarefa");
        Button button3 = new Button("Eliminar tarefa");
        hbox2.setPadding(new Insets(10));
        hbox2.setSpacing(20);
        hbox2.getChildren().addAll(button1,button2,button3);
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        
        stack.setStyle("-fx-background-color: #ECEBE4;");
        stack.getChildren().addAll(hbox1,hbox2);
        borderPane.setTop(stack);
        
        return borderPane;
    }
    Node createListView(){
        TasksPane tasksPane = new TasksPane();
        return tasksPane;
    }
}
