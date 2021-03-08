package pt.pa.model;

import java.util.*;

public class DAOGradesImplementation implements DAOGrades{
    private final Map<String, StudentGrade> results;

    public DAOGradesImplementation(){
        this.results = new HashMap<String, StudentGrade>();
    }

    /**
     * Gets the corresponding result for a student.
     * @param id student id to query
     * @return student grade; null if does not exist
     */
    @Override
    public StudentGrade get(String id) {
        return this.results.get(id);
    }

    /**
     * Returns a collection of all current grades.
     * @return collection of grades
     */
    @Override
    public List<StudentGrade> getAll() {
        List<StudentGrade> list = new ArrayList<>( this.results.values() );
        return list;
    }

    /**
     * Adds a grade to the course results
     * @param g grade to add
     * @throws DAOGradesException if already exists a result for the student contained in <code>g</code>
     * @throws DAOGradesNotNullException if <code>g</code> is null
     */
    @Override
    public void add(StudentGrade g) throws DAOGradesException {
        if(g == null)
            throw new DAOGradesNotNullException("StudentGrade cannot be null.");

        if(results.containsKey(g.getId()))
            throw new DAOGradesException("Student already exists.");
        results.put(g.getId(), g);
    }


    /**
     * Updates a grade within the current results.
     * @param sGrade Student Grade to be modified
     * @param grade new grade for the student
     * @return previous grade
     * @throws DAOGradesException if the grade isn't between [0,20]
     */
    @Override
    public int update(StudentGrade sGrade, int grade) throws DAOGradesException {
        if(grade < 0 || grade > 20) throw new DAOGradesException("Grade must be in [0,20].");
        if(!results.containsKey(sGrade.getId()))
            throw new DAOGradesException("Student does not exist: " + sGrade.getId());
        StudentGrade studentGrade = results.get(sGrade.getId());
        int oldGrade = studentGrade.updateGrade(grade);
        return oldGrade;
    }

    /**
     * Removes a grade from the course results
     * @param sGrade Student Grade to remove
     * @return the removed grade; null if does not exist
     */
    @Override
    public StudentGrade delete(StudentGrade sGrade) {
        StudentGrade studentGrade = results.remove(sGrade);
        /* If student doesn't exist, null is returned */
        return studentGrade;
    }

    /**
     * Clears all current results.
     */
    @Override
    public void clear() {
        results.clear();
    }
}
