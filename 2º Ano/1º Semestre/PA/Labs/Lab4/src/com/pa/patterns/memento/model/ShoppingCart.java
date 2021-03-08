package com.pa.patterns.memento.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Alexandre Coelho
 */
public class ShoppingCart implements Originator{
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void reset() {
        products.clear();
    }

    public void removeProduct(Product p) {
        products.remove(p);
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getCost();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.valueOf(products);
    }

    @Override
    public Memento createMemento() {
        MyMemento myMemento = new MyMemento(this.products);
        return myMemento;
    }

    @Override
    public void setMemento(Memento savedState) {
        reset();
        for(Product p : savedState.getState()){
            addProduct(p);
        }
    }

    private class MyMemento implements Memento {
        private List state;
        private Date date;

        public MyMemento(List<Product> stateToSave) {
            this.state = new ArrayList<>(stateToSave); //copy of list
            this.date = new Date();
        }

        @Override
        public List<Product> getState() {
            return state;
        }
    }
}
