package com.pa.patterns.memento.view;

import com.pa.patterns.memento.model.Caretaker;
import com.pa.patterns.memento.model.Product;
import com.pa.patterns.memento.model.ShoppingCart;
import com.pa.patterns.memento.model.ShoppingCartController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.Date;

/**
 * @author Alexandre Coelho
 */
public class GUI extends Application {
    private ShoppingCartController shoppingCartController;
    private ListView<Product> listViewCartContents;
    private ComboBox<Date> comboBoxMemento;
    private int columnIndex,comboBoxValue;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPaneMain = new GridPane();
        GridPane gridPaneCartContents = new GridPane();
        ComboBox cBox = new ComboBox();
        // Add product
        GridPane gridPaneAddProduct = new GridPane();
        Label labelAddProduct = new Label("Add products to cart");
        labelAddProduct.setStyle("-fx-font-weight: bold");
        gridPaneAddProduct.add(labelAddProduct, 0, 0);
        gridPaneAddProduct.add(new Label("Name"), 0, 1);
        TextField textFieldProductName = new TextField();
        gridPaneAddProduct.add(textFieldProductName, 1, 1);
        gridPaneAddProduct.add(new Label("Price"), 0, 2);
        TextField textFieldPrice = new TextField();
        gridPaneAddProduct.add(textFieldPrice, 1, 2);
        HBox hBoxAddProductButtons = new HBox();
        Button buttonAddProduct = new Button("Add");
        hBoxAddProductButtons.getChildren().add(buttonAddProduct);
        hBoxAddProductButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxAddProductButtons.setStyle("-fx-padding: 2px 0 0 0");
        gridPaneAddProduct.add(hBoxAddProductButtons, 1, 3);

        buttonAddProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (textFieldProductName.getText().isEmpty() || textFieldPrice.getText().isEmpty()) {
                    error("Missing product information.");
                } else {
                    try {
                        String[] arrOfStr = cBox.getValue().toString().split("-");
                        comboBoxValue = Integer.parseInt(arrOfStr[arrOfStr.length-1].trim());
                        String name = textFieldProductName.getText();
                        double price = Double.parseDouble(textFieldPrice.getText());
                        shoppingCartController.addProduct(name, price);
                        updateProductCartList();
                    } catch (NumberFormatException nfe) {
                        error("Invalid price value.");
                    }
                }
            }
        });
        Button newCart = new Button("Add new cart!");
        columnIndex = 1;
        newCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(columnIndex < 5 && columnIndex >= 1) {
                    Label labelCartContents = new Label("Cart contents");
                    labelCartContents.setStyle("-fx-font-weight: bold");
                    listViewCartContents = new ListView<>();
                    gridPaneCartContents.add(labelCartContents, columnIndex, 0);
                    gridPaneCartContents.add(listViewCartContents, columnIndex, 1);
                    gridPaneCartContents.setId(String.valueOf(columnIndex));
                    cBox.getItems().add("ShoppingCart - " + columnIndex);
                    gridPaneMain.add(gridPaneCartContents, columnIndex-1, 1);
                    Button buttonUndo = new Button("Undo");
                    GridPane.setHgrow(listViewCartContents, Priority.ALWAYS);
                    buttonUndo.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            shoppingCartController.undo();
                            updateProductCartList();
                        }
                    });
                    gridPaneCartContents.add(buttonUndo, columnIndex, 2);
                    columnIndex++;
                    updateProductCartList();
                }
            }
        });
        gridPaneAddProduct.add(newCart,3,2);
        gridPaneAddProduct.add(cBox,3,1);
        gridPaneMain.add(gridPaneAddProduct, 0, 0);
        gridPaneMain.setStyle("-fx-padding: 5px");
        Scene scene = new Scene(gridPaneMain);
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    private void updateProductCartList() {
        listViewCartContents.getItems().clear();
        for (Product product : shoppingCartController.getProducts()) {
            listViewCartContents.getItems().add(product);
        }
    }

    private void error(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Shopping Cart Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
