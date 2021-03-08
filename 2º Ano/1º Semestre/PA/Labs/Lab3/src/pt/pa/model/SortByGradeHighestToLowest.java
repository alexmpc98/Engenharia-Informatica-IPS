package pt.pa.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByGradeHighestToLowest implements GradeSorting {
    @Override
    public void sort(List<StudentGrade> list) {
        Collections.sort(list, new SortByGradeCompare(){});
    }

    abstract class SortByGradeCompare implements Comparator<StudentGrade> {
        public int compare(StudentGrade a, StudentGrade b)
        {
            return b.getGrade() - a.getGrade();
        }
    }
}
