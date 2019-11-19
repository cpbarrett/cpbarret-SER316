package main.java;

import java.awt.List;
import java.util.ArrayList;



public class Student {
    private String asurite;
    private Major major;
    private double overallGrade;
    private ArrayList<Course> courses = new ArrayList<Course>();

    /**
     * Constructor for student.
     * @param asurite student id
     * @param major of student
     */
    public Student(String asurite, Major major) {
        this.setAsurite(asurite);
        this.setMajor(major);
        setOverall_grade(0);

    }

    public String getAsurite() {
        return asurite;
    }

    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }

    public Major getMajor() {
        return major;
    }

    public boolean register_forCourse(Course course) {
        course.addStudent(this);
        return courses.add(course);
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public double getOverall_grade() {
        return overallGrade;
    }

    public void setOverall_grade(double overallGrade) {
        this.overallGrade = overallGrade;
    }

    /**
     * Test to check if the students are the same student.
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Student s = (Student) other;
        return this.getAsurite().equals(s.getAsurite());
    }

}
