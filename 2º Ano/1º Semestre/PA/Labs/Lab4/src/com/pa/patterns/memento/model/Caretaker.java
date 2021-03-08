package com.pa.patterns.memento.model;

import java.util.Stack;

public class Caretaker {
    private Memento memento;
    private Originator cart;
    //private Stack<Memento> stack;

    public Caretaker(Originator cart){
        this.memento = null;
        this.cart = cart;
        //this.stack = new Stack<>();
    }

    public void saveState(){
        this.memento = cart.createMemento();
        //stack.push(this.memento);
    }

    public void restoreState(){
        if(this.memento == null){
            throw new NoMementoException("There is no Memento!");
        }
        //stack.pop()
        cart.setMemento(this.memento);
    }
}