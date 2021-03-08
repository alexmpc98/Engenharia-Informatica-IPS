import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.pa.model.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseGradesTest {
    private CourseGrades courseGrades;

    @BeforeEach
    void setUp() {
        this.courseGrades = new CourseGrades("Programação Avançada");
        courseGrades.add( new StudentGrade("190221093","Alex Coelho",20));
        courseGrades.add( new StudentGrade("190221086","Pizzi",16));
        courseGrades.add( new StudentGrade("190221032","Not Alex Coelho",5));
    }

    @Test
    void differentStatistic() {
        int sum = 0;
        double size = this.courseGrades.list().size();
        for(StudentGrade studentGrade : this.courseGrades.list()){
            sum = sum + studentGrade.getGrade();
        }
        assertEquals(this.courseGrades.computeStatistic(),(double)sum/this.courseGrades.list().size());
        this.courseGrades.changeStatistic(new StatisticHighestGrade());
        assertEquals(this.courseGrades.computeStatistic(),20.0);
        this.courseGrades.changeStatistic(new StatisticLowestGrade());
        assertEquals(this.courseGrades.computeStatistic(),5);
    }

    @Test
    void changeSorting(){
        assertEquals(courseGrades.list().iterator().next().getName(),"Alex Coelho");
        this.courseGrades.changeSorting(new SortById());
        assertEquals(courseGrades.list().iterator().next().getName(),"Not Alex Coelho");
        this.courseGrades.changeSorting(new SortByGradeHighestToLowest());
        assertEquals(courseGrades.list().iterator().next().getName(), "Alex Coelho");
        this.courseGrades.changeSorting(new SortByGradeLowestToHighest());
        assertEquals(courseGrades.list().iterator().next().getName(), "Not Alex Coelho");
    }

    @Test
    void arrayIsNotNull(){
        CourseGrades tempCourseGrades = new CourseGrades("Course Tests");
        assertThrows(CourseGradesException.class, () -> {
            tempCourseGrades.computeStatistic();
        });
    }

    @Test
    void oddSizeMedian(){
        CourseGrades tempCourseGrades = new CourseGrades("Temp Course Grades Odd");
        tempCourseGrades.add( new StudentGrade("18","Samaris",20));
        tempCourseGrades.add( new StudentGrade("11","Pizzi",16));
        tempCourseGrades.add( new StudentGrade("27","Weigl",5));
        tempCourseGrades.changeStatistic(new StatisticMedian());
        assertEquals(tempCourseGrades.computeStatistic(), 16.0);
    }
    @Test
    void pairSizeMedian(){
        CourseGrades tempCourseGrades2 = new CourseGrades("Temp Course Grades Odd");
        tempCourseGrades2.add( new StudentGrade("190221093","Alex Coelho",20));
        tempCourseGrades2.add( new StudentGrade("190221086","Pizzi",16));
        tempCourseGrades2.add( new StudentGrade("190221032","Not Alex Coelho",5));
        tempCourseGrades2.add( new StudentGrade("190221034","Not Alex Coelho2",12));
        tempCourseGrades2.changeStatistic(new StatisticMedian());
        assertEquals(tempCourseGrades2.computeStatistic(), 14.0);
    }
}