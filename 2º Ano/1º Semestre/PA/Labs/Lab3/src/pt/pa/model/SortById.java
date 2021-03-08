package pt.pa.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortById implements GradeSorting {
    @Override
    public void sort(List<StudentGrade> list) {
        list.sort((g1, g2) -> g1.getId().compareToIgnoreCase(g2.getId()));
    }
}
