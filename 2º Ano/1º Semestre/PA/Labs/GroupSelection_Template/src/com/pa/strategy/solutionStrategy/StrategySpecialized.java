package com.pa.strategy.solutionStrategy;

import java.util.Map;

public class StrategySpecialized implements Strategy{

    /**
     * @param personList - List of persons in the group
     * @return global index
     */
    @Override
    public float calculateGlobalIndex(Map<Integer, Programmer> personList) {
        int x = 0;
        int y = personList.size();
        for(Programmer p : personList.values()){
            if(p.getYearsOfExperience() >= 5 && p.getNumberLanguages() <= 3){
                x++;
            }
        }
        return (float)x/(y-x);
    }

    /**
     * @param personList
     * @return leader selected
     */
    @Override
    public Programmer selectLeader(Map<Integer, Programmer> personList) {
        Programmer leader = null;
        int minLanguages = Integer.MAX_VALUE;
        for(Programmer p: personList.values()){
            if(p.getYearsOfExperience() > 5 && p.getNumberLanguages() < minLanguages){
                minLanguages = p.getNumberLanguages();
                leader = p;
            }
        }
        return leader;
    }
}
