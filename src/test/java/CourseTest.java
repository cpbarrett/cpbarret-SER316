
import main.java.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
	Course test;

	@Before
	public void setUp() throws Exception {
		test = new Course("SER316");
		test.set_points("Alice", 15);
		test.set_points("Bill", 30);
		test.set_points("Cathy", 45);
		test.set_points("Joe", 70);
		test.set_points("Jane", 80);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void uncurvedFiveStudents() {
		HashMap<String, Integer> uncurved = test.countOccurencesLetterGrades(false);
		assertTrue(uncurved.get("A") == 0);
        assertTrue(uncurved.get("B") == 1);
        assertTrue(uncurved.get("C") == 1);
        assertTrue(uncurved.get("D") == 1);
        assertTrue(uncurved.get("F") == 2);
	}
	
	@Test
	public void curvedFiveStudents() {
		HashMap<String, Integer> curved = test.countOccurencesLetterGrades(true);
		assertTrue(curved.get("A") == 2);
        assertTrue(curved.get("B") == 0);
        assertTrue(curved.get("C") == 1);
        assertTrue(curved.get("D") == 1);
        assertTrue(curved.get("F") == 1);
	}

}
