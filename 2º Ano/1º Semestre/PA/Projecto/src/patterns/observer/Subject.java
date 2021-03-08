package patterns.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that will have a list of observers, implements Observable
 */
public abstract class Subject implements Observable {
    private List<Observer> observerList;

    /**
     * Method that will instanciate the subject
     */
    public Subject() {
        this.observerList = new ArrayList<>();
    }

    /**
     * Method that will will add observers to the list atribute if it doesnt contain the ones that are passed in parameter
     *
     * @param observers  to be attached
     */
    @Override
    public void addObservers(Observer... observers) {
        for (Observer obs : observers) {
            if (!observerList.contains(obs))
                this.observerList.add(obs);
        }
    }

    /**
     * Method that will update all objects of the list
     *
     * @param obj an object to update with
     */
    @Override
    public void notifyObservers(Object obj) {
        for (Observer observer : this.observerList)
            observer.update(obj);
    }
}


