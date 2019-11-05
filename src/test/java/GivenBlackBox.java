import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.Course;
//import cls.main.java;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<Course> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
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
        happyDayGradeBoundary.set_points("100"  , 100);  //A
        happyDayGradeBoundary.set_points(">89"  , 90);  //A
        happyDayGradeBoundary.set_points(">79"  , 89);  //B
        happyDayGradeBoundary.set_points(">59"  , 79);  //C
        happyDayGradeBoundary.set_points(">35"  , 59);  //D
        happyDayGradeBoundary.set_points("<=35" , 35);  //F

        happyDayGradeBoundaryExpected.put("100"  , "A");
        happyDayGradeBoundaryExpected.put(">89"  , "A");
        happyDayGradeBoundaryExpected.put(">79"  , "B");
        happyDayGradeBoundaryExpected.put(">59"  , "C");
        happyDayGradeBoundaryExpected.put(">35"  , "D");
        happyDayGradeBoundaryExpected.put("<=35" , "F");
        
        //course created with zero students
        zeroStudents = createCourse("ser317");
        
        //5 students from example
        fiveStudents = createCourse("ser318");
        
        fiveStudents.setMaxPoints(100);
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
        badDayGradeBoundary.setMaxPoints(-1);
        badDayGradeBoundary.set_points("100"  , -100);  //A
        badDayGradeBoundary.set_points(">89"  , -90);  //A
        badDayGradeBoundary.set_points(">79"  , -89);  //B
        badDayGradeBoundary.set_points(">59"  , -79);  //C
        badDayGradeBoundary.set_points(">35"  , -59);  //D
        badDayGradeBoundary.set_points("<=35" , -35);  //F

        badDayGradeBoundaryExpected.put("100"  , "A");
        badDayGradeBoundaryExpected.put(">89"  , "A");
        badDayGradeBoundaryExpected.put(">79"  , "B");
        badDayGradeBoundaryExpected.put(">59"  , "C");
        badDayGradeBoundaryExpected.put(">35"  , "D");
        badDayGradeBoundaryExpected.put("<=35" , "F");
        
        //course of students with empty names
        emptyStudents = createCourse("SER320");

        // this would be the expected result after the method countOccurencesLetterGrades is called
        emptyStudents.set_points("", 50);
        emptyStudentsExpected.put("", "A"); 
        emptyStudents.set_points("", 40);
        emptyStudentsExpected.put("", "B");
        emptyStudents.set_points("", 30);
        emptyStudentsExpected.put("", "C");
        emptyStudents.set_points("", 20);
        emptyStudentsExpected.put("", "D");
        emptyStudents.set_points("", 10);
        emptyStudentsExpected.put("", "F");
        
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
        totalFailure.set_points("Jane", 0);
        totalFailureExpected.put("Jane", "F");
        
        //5 students, all get > max points
        overAchievers = createCourse("ser322");
        
        overAchievers.setMaxPoints(10);
        overAchievers.set_points("Alice", 100);
        overAchieversExpected.put("Alice", "F");
        overAchievers.set_points("Bill", 100);
        overAchieversExpected.put("Bill", "F");
        overAchievers.set_points("Kathy", 100);
        overAchieversExpected.put("Kathy", "F");
        overAchievers.set_points("Joe", 100);
        totalFailureExpected.put("Joe", "F");
        overAchievers.set_points("Jane", 100);
        overAchieversExpected.put("Jane", "F");
        
      //5 students, all get > max points
        noPoints = createCourse("ser323");
        
        noPoints.set_points((String) null, 1/10);
        noPointsExpected.put((String) null, (String) null);
        
    }

    @After
    public void tearDown() throws Exception {
    }

    // sample test
    @Test
    public void oneStudent() {
        Map<String, String> ans = oneStudent.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(oneStudentExpected));
    }

    // sample test2
    @Test
    public void happyDayGradeBoundaries() {
        Map<String, String> ans = happyDayGradeBoundary.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(happyDayGradeBoundaryExpected));
    }
    
    @Test
    public void zeroStudents() {
        Map<String, String> ans = zeroStudents.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(zeroStudentsExpected));
    }
    
    @Test
    public void fiveStudents() {
        Map<String, String> ans = fiveStudents.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(fiveStudentsExpected));
    }
    
    @Test
    public void badDayGradeBoundaries() {
        Map<String, String> ans = badDayGradeBoundary.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(badDayGradeBoundaryExpected));
    }
    
    @Test
    public void emptyStudents() {
        Map<String, String> ans = emptyStudents.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(emptyStudentsExpected));
    }
    
    @Test
    public void totalFailure() {
        Map<String, String> ans = totalFailure.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(totalFailureExpected));
    }
    
    @Test
    public void overAchievers() {
        Map<String, String> ans = overAchievers.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(overAchieversExpected));
    }
    
    @Test
    public void noPoints() {
        Map<String, String> ans = noPoints.curveLetterGrades();
        for(Map.Entry<String, String> e : ans.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        assertTrue(ans.equals(noPointsExpected));
    }

}
