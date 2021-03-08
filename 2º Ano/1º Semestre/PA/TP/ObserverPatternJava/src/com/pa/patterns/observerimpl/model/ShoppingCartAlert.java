package com.pa.patterns.observerimpl.model;

import com.pa.patterns.observerimpl.observer.Observable;
import com.pa.patterns.observerimpl.observer.Observer;

public class ShoppingCartAlert implements Observer {
    private double maxValue;

    public ShoppingCartAlert(double maxValue){
        this.maxValue = maxValue;
    }

    @Override
    public void update(Observable obs) {
        if(obs instanceof ShoppingCart){
            ShoppingCart cart = (ShoppingCart)obs;
            String name = cart.getName();

            Product last = cart.lastProductAdded();
            if(last.getCost() > maxValue){
                System.out.println(String.format("ALERT! The product (%s | %.2f) has exceeded the maximum configured value %.2f", last.getName(), last.getCost(), this.maxValue));
            }
        }
    }
}
