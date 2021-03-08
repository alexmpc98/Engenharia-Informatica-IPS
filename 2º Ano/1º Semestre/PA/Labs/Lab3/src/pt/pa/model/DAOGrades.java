package pt.pa.model;


import java.util.List;

public interface DAOGrades {

    StudentGrade get(String id);

    List<StudentGrade> getAll() throws CourseGradesException, NullPointerException;

    void add(StudentGrade t);

    int update(StudentGrade g, int studentGrade);

    StudentGrade delete(StudentGrade t);

    public void clear();
}
