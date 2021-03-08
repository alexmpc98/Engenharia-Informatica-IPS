package patterns.observer;

public interface Observer {
    /**
     * When a observer is notified executes this function
     * @param obj - argument of the method
     */
    void update(Object obj);

}

