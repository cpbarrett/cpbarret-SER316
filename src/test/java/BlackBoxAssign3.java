import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BlackBoxAssign3 {
    private Class<Course> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxAssign3(Object classUnderTest) {
        this.classUnderTest = (Class<Course>) classUnderTest;
    }

    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = {
                {CourseGrades0.class},
                {CourseGrades1.class},
                {CourseGrades2.class},
                {CourseGrades3.class},
                {CourseGrades4.class},
                {CourseGrades5.class}

        };
        return Arrays.asList(classes);
    }

    private Course createCourse(String name) throws Exception {
        Constructor<Course> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }

    Course oneStudent;
    HashMap<String, String> oneStudentExpected = new HashMap<String, String>();

    Course happyDayGradeBoundary;
    HashMap<String, String> happyDayGradeBoundaryExpected = new HashMap<String, String>();

    Course zeroStudents;
    HashMap<String, String> zeroStudentsExpected = new HashMap<String, String>();

    Course fiveStudents;
    HashMap<String, String> fiveStudentsExpected = new HashMap<String, String>();

    Course emptyStudents;
    HashMap<String, String> emptyStudentsExpected = new HashMap<String, String>();

    Course overAchievers;
    HashMap<String, String> overAchieversExpected = new HashMap<String, String>();

    @Before
    public void setUp() throws Exception {

        oneStudent = createCourse("SER316");

        oneStudent.set_points("Hanna", 50);
        oneStudentExpected.put("Hanna", "A");      

        // Happy Day Case Grade Boundaries
        // Students mix of grades
        happyDayGradeBoundary = createCourse("SER315");
        happyDayGradeBoundary.set_points("100", 100);  //A
        happyDayGradeBoundary.set_points(">89", 90);  //A
        happyDayGradeBoundary.set_points(">79", 89);  //B
        happyDayGradeBoundary.set_points(">59", 79);  //C
        happyDayGradeBoundary.set_points(">35", 59);  //D
        happyDayGradeBoundary.set_points("<=35", 35);  //F

        happyDayGradeBoundaryExpected.put("100", "A");
        happyDayGradeBoundaryExpected.put(">89", "A");
        happyDayGradeBoundaryExpected.put(">79", "B");
        happyDayGradeBoundaryExpected.put(">59", "C");
        happyDayGradeBoundaryExpected.put(">35", "D");
        happyDayGradeBoundaryExpected.put("<=35", "F");

        //course created with zero students
        zeroStudents = createCourse("ser317");

        //5 students from example
        fiveStudents = createCourse("ser318");

        fiveStudents.set_points("Alice", 15);
        fiveStudentsExpected.put("Alice", "F");
        fiveStudents.set_points("Bill", 30);
        fiveStudentsExpected.put("Bill", "D");
        fiveStudents.set_points("Kathy", 45);
        fiveStudentsExpected.put("Kathy", "C");
        fiveStudents.set_points("Joe", 70);
        fiveStudentsExpected.put("Joe", "A");
        fiveStudents.set_points("Jane", 80);
        fiveStudentsExpected.put("Jane", "A");

        //course of students with empty names
        emptyStudents = createCourse("SER320");

        // this would be the expected result after the method countOccurencesLetterGrades is called
        emptyStudents.set_points("", 50);
        emptyStudentsExpected.put("", "A"); 
        emptyStudents.set_points("", 40);
        //emptyStudentsExpected.put("", "B");
        emptyStudents.set_points("", 30);
        //emptyStudentsExpected.put("", "C");
        emptyStudents.set_points("", 20);
        //emptyStudentsExpected.put("", "D");
        emptyStudents.set_points("", 10);
        //emptyStudentsExpected.put("", "F");

        //5 students, all get > max points
        overAchievers = createCourse("ser322");

        overAchievers.setMaxPoints(10);
        overAchievers.set_points("Alice", 100);
        overAchieversExpected.put("Alice", "A");
        overAchievers.set_points("Bill", 100);
        overAchieversExpected.put("Bill", "A");
        overAchievers.set_points("Kathy", 100);
        overAchieversExpected.put("Kathy", "A");
        overAchievers.set_points("Joe", 100);
        overAchieversExpected.put("Joe", "A");
        overAchievers.set_points("Jane", 100);
        overAchieversExpected.put("Jane", "A");

    }

    @After
    public void tearDown() throws Exception {
    }

    // sample test
    @Test
    public void oneStudent() {
        Map<String, String> ans = oneStudent.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(oneStudentExpected));
    }

    // sample test2
    @Test
    public void happyDayGradeBoundaries() {
        Map<String, String> ans = happyDayGradeBoundary.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(happyDayGradeBoundaryExpected));
    }

    @Test
    public void zeroStudents() {
        try {
            Map<String, String> ans = zeroStudents.curveLetterGrades();
            for (Map.Entry<String, String> e : ans.entrySet()) {
                System.out.println(e.getKey() + " " + e.getValue());
            }
            assertTrue(ans.equals(zeroStudentsExpected));
        } catch (Throwable expected) {
            assertEquals(NullPointerException.class, expected.getClass());
        }
    }

    @Test
    public void fiveStudents() {
        Map<String, String> ans = fiveStudents.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(fiveStudentsExpected));
    }

    @Test
    public void emptyStudents() {
        Map<String, String> ans = emptyStudents.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(emptyStudentsExpected));
    }

    @Test
    public void overAchievers() {
        Map<String, String> ans = overAchievers.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(overAchieversExpected));
    }

}
