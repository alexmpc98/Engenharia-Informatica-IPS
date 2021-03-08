package com.pa.strategy.solutionStrategy;
/**
 * @author patricia.macedo
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Group {

    private Strategy strategy;
    private String name;
    private Map<Integer, Programmer> personList;

    private static Random random = new Random();

    /**
     *
     * @param name of the GroupElem
     * @param  strategy to the group
     */
    public Group(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
        this.personList = new HashMap<>();
    }

    /**
     * Enroll the a programmers to the group
     * @param programmers to be added to group
     */
    public void addMember(Programmer... programmers) {
        for (Programmer c : programmers)
            personList.put(c.getId(), c);
    }


    public String getName() {
        return name;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculate an indication of the value of the group according to its type
     * @return the globalValueIndexoftheGroup
     */
    public float calculateGlobalIndex() {
        return strategy.calculateGlobalIndex(personList);
    }

    public Programmer selectLeader() {
        return strategy.selectLeader(personList);
    }

    @Override
    public String toString() {
        return "Group{" +
                "strategy=" + strategy.getClass().getSimpleName() +
                ", name='" + name + '\'' +
                '}';
    }
}
