/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.observerimpl.model;

import com.pa.patterns.observerimpl.observer.Observable;
import com.pa.patterns.observerimpl.observer.Observer;

import java.util.Collection;



/**
 *
 * @author brunomnsilva
 */
public class ShoppingCartTotalCostView implements Observer {
    @Override
    public void update(Observable arg) {
        if(arg instanceof ShoppingCart) {
            
            ShoppingCart cart = (ShoppingCart)arg;
            String name = cart.getName();
            double total = 0;
            System.out.printf("(%s) total cost: %.2f \n", name,  cart.getTotal());
        }
    }
    
}
