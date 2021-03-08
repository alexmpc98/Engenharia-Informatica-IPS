package com.pa.patterns.memento.model;

/**
 * @author Alexandre Coelho
 */
public interface Originator {
    public Memento createMemento();

    public void setMemento(Memento savedState);
}
