package com.pa.patterns.observerimpl.model;

import com.pa.patterns.observerimpl.observer.Observable;
import com.pa.patterns.observerimpl.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoppingCartListView implements Observer {


    @Override
    public void update(Observable obs) {
        if(obs instanceof ShoppingCart){
            ShoppingCart cart = (ShoppingCart)obs;
            System.out.println(cart.getName());
            List<Product> listProducts = new ArrayList<Product>(cart.getProducts());
            for(int i = 0;i< listProducts.size();i++){
                System.out.println(i + " | Product Name - " + listProducts.get(i).getName() + " | Product Cost - " + listProducts.get(i).getCost());
            }
        }
    }
}
