/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.observerimpl.model;

import com.pa.patterns.observerimpl.observer.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



/**
 *
 * @author brunomnsilva
 */
public class ShoppingCart extends Subject {
    
    private String name;
    private List<Product> products;

    public ShoppingCart(String name) {
        this.name = name;
        products = new ArrayList<>();
    }
    
    public void addProduct(Product p) {
        products.add(p);
        notifyObservers(this);
    }
    
    public void removerProduct(Product p) {
        products.remove(p);
        this.notifyObservers(this);
    }
    public Product lastProductAdded(){
          return products.get(products.size()-1);

    }
    public String getName() {
        return name;
    }
    
    public Collection<Product> getProducts() {
        return products;
    }

    public float getTotal() {
        float total=0.0f;
        for(Product p: products)
            total+=p.getCost();
        return total;
    }
}
