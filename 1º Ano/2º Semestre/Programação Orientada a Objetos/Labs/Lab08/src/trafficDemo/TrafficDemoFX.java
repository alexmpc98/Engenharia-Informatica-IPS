package trafficDemo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TrafficDemoFX extends Application {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 700;
    public static final double CENTER_X = WINDOW_WIDTH / 2;
    public static final double CENTER_Y = WINDOW_HEIGHT / 2 - 60;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        roundaboutTraffic(root);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Roundabout Traffic Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void putTitle(Group group){
        Font verdana = Font.font("Verdana", FontWeight.BOLD, 20);
        Text title = new Text(CENTER_X - 190, 35, String.format("%s", "Circulação correta numa rotunda"));
        title.setFont(verdana);
        title.setFill(Color.DARKGREY);
        group.getChildren().add(title);
    }
    public void createCarsFirstExit(Group group){
        Car orangeCar1 = new Car(CENTER_X + 180, CENTER_Y - 50, Color.DARKORANGE);
        orangeCar1.rotate(-90);
        orangeCar1.signalRightTurn();
        orangeCar1.addTo(group);
        
        Car orangeCar2 = new Car(CENTER_X + 70, CENTER_Y - 105, Color.DARKORANGE);
        orangeCar2.addTo(group);
        orangeCar2.signalRightTurn();
        orangeCar2.rotate(-45);
        
        Car orangeCar3 = new Car(CENTER_X + 5, CENTER_Y - 205, Color.DARKORANGE);
        orangeCar3.addTo(group);
        orangeCar3.signalRightTurn();
    }
    public void createCarsSecondExit(Group group){
        Car redCar1 = new Car(CENTER_X - 200, CENTER_Y - 5, Color.DARKRED);
        redCar1.addTo(group);
        redCar1.signalLeftTurn();
        redCar1.rotate(90);
        
        Car redCar2 = new Car(CENTER_X - 45, CENTER_Y + 45, Color.DARKRED);
        redCar2.addTo(group);
        redCar2.signalRightTurn();
        redCar2.rotate(125);
        
        Car redCar3 = new Car(CENTER_X + 85, CENTER_Y + 45, Color.DARKRED);
        redCar3.addTo(group);
        redCar3.signalRightTurn();
        redCar3.rotate(40);
        
        Car redCar4 = new Car(CENTER_X + 180, CENTER_Y - 5, Color.DARKRED);
        redCar4.addTo(group);
        redCar4.signalRightTurn();
        redCar4.rotate(90);
    }
    public void createCarsThirdExit(Group group){
        Car greenCar1 = new Car(CENTER_X + 5, CENTER_Y + 150, Color.SEAGREEN);
        greenCar1.addTo(group);
        greenCar1.signalLeftTurn();

        Car greenCar2 = new Car(CENTER_X + 65, CENTER_Y, Color.SEAGREEN);
        greenCar2.addTo(group);
        greenCar2.signalLeftTurn();
        greenCar2.rotate(20);
        
        Car greenCar3 = new Car(CENTER_X - 80, CENTER_Y - 120, Color.SEAGREEN);
        greenCar3.addTo(group);
        greenCar3.signalRightTurn();
        greenCar3.rotate(-125);
        
        Car greenCar4 = new Car(CENTER_X - 200, CENTER_Y - 45, Color.SEAGREEN);
        greenCar4.addTo(group);
        greenCar4.signalRightTurn();
        greenCar4.rotate(-90);
    }
    public void createButtons(Group group){
        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        button1.setText("1ª saída: tomar a via mais à direita.");
        button2.setText("2ª saída: tomar a via da esquerda, a seguir à 1ª saída passar para a via mais à direita.");
        button3.setText("3ª saída: tomar a via da esquerda, a seguir à 2ª saída passar para a via mais à direita.");
        button1.setStyle("-fx-text-fill: DARKORANGE; ");
        button1.setLayoutX(180);
        button1.setLayoutY(570);
        button2.setStyle("-fx-text-fill: DARKRED;");
        button2.setLayoutX(180);
        button2.setLayoutY(600);
        button3.setStyle("-fx-text-fill: SEAGREEN; ");
        button3.setLayoutX(180);
        button3.setLayoutY(630);
        button1.setMinWidth(460);
        button1.setOnAction((event) -> {
            createCarsFirstExit(group);
        });
        button2.setOnAction((event) -> {
            createCarsSecondExit(group);
        });
        button3.setOnAction((event) -> {
            createCarsThirdExit(group);
        });
        group.getChildren().add(button1);
        group.getChildren().add(button2);
        group.getChildren().add(button3);
       
    }
    
    public void roundaboutTraffic(Group group){
        //NIVEL 1 ***************************************
        Roundabout roundabout = new Roundabout(CENTER_X, CENTER_Y, Color.SILVER);
        roundabout.addTo(group);
        //NIVEL 2 ***************************************
        putTitle(group);
        //NIVEL 3 ***************************************
        //createCarsFirstExit(group);
        //createCarsSecondExit(group);
        //NIVEL 4 ***************************************
        //createCarsThirdExit(group);
        
        //NIVEL 5 ***************************************
        createButtons(group);
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}
