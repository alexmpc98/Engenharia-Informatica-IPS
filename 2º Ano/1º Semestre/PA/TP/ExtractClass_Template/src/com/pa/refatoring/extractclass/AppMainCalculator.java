package com.pa.refatoring.extractclass;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AppMainCalculator extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CalculatorUI ui = new CalculatorUI();
        Scene scene = new Scene(ui, 300, 250);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
