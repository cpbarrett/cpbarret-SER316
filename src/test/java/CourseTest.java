
import main.java.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
	Course test;
	Course empty;

	@Before
	public void setUp() throws Exception {
		test = new Course("SER316");
		test.set_points("Alice", 15);
		test.set_points("Bill", 30);
		test.set_points("Cathy", 45);
		test.set_points("Joe", 70);
		test.set_points("Jane", 80);
		
		empty = new Course("SER317");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void addAndDropTest() {
		Student s = new Student("Joey", Major.SER);
		assertTrue(test.addStudent(s));
		assertFalse(test.addStudent(s));
		assertTrue(test.dropStudent("Joey"));
		assertFalse(test.dropStudent("Joey"));
		assertTrue(empty.addStudent(s));			
	}	

	@Test
	public void uncurvedFiveStudents() {
		test.set_points("Joey", 100);
		HashMap<String, Integer> uncurved = test.countOccurencesLetterGrades(false);
		assertTrue(uncurved.get("A") == 1);
        assertTrue(uncurved.get("B") == 1);
        assertTrue(uncurved.get("C") == 1);
        assertTrue(uncurved.get("D") == 1);
        assertTrue(uncurved.get("F") == 2);
	}
	
	@Test
	public void curvedFiveStudents() {
		test.set_points("Joe", 60);
		HashMap<String, Integer> curved = test.countOccurencesLetterGrades(true);
		assertTrue(curved.get("A") == 1);
        assertTrue(curved.get("B") == 1);
        assertTrue(curved.get("C") == 1);
        assertTrue(curved.get("D") == 1);
        assertTrue(curved.get("F") == 1);
        assertTrue(test.getName().equals("SER316"));
	}
	
	@Test
	public void uncurvedemptyCourse() {		
		try {
			empty.countOccurencesLetterGrades(false);
	        fail("no exception thrown");
	    } catch (Throwable expected) {
	        assertEquals(NullPointerException.class, expected.getClass());
	    }
    	try {
			empty.curveLetterGrades();
	        fail("no exception thrown");
	    } catch (Throwable expected) {
	        assertEquals(NullPointerException.class, expected.getClass());
	    }		
	}
}
