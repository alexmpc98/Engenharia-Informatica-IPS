package com.pa.strategy.solutionStrategy;
/*
 *@author patriciamacedo
 */
import java.util.Iterator;
import java.util.Map;

public class StrategySenior implements Strategy {

    /**
     *
     * @param personList - List of persons in the group
     * @return ratio between person that has more than 10 year of experience and group size
     */
    @Override

    public float calculateGlobalIndex(Map<Integer, Programmer> personList){
        int countOld=0;
        for (Programmer programmer : personList.values()) {
            if(programmer.getYearsOfExperience()>10)
                countOld++;
        }
        return countOld*1.f/personList.size();
    }

    @Override
    public Programmer selectLeader(Map<Integer, Programmer> personList) {

        int max=0;
        Programmer p=null;
        for (Programmer programmer : personList.values()) {
            if(programmer.getYearsOfExperience()>max){
                max=programmer.getYearsOfExperience();
                p=programmer;

            }
        }
        return p;

    }
}
