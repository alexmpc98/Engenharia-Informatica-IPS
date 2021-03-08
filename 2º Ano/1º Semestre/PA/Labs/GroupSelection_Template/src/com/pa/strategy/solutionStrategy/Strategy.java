package com.pa.strategy.solutionStrategy;
/*
 *@author patriciamacedo
 */
import java.util.Map;

/**
 *  Interface Strategy define the method that will be implemented in concreteStrategy
 */
public interface Strategy {
    /**

     * @param personList - List of persons in the group
     * @return global index
     */
    public float calculateGlobalIndex(Map<Integer, Programmer> personList);
    /**
     *
     * @return leader selected
     */
    public Programmer selectLeader(Map<Integer, Programmer> personList);
}
