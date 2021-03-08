package pt.pa.model;

import java.util.Objects;

/**
 * An instance of this class identifies a student and his grade.
 *
 * For all purposes, two students are considered the same ("equals" criteria) if they have the same ID.
 */
public class StudentGrade {

    private final String id;
    private final String name;
    private int grade;

    public StudentGrade(String id, String name, int grade) {
        if(id == null || name == null) throw new NullPointerException("Id/name cannot be null.");
        if(grade < 0 || grade > 20) throw new IllegalArgumentException("grade must be in [0,20].");

        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int updateGrade(int grade) {
        if(grade < 0 || grade > 20) throw new IllegalArgumentException("grade must be in [0,20].");
        int previous = this.grade;
        this.grade = grade;
        return previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGrade that = (StudentGrade) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
