/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclockapplication;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import smartWatch.Clock;

/**
 *
 * @author Alexandre & Sérgio
 */

public class ClockPane {
    Text hourText;
    Text minuteText;
    Text secondsText;
    Font f1 = Font.loadFont("file:resources/fonts/DS-DIGI.ttf", 32);
    
    public ClockPane(Group group){
        build(group);
    }
     private void build(Group group){
        Background blackBackground = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));   
        hourText = new Text();
        minuteText = new Text();
        secondsText = new Text();
        hourText.setFont(f1); 
        minuteText.setFont(f1);  
        secondsText.setFont(f1);
        hourText.setFill(Color.GREEN);
        minuteText.setFill(Color.GREEN);
        secondsText.setFill(Color.GREEN); 
        VBox vbox = new VBox();
        vbox.setMinWidth(170);
        vbox.setMinHeight(270);
        vbox.setBackground(blackBackground);
        vbox.setSpacing(20);
        vbox.setStyle("-fx-padding:65");
        vbox.getChildren().addAll(hourText,minuteText,secondsText);
        group.getChildren().add(vbox);
        getClock();
        refreshClock(); 
     }
     private void getClock(){
        Clock clock = new Clock();
        String hour = clock.getHour() + " H";
        String minutes = clock.getMinute() + " MM";
        String sec = clock.getSecond() + " S";
        hourText.setText(hour); 
        minuteText.setText(minutes);
        secondsText.setText(sec);
     }
     
     public void refreshClock(){
        Clock clock = new Clock();
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(500),
        ae -> getClock()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
     }
}
