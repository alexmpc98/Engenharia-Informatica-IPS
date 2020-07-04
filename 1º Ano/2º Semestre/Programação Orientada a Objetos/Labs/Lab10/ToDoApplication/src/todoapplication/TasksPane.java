/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapplication;

import Model.Task;
import Model.Tasks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Alexandre
 */
public class TasksPane extends ListView<Tasks>{
    private ObservableList<Task> tasksList;
    
    public TasksPane(Tasks tasks){
        this.tasksList = FXCollections.observableArrayList(tasks);
    }
    
    private Node drawCell(Task item){
        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(10));
        hbox1.setSpacing(20);
        CheckBox checkBox = new CheckBox();
        Label courseUnitLbl = new Label();
        Label descriptionLbl = new Label();
        Label dateLbl = new Label();
        Label priorityLbl = new Label();
        
        courseUnitLbl.setText(item.getCourseUnit());
        descriptionLbl.setText(item.getDescription());
        dateLbl.setText(item.getDateTime());
        priorityLbl.setText(item.getPriority().toString());

        //hbox1.getChildren().addAll(CheckBox);
        return hbox1;
    }
}
