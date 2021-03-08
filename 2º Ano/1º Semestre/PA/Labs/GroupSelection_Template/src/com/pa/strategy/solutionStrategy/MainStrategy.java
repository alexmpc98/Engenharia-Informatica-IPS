package com.pa.strategy.solutionStrategy;

/*
 *@author patriciamacedo
 */
public class MainStrategy {

    public static void main(String[] args) {

        Programmer p1= new Programmer(1, "Ana",5,2);
        Programmer p2= new Programmer(2, "Rui",15,8);
        Programmer p3= new Programmer(3, "Paula",2,9);
        Programmer p4= new Programmer(4, "Luis",6,2);

        Group gr1 = new Group("PA-23", new StrategyDiversity());
        gr1.addMember(p4, p1,p3,p2);
        System.out.printf("\nGrupo %s , GlobalIndex- %f\n", gr1.toString(),gr1.calculateGlobalIndex());
        System.out.println("Leader " + gr1.selectLeader());

        gr1.setStrategy(new StrategyMultiSkill());
        System.out.printf("\nGrupo %s , GlobalIndex- %f\n", gr1.toString(),gr1.calculateGlobalIndex());
        System.out.println("Leader " + gr1.selectLeader());

        gr1.setStrategy(new StrategySenior());
        System.out.printf("\nGrupo %s , GlobalIndex- %f\n", gr1.toString(),gr1.calculateGlobalIndex());
        System.out.println("Leader " + gr1.selectLeader());

        gr1.setStrategy(new StrategySpecialized());
        System.out.printf("\nGrupo %s , GlobalIndex- %f\n", gr1.toString(),gr1.calculateGlobalIndex());
        System.out.println("Leader " + gr1.selectLeader());

    }
}
