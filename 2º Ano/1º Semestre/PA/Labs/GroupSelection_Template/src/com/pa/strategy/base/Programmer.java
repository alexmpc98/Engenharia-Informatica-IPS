package com.pa.strategy.base;

/**
 * @author patricia.macedo
 *
 */
public class Programmer {
    private int id;
    private String name;

    private int yearsOfExperience;
    private int numberLanguages;

    public Programmer(int id, String name, int yearsOfExperience, int numberLanguages) {
        this.id = id;
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
        this.numberLanguages = numberLanguages;
    }

    public Programmer() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public int getNumberLanguages() {
        return numberLanguages;
    }
}
