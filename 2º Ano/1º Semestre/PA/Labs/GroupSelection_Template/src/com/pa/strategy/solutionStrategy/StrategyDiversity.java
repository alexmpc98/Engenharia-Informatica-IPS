package com.pa.strategy.solutionStrategy;
/*
 *@author patriciamacedo
 */
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class StrategyDiversity implements Strategy {
     private static Random rd= new Random();
    /**
     *
     * @param personList - List of persons in the group
     * @return ratio between person with more than 5 year of experience and the others
     */
    @Override
    public float calculateGlobalIndex(Map<Integer, Programmer> personList){
        int countYoung=0;
        int countOld=0;

        for (Programmer programmer : personList.values()) {

            if(programmer.getYearsOfExperience()>5)
                countOld++;
            if(programmer.getYearsOfExperience()<=5)
                countYoung++;

        }
       return countYoung*1.f/countOld;

    }

    @Override
    public Programmer selectLeader(Map<Integer, Programmer> personList) {
        int value= rd.nextInt(personList.size());
        int count=0;
        Iterator<Programmer> it= personList.values().iterator();
        Programmer p=null;
        while(it.hasNext()&& count++!=value)
            p=it.next();
        return p;

    }
}
