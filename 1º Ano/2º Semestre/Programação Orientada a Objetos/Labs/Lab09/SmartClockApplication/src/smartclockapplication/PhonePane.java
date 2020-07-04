/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclockapplication;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Alexandre & Sérgio
 */
public class PhonePane {
    String str;
            
    public PhonePane(Group group){
        build(group);
    }
    private void build(Group group){
       // Nível 4 ********************************************
       Background blackBackground = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));  
       Background blackBackground2 = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));  
       Font f1 = Font.loadFont("file:resources/fonts/DS-DIGI.ttf", 32);
       str = " ";
       Label lbl = new Label();
       lbl.setText(str);
       lbl.setFont(f1);
       lbl.setStyle("-fx-text-fill: GREEN;");
       
       Button button1 = new Button("1");
       button1.setBackground(blackBackground);
       button1.setFont(f1);
       button1.setStyle("-fx-text-fill: YELLOW;");
       button1.setOnMouseClicked((event) -> {
            str += "1";
            lbl.setText(str);
       });
       Button button2 = new Button("2");
       button2.setBackground(blackBackground);
       button2.setFont(f1);
       button2.setStyle("-fx-text-fill: YELLOW;");
       button2.setOnMouseClicked((event) -> {
            str += "2";
            lbl.setText(str);
       });
       Button button3 = new Button("3");
       button3.setBackground(blackBackground);
       button3.setFont(f1);
       button3.setStyle("-fx-text-fill: YELLOW;");
       button3.setOnMouseClicked((event) -> {
            str += "3";
            lbl.setText(str);
       });
       Button button4 = new Button("4");
       button4.setBackground(blackBackground);
       button4.setFont(f1);
       button4.setStyle("-fx-text-fill: YELLOW;");
       button4.setOnMouseClicked((event) -> {
            str += "4";
            lbl.setText(str);
       });
       Button button5 = new Button("5");
       button5.setBackground(blackBackground);
       button5.setFont(f1);
       button5.setStyle("-fx-text-fill: YELLOW;");
       button5.setOnMouseClicked((event) -> {
            str += "5";
            lbl.setText(str);
       });
       Button button6 = new Button("6");
       button6.setBackground(blackBackground);
       button6.setFont(f1);
       button6.setStyle("-fx-text-fill: YELLOW;");
       button6.setOnMouseClicked((event) -> {
            str += "6";
            lbl.setText(str);
       });
       Button button7 = new Button("7");
       button7.setBackground(blackBackground);
       button7.setFont(f1);
       button7.setStyle("-fx-text-fill: YELLOW;");
       button7.setOnMouseClicked((event) -> {
            str += "7";
            lbl.setText(str);
       });
       Button button8 = new Button("8");
       button8.setBackground(blackBackground);
       button8.setFont(f1);
       button8.setStyle("-fx-text-fill: YELLOW;");
       button8.setOnMouseClicked((event) -> {
            str += "8";
            lbl.setText(str);
       });
       Button button9 = new Button("9");
       button9.setBackground(blackBackground);
       button9.setFont(f1);
       button9.setStyle("-fx-text-fill: YELLOW;");
       button9.setOnMouseClicked((event) -> {
            str += "9";
            lbl.setText(str);
       });
       Button button0 = new Button("0");
       button0.setBackground(blackBackground);
       button0.setFont(f1);
       button0.setStyle("-fx-text-fill: YELLOW;");
       button0.setOnMouseClicked((event) -> {
            str += "0";
            lbl.setText(str);
       });
       Button buttonC = new Button("C");
       buttonC.setBackground(blackBackground);
       buttonC.setFont(f1);
       buttonC.setStyle("-fx-text-fill: YELLOW;");
       buttonC.setOnMouseClicked((event) -> {
            str = " ";
            lbl.setText(str);
       });
       Button buttonE = new Button("E");
       buttonE.setBackground(blackBackground);
       buttonE.setFont(f1);
       buttonE.setStyle("-fx-text-fill: YELLOW;");
       buttonE.setOnMouseClicked((event) -> {
            String str2 = " ";
            str2 = " Calling " + str;
            lbl.setText(str2);
       });

       
       HBox hbox = new HBox(lbl);
       hbox.setMinWidth(170);
       hbox.setBackground(blackBackground);
       GridPane grid = new GridPane();
       grid.setLayoutY(30);
       grid.setBackground(blackBackground);
       grid.setPadding(new Insets(0, 7, 0, 7));
       grid.setMaxWidth(1);
       grid.add(button1,1,1);
       grid.add(button2,2,1);
       grid.add(button3,3,1);
       grid.add(button4,1,2);
       grid.add(button5,2,2);
       grid.add(button6,3,2);
       grid.add(button7,1,3);
       grid.add(button8,2,3);
       grid.add(button9,3,3);
       grid.add(buttonC,1,4);
       grid.add(button0,2,4);
       grid.add(buttonE,3,4);
       
       
       group.getChildren().addAll(hbox,grid);
       
    }
    
}
