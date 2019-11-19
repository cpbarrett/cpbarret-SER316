import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import main.java.Course;

import org.junit.Before;

import org.junit.Test;


public class GivenWhiteBox {
    Course oneStudent;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void noCurvingTwoStudents() {
        // One Student
        oneStudent = new Course("SER316");
        oneStudent.set_points("Hanna", 85);
        oneStudent.set_points("Tanja", 100);
        HashMap<String, Integer> ans = oneStudent.countOccurencesLetterGrades(false);
        assertTrue(ans.get("A") == 1);
        assertTrue(ans.get("B") == 1);
        assertTrue(ans.get("C") == 0);
        assertTrue(ans.get("D") == 0);
        assertTrue(ans.get("F") == 0);
    }
}
