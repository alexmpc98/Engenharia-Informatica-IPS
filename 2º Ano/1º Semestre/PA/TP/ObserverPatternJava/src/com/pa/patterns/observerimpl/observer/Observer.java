package com.pa.patterns.observerimpl.observer;

import java.util.Optional;

/**
 *
 * @author patriciamacedo
 */
public interface Observer {
    /**
     * When a observer is notified execute this function
     * @param obj - argument of the method
     */
    void update(Observable obj);

}

