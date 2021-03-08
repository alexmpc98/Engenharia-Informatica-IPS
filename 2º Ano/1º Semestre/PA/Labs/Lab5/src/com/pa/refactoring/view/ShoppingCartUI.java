package com.pa.refactoring.view;

import com.pa.refactoring.model.Product;
import com.pa.refactoring.model.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ShoppingCartUI extends BorderPane {
    private ShoppingCart shoppingCart;
    private ListView<Product> listViewCartContents;
    TextField textFieldProductName;
    TextField textFieldPrice;
    Label labelCost;
    Button buttonAddProduct;
    Label labelEnd;

    public ShoppingCartUI(){
        shoppingCart = new ShoppingCart();
        initComponents();
    }

    public void initComponents(){
        GridPane gridPaneAddProduct = new GridPane();
        textFieldProductName = new TextField();
        buttonAddProduct = new Button("Add");
        Button buttonTerminate = new Button("End");
        labelEnd = new Label();
        textFieldPrice = new TextField();
        labelCost = new Label("Total cost: 0.0 €");

        Label labelAddProduct = new Label("Add products to cart");
        labelAddProduct.setStyle("-fx-font-weight: bold");
        gridPaneAddProduct.add(labelAddProduct, 0, 0);
        gridPaneAddProduct.add(new Label("Name"), 0, 1);
        gridPaneAddProduct.add(textFieldProductName, 1, 1);
        gridPaneAddProduct.add(new Label("Price"), 0, 2);
        gridPaneAddProduct.add(textFieldPrice, 1, 2);
        HBox hBoxAddProductButtons = new HBox(6);
        this.setBottom(labelEnd);
        hBoxAddProductButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxAddProductButtons.setStyle("-fx-padding: 2px 0 0 0");
        gridPaneAddProduct.add(labelCost, 0, 3);
        gridPaneAddProduct.add(hBoxAddProductButtons, 1, 3);
        hBoxAddProductButtons.getChildren().addAll(buttonAddProduct,buttonTerminate);
        buttonAddProduct.setOnAction((ActionEvent e) -> {
            addProduct();
        });
        buttonTerminate.setOnAction((ActionEvent e) -> {
            terminate();
        });
        GridPane gridPaneCartContents = new GridPane();
        Label labelCartContents = new Label("Cart contents");
        labelCartContents.setStyle("-fx-font-weight: bold");
        listViewCartContents = new ListView<>();
        gridPaneCartContents.add(labelCartContents, 0, 0);
        gridPaneCartContents.add(listViewCartContents, 0, 1);
        GridPane.setHgrow(listViewCartContents, Priority.ALWAYS);

        listViewCartContents.getItems().clear();
        for (Product product : shoppingCart.getProductArrayList()) {
            listViewCartContents.getItems().add(product);
        }
        this.setTop(gridPaneAddProduct);
        this.setCenter(gridPaneCartContents);
        this.setStyle("-fx-padding: 5px");
    }

    public Alert sendAlertError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Shopping Cart Error");
        alert.setHeaderText(null);
        return alert;
    }

    public void addProduct(){
        if (textFieldProductName.getText().isEmpty() || textFieldPrice.getText().isEmpty()) {
            sendAlertError().setContentText("empty fields");
            sendAlertError().showAndWait();
        } else {
            try {
                String name = textFieldProductName.getText();
                double price = Double.parseDouble(textFieldPrice.getText());
                shoppingCart.getProductArrayList().add(new Product(name, price));
                labelCost.setText(String.format("Total Cost %.1f €", shoppingCart.getTotal()));
                listViewCartContents.getItems().clear();
                for (Product product : shoppingCart.getProductArrayList()) {
                    listViewCartContents.getItems().add(product);
                }
                textFieldPrice.clear();
                textFieldProductName.clear();
            } catch (NumberFormatException nfe) {
                sendAlertError().setContentText("invalid format");
                sendAlertError().showAndWait();
            }
        }
    }

    public void terminate(){
        shoppingCart.terminate();
        String strEnd;
        if (shoppingCart.isTerminated()) {
            strEnd = String.format("%s Total Cost %.2f Number of Items %d", shoppingCart.getCartDate().getDateStr(),
                    shoppingCart.getTotal(), shoppingCart.getProductArrayList().size());
        } else {
            strEnd = "";
        }
        labelCost.setText(String.format("Current Cost %.1f €", shoppingCart.getTotal()));
        buttonAddProduct.setDisable(true);
        labelEnd.setText(strEnd);
    }
}
