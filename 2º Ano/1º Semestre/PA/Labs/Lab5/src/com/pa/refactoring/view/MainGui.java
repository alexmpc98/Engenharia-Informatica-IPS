package com.pa.refactoring.view;

import com.pa.refactoring.model.Product;
import com.pa.refactoring.model.ShoppingCart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Template para o laboratório de deteçãod e BAD Smells e aplicação de tecnicas de refatoring
 *
 * @author patricia.macedo
 */
public class MainGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane ShoppingCartUI = new ShoppingCartUI();
        Scene scene = new Scene(ShoppingCartUI,400,600);
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


}
