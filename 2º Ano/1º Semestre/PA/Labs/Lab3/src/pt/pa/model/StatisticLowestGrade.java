package pt.pa.model;

import java.util.List;

public class StatisticLowestGrade implements Statistic {

    @Override
    public double compute(List<StudentGrade> grades) {
        StudentGrade lowestGrade = grades.get(0);
        for(StudentGrade sGrade : grades){
            if(sGrade.getGrade() < lowestGrade.getGrade()){
                lowestGrade = sGrade;
            }
        }
        return lowestGrade.getGrade();
    }
}
