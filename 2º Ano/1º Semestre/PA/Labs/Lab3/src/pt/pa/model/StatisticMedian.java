package pt.pa.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatisticMedian implements Statistic{
    @Override
    public double compute(List<StudentGrade> grades) {
        Collections.sort(grades, new SortByGradeLowestHighestCompare(){});
        int size = grades.size();
        if(size % 2 != 0){
            return grades.get(size/2).getGrade();
        }
        return (grades.get((size-1)/2).getGrade() + grades.get(size/2).getGrade()) / 2.00;
    }

    abstract class SortByGradeLowestHighestCompare implements Comparator<StudentGrade> {
        public int compare(StudentGrade a, StudentGrade b)
        {
            return a.getGrade() - b.getGrade();
        }
    }
}
