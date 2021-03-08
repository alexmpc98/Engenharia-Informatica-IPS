package pt.pa.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByGradeLowestToHighest implements GradeSorting {

    @Override
    public void sort(List<StudentGrade> list) {
        Collections.sort(list, new SortByGradeLowestHighestCompare(){});
    }

    abstract class SortByGradeLowestHighestCompare implements Comparator<StudentGrade>{
        public int compare(StudentGrade a, StudentGrade b)
        {
            return a.getGrade() - b.getGrade();
        }
    }
}
