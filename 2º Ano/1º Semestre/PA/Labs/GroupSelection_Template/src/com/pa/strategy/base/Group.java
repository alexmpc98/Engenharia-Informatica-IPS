package com.pa.strategy.base;
/**
 * @author patricia.macedo
 */

import java.util.HashMap;
import java.util.Map;

public class Group {
    /**
     * enumerado que define o perfil pretendido para o grupo
     */
    public enum TYPE {DIVERSITY, SENIOR, MULTYSKILS}


    private TYPE type;
    private String name;
    private Map<Integer, Programmer> personList;

    /**
     * @param name of the GroupElem
     * @param type of Group
     */
    public Group(String name, Group.TYPE type) {
        this.name = name;
        this.type = type;
        personList = new HashMap<>();
    }

    /**
     * Enroll the a person to the group
     *
     * @param programmers to be added to group
     */
    public void addMember(Programmer... programmers) {
        for (Programmer c : programmers)
            personList.put(c.getId(), c);
    }


    public String getName() {
        return name;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    /**
     * Calculate an indication of the value of the group according to its type
     *
     * @return the globalValueIndexoftheGroup
     */
    public float calculateGlobalIndex() {
        int value = 0;
        float res = 0.0f;
        switch (type) {

            case DIVERSITY:

                int countYoung = 0;
                int countOld = 0;

                for (Programmer programmer : personList.values()) {

                    if (programmer.getYearsOfExperience() > 5)
                        countOld++;
                    if (programmer.getYearsOfExperience() <= 5)
                        countYoung++;
                }
                res = countYoung * 1.f / countOld;
                break;
            case SENIOR:

                countOld = 0;
                for (Programmer programmer : personList.values()) {
                    if (programmer.getYearsOfExperience() > 10)
                        countOld++;
                }
                res = countOld * 1.f / personList.size();
                break;
            case MULTYSKILS:
                int countL = 0;
                for (Programmer programmer : personList.values()) {
                    if (programmer.getNumberLanguages() > 5)
                        countL++;
                }
                res = countL * 1.f / personList.size();
        }
        return res;
    }

    @Override
    public String toString() {
        return name;
    }
}
