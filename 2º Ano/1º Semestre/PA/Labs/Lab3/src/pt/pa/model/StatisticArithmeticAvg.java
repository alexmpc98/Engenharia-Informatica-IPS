package pt.pa.model;

import java.util.List;

public class StatisticArithmeticAvg implements Statistic{

    @Override
    public double compute(List<StudentGrade> grades) {
        int sum = 0;
        double size = grades.size();
        for(StudentGrade sGrade : grades){
            sum = sum + sGrade.getGrade();
        }
        return (float)sum/size;
    }
}
