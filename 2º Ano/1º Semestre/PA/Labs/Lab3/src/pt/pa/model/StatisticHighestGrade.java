package pt.pa.model;

import java.util.List;

public class StatisticHighestGrade implements Statistic {

    @Override
    public double compute(List<StudentGrade> grades) {
        StudentGrade highestGrade = grades.get(0);
        for(StudentGrade sGrade : grades){
            if(sGrade.getGrade() > highestGrade.getGrade()){
                highestGrade = sGrade;
            }
        }
        return highestGrade.getGrade();
    }
}
