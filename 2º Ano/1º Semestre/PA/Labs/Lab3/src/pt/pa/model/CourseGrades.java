package pt.pa.model;

import java.util.*;

public class CourseGrades {
    private final String course;
    private DAOGrades daoGrades = new DAOGradesImplementation();
    private Statistic s = new StatisticArithmeticAvg();
    private GradeSorting gSorting = new SortByName();


    /**
     * Instanciates a new course with empty list of grades.
     * @param course
     */
    public CourseGrades(String course) {
        this.course = course;
    }

    /**
     * Gets the corresponding result for a student.
     * @param studentId student id to query
     * @return student grade; null if does not exist
     */
    public StudentGrade get(String studentId)  {
        return this.daoGrades.get(studentId);
    }

    /**
     * Adds a grade to the course results
     * @param g grade to add
     * @throws CourseGradesException if already exists a result for the student contained in <code>g</code>
     * @throws NullPointerException if <code>g</code> is null
     */
    public void add(StudentGrade g) throws CourseGradesException, NullPointerException {
        this.daoGrades.add(g);
    }

    /**
     * Removes a grade from the course results
     * @param studentId id of student's grade to remove.
     * @return the removed grade; null if does not exist
     */
    public StudentGrade remove(String studentId)  {
        return this.daoGrades.delete(this.daoGrades.get(studentId));
    }

    /**
     * Updates a grade within the current results.
     * @param studentId student id
     * @param newGrade new grade for the student
     * @return previous grade
     * @throws CourseGradesException if no student with <code>studentId</code> exist in the current results
     */
    public int update(String studentId, int newGrade) throws CourseGradesException {
        return this.daoGrades.update(this.daoGrades.get(studentId),newGrade);
    }

    /**
     * Returns a collection of all current grades.
     * @return collection of grades
     */
    public Collection<StudentGrade> list() {
        this.gSorting.sort(this.daoGrades.getAll());
        return this.daoGrades.getAll();
    }

    /**
     * Clears all current results.
     */
    public void clear() {
        this.daoGrades.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.course + "\n");
        for(StudentGrade g : list()) {
            sb.append( String.format("%10s | %-30s | %2d\n",
                    g.getId(), g.getName(), g.getGrade()));
        }
        return sb.toString();
    }

    public double compute(List<StudentGrade> grades) {
        if(grades == null){
            return -1;
        }
        System.out.println("Arithmetic Average: " +  s.compute(grades));
        changeStatistic(new StatisticLowestGrade());
        System.out.println("Lowest Grade: " + (int)s.compute(grades));
        changeStatistic(new StatisticHighestGrade());
        System.out.println("Highest Grade: " + (int)s.compute(grades) + "\n");
        return 0;
    }

    public void changeStatistic(Statistic s){
        this.s = s;
    }

    public void changeSorting(GradeSorting gS){
        this.gSorting = gS;
    }

    public double computeStatistic() throws CourseGradesException {
        if(Double.compare(this.s.compute(new ArrayList<>(this.daoGrades.getAll())),Double.NaN) == 0){
            throw new CourseGradesException("The array doesn't exist or doesn't have values!");
        }
        else {
            return this.s.compute(new ArrayList<>(this.daoGrades.getAll()));
        }
    }
}
