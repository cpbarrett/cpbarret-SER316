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



//import main.java.Course;
//import cls.main.java;

@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<Course> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
        this.classUnderTest = (Class<Course>) classUnderTest;
    }

    /**
     * The course grades being tested.
     * @return
     */
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

    Course badDayGradeBoundary;
    HashMap<String, String> badDayGradeBoundaryExpected = new HashMap<String, String>();

    Course emptyStudents;
    HashMap<String, String> emptyStudentsExpected = new HashMap<String, String>();

    Course totalFailure;
    HashMap<String, String> totalFailureExpected = new HashMap<String, String>();

    Course overAchievers;
    HashMap<String, String> overAchieversExpected = new HashMap<String, String>();

    Course noPoints;
    HashMap<String, String> noPointsExpected = new HashMap<String, String>();

    Course communism;
    HashMap<String, String> communismExpected = new HashMap<String, String>();
    
    Course negativeCurve;
    HashMap<String, String> negativeCurveExpected = new HashMap<String, String>();
    
    Course hugePoints;
    HashMap<String, String> hugePointsExpected = new HashMap<String, String>();
    
    Course emptyCourse;
    HashMap<String, String> emptyCourseExpected = new HashMap<String, String>();

    /**
     * Set up conditions for all tests.
     * @throws Exception for a reason
     */
    @Before
    public void setUp() throws Exception {

        // One Student


        // all courses should be created like this


        // Course created with two Students having
        oneStudent = createCourse("SER316");

        // this would be the expected result after the method countOccurencesLetterGrades is called
        oneStudent.set_points("Hanna", 50);
        oneStudentExpected.put("Hanna", "A");      

        // Happy Day Case Grade Boundaries
        // Four Students mix of grades
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

        // Bad Day Case Grade Boundaries
        // Four Students all negative points
        badDayGradeBoundary = createCourse("SER319");

        badDayGradeBoundary.setMaxPoints(-100);
        badDayGradeBoundary.set_points("100", -100);  //A
        badDayGradeBoundary.set_points(">89", -90);  //A
        badDayGradeBoundary.set_points(">79", -89);  //B
        badDayGradeBoundary.set_points(">59", -79);  //C
        badDayGradeBoundary.set_points(">35", -59);  //D
        badDayGradeBoundary.set_points("<=35", -35);  //F

        badDayGradeBoundaryExpected.put("100", "A");
        badDayGradeBoundaryExpected.put(">89", "A");
        badDayGradeBoundaryExpected.put(">79", "B");
        badDayGradeBoundaryExpected.put(">59", "C");
        badDayGradeBoundaryExpected.put(">35", "D");
        badDayGradeBoundaryExpected.put("<=35", "F");

        //course of students with empty names
        emptyStudents = createCourse("SER320");

        // this would be the expected result after the method countOccurencesLetterGrades is called
        emptyStudents.set_points("", 50);
        emptyStudentsExpected.put("", "A"); 

        //5 students, all get 0 points
        totalFailure = createCourse("ser321");

        totalFailure.setMaxPoints(0);
        totalFailure.set_points("Alice", 0);
        totalFailureExpected.put("Alice", "F");
        totalFailure.set_points("Bill", 0);
        totalFailureExpected.put("Bill", "F");
        totalFailure.set_points("Kathy", 0);
        totalFailureExpected.put("Kathy", "F");
        totalFailure.set_points("Joe", 0);
        totalFailureExpected.put("Joe", "F");
        totalFailure.set_points("Jane", -1);
        totalFailureExpected.put("Jane", "F");

        //5 students, all get > max points
        overAchievers = createCourse("ser322");

        overAchievers.setMaxPoints(10);
        overAchievers.set_points("Alice", 200);
        overAchieversExpected.put("Alice", "A");
        overAchievers.set_points("Bill", 100);
        overAchieversExpected.put("Bill", "A");
        overAchievers.set_points("Kathy", 100);
        overAchieversExpected.put("Kathy", "A");
        overAchievers.set_points("Joe", 100);
        overAchieversExpected.put("Joe", "A");
        overAchievers.set_points("Jane", -100);
        overAchieversExpected.put("Jane", "F");
        
        //null student with 0 points
        noPoints = createCourse("ser323");
        
        noPoints.set_points(null, 0);
        noPointsExpected.put(null, "A");

        //all students are given 100
        communism = createCourse("ser324");
        
        communism.setMaxPoints(10);
        communism.set_points("student 1", 10);
        communismExpected.put("student 1", "A");
        communism.set_points("student 2", 10);
        communismExpected.put("student 2", "A");
        
        //5 students, one gets > max points
        negativeCurve = createCourse("ser325");
        
        negativeCurve.set_points("Alice", 200);
        negativeCurveExpected.put("Alice", "A");
        negativeCurve.set_points("Bill", 100);
        negativeCurveExpected.put("Bill", "A");
        negativeCurve.set_points("Kathy", 90);
        negativeCurveExpected.put("Kathy", "A");
        negativeCurve.set_points("Joe", 50);
        negativeCurveExpected.put("Joe", "D");
        negativeCurve.set_points("Jane", -10);
        negativeCurveExpected.put("Jane", "F");

        //uses large ints
        hugePoints = createCourse("ser326");
        
        hugePoints.setMaxPoints(Integer.MIN_VALUE);
        hugePoints.set_points("stud1", 1);
        hugePointsExpected.put("stud1", "A");
        hugePoints.set_points("stud2", Integer.MIN_VALUE);
        hugePointsExpected.put("stud2", "A");
        hugePoints.set_points("stud3", 0);
        hugePointsExpected.put("stud3", "A");
        hugePoints.set_points("stud4", Integer.MAX_VALUE);
        hugePointsExpected.put("stud4", "A");
        
        //empty course
        emptyCourse = createCourse(null);

        emptyCourse.setMaxPoints(00);
        emptyCourse.set_points("a", 1000);
        emptyCourseExpected.put("a", "F");
        emptyCourse.set_points("b", -1000);
        emptyCourse.set_points("c", 0);
        emptyCourseExpected.put("c", "F");
        emptyCourse.setName("new");

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
        Map<String, String> ans = zeroStudents.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(zeroStudentsExpected));
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
    public void badDayGradeBoundaries() {
        Map<String, String> ans = badDayGradeBoundary.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(badDayGradeBoundaryExpected));
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
    public void totalFailure() {
        Map<String, String> ans = totalFailure.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(totalFailureExpected));
    }

    @Test
    public void overAchievers() {
        Map<String, String> ans = overAchievers.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(overAchieversExpected));
    }

    @Test
    public void noPoints() {
        Map<String, String> ans = noPoints.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(noPointsExpected));
    }
    
    @Test
    public void communism() {
        Map<String, String> ans = communism.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(communismExpected));
    }
    
    @Test
    public void negativeCurve() {
        Map<String, String> ans = negativeCurve.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(negativeCurveExpected));
    }

    @Test
    public void hugePoints() {
        Map<String, String> ans = hugePoints.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(hugePointsExpected));
    }

    @Test
    public void emptyCourse() {
        Map<String, String> ans = emptyCourse.curveLetterGrades();
        for (Map.Entry<String, String> e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        assertTrue(ans.equals(emptyCourseExpected));
    }
}
