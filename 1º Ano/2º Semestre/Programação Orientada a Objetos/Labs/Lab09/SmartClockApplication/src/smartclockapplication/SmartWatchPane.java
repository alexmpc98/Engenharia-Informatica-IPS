/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclockapplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import smartWatch.Mode;

/**
 *
 * @author Alexandre & Sérgio
 */
public class SmartWatchPane extends BorderPane {
    Mode mode;
    
    public SmartWatchPane(Group group){
        this.mode = Mode.PHONE;
        build(group);
    }
    private void build(Group group){
        Background blackBackground = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));  
        Font f1 = Font.loadFont("file:resources/fonts/DS-DIGI.ttf", 20);
        Button button1 = new Button("Set\nMode");
        button1.setMinHeight(270);
        button1.setBackground(blackBackground);
        button1.setLayoutX(170);
        button1.setFont(f1);
        button1.setAlignment(Pos.BASELINE_CENTER);
        button1.setStyle("-fx-text-fill: YELLOW;-fx-pref-width: 100px;");
        button1.setOnMouseClicked((event) -> {
            // Nível 3 ********************************************
            setMode();
            if(getMode() == Mode.PHONE){
                createPhonePane(group);
            }
            else
                createClockPane(group);    
        });
        group.getChildren().add(button1);
    }
    public void setMode(){
        if(getMode() == Mode.PHONE)
            this.mode = Mode.CLOCK;
        else
            this.mode = Mode.PHONE;
    }
    
    public Mode getMode(){
         return this.mode;
    }
    private void createClockPane(Group group){
        ClockPane clockPane = new ClockPane(group);
    }
     private void createPhonePane(Group group){
        PhonePane phonePane = new PhonePane(group);
    }
}
