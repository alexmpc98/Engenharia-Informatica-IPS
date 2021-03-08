/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.observerimpl;

import com.pa.patterns.observerimpl.model.*;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Product> productList= generateProductList();
        ShoppingCart cart1 = new ShoppingCart("Bruno's Cart");
        ShoppingCart cart2 = new ShoppingCart("Susana's Cart");
        ShoppingCartTotalCostView costView = new ShoppingCartTotalCostView();
        //cart1.addObservers(costView);
        cart1.addObservers(new ShoppingCartListView());
        //cart2.addObservers(costView);
        cart1.addObservers(new ShoppingCartAlert(100.00));
        cart1.addProduct(productList.get(0));
        cart1.addProduct(productList.get(1));
        cart1.addProduct(productList.get(5));
        cart1.removerProduct(productList.get(0));
        cart2.addProduct(productList.get(5));
        cart2.addProduct(productList.get(1));
        cart2.addProduct(productList.get(6));
    }

    private static List<Product> generateProductList(){

        ArrayList<Product> products= new ArrayList();
        products.add(0,new Product("Powerbank", 30));
        products.add(1,new Product("PSP4", 350));
        products.add(2,new Product("Gum", 0.5));
        products.add(3,new Product("Milk", 1.2));
        products.add(4,new Product("Pencil", 1));
        products.add(5,new Product("Smartphone", 300));
        products.add(6,new Product("BookA", 13));
        products.add(7,new Product("Chocolat", 2.2));
        return products;
    }
    
}
