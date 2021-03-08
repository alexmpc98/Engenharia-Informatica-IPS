package pt.pa.model;

import java.util.List;

public class SortByName implements GradeSorting {
    @Override
    public void sort(List<StudentGrade> list) {
        list.sort((g1, g2) -> g1.getName().compareToIgnoreCase(g2.getName()));
    }
}
