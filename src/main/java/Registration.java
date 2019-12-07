package main.java;

import java.util.HashMap;

/**
 * belongs to one student, one course and holds the points for this student and this class
 *
 */
public class Registration {
    public HashMap<String, Integer> points = new HashMap<>();
    public Student s;

    public Registration() {
        super();
    }
    
    public Registration(Student s) {
        this.s = s;
    }

    public int getStudent_Points(String student) {
        return points.get(student);
    }

    public int getStudent_Points(Student student) {
        return points.get(student.getAsurite());
    }
}
