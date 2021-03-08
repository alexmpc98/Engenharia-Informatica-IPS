package com.pa.patterns.observerimpl.observer;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patriciamacedo
 */
public abstract class Subject implements Observable {
    private List<Observer> observerList;

    public Subject() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void addObservers(Observer... observers) {
        for (Observer obs : observers) {
            if (!observerList.contains(obs))
                this.observerList.add(obs);
        }

    }

    @Override
    public void removeObservers(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers(Observable observable) {
        for (Observer observer : this.observerList)
            observer.update(observable);

    }
}


