package patterns.observer;

public interface Observable {
    /**
     *  Attach  observers to the subject.
     * @param observers  to be attached
     */
    public void addObservers(Observer... observers);

    /**
     * notify all observer
     * @param object, argument of update method
     */
    public void notifyObservers(Object object);
}
