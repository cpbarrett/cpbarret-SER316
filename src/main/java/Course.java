package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for managing course statistics.
 */

public class Course {

    // maps student names (asurite) to their points
    public HashMap<String, Integer> points = new HashMap<>(); 
    private String name; // course name
    private int maxPoints;


    public Course(String name) {
        this(name, 100);

    }

    public Course(String name, int maxPoints) {
        this.setName(name);
        this.maxPoints = maxPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Prints course avg grades.
     */
    public void printCourseStats() {
        System.out.print("Average Grades without max and without min: ");
        System.out.println(this.calculateAverageWithoutMinWithoutMax());
    }

    /**
     * Calculates course avg grades.
     */
    public double calculateAverageWithoutMinWithoutMax() throws NullPointerException {
        List<Integer> collection = getValues();

        int counter = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if (collection.size() == 1) {
            return collection.get(0);
        } else if (collection.size() == 2) {
            return (double)(collection.get(0) + collection.get(1)) / 2;
        } else {
            int allPoints = 0;
            for (int point: collection) {
                if (point >= 0) {

                    counter++;
                    if (point < min) {
                        min = point;
                    }
                    if (point > max) {
                        max = point;
                    }
                    allPoints = allPoints + point;
                }
            }
            int totalPoints = allPoints - max - min;
            return totalPoints / (double) (counter - 1);
        }
    }

    /**
     *  REACH at least 95% Code Coverage (assign 3)
     *  drop a student from course.
     */
    public boolean dropStudent(String asurite) {
        boolean removeFromPoints = points.remove(asurite) != null;
        boolean removeFromStudents = students.remove(new Student(asurite, null));
        return removeFromPoints == removeFromStudents;
    }

    /* REACH at least 95% Code coverage  (assign 3)
     * Students should only be added when they are not yet in the course 
     * (names (asurite member) needs to be unique)
     */
    List<Student> students  = new ArrayList<Student>();

    /**
     * Adds a student to the course.
     * @param s Student
     * @return true if student add was successful
     */
    public boolean addStudent(Student s) {
        if (students != null && points.putIfAbsent(s.getAsurite(), -1) == null) {
            students.add(s);
            return students.add(s);
        }
        return false;
    }

    /**
     * Sets student points.
     * @param name of student
     * @param points of student
     */
    public void set_points(String name, int points) {
        if (!this.points.containsKey(name)) {
            addStudent(new Student(name, null));
        }
        this.points.put(name, points);
    }

    public HashMap<String, Integer> getPoints() {
        return points;
    }

    public int getStudent_Points(String student) {
        return points.get(student);
    }

    public int getStudent_Points(Student student) {
        return points.get(student.getAsurite());
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }


    public List<Student> getStudents() {
        return students;
    }
    
    public List<Integer> getValues() {
        return new ArrayList<Integer>(points.values());
    }

    /**
     * Calculates the letter grade percentages.
     * @param collection of the student grades
     * @return list of grades
     * @throws NullPointerException if no students exist
     */
    public List<Double> calculatePercentiles(List<Integer> collection) 
            throws NullPointerException {
        if (collection == null) {
            throw new NullPointerException();
        }


        int maxMarks = calculateMax();
        System.out.println("Test: " + maxMarks);
        double eachPercentile = 0.0;
        List<Double> percentileList = new ArrayList<Double>();

        for (int element : collection) {

            if (element > 0) {

                eachPercentile = (double)(element / maxMarks  * 100);
                percentileList.add(eachPercentile);
            }
        }
        System.out.println(percentileList);
        return percentileList;
    }

    /**
     * Calculates the greatest positive points scored by a student.
     * @return
     * @throws NullPointerException if no students exist
     */
    public int calculateMax() throws NullPointerException {
        List<Integer> collection = getValues();
        
        if (collection.isEmpty()) {
            throw new NullPointerException();
        }

        if (collection.size() == 1) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        for (int point: collection) {
            if (point >= 0) {
                if (point > max) {
                    max = point;
                }
            }
        }

        return max;
    }


    /**
     * This is where you create your node flow graph and write your White Box test. 
     * Calculates final grades either with curve or without  (assign 3)
     * 
     * <p>Calculation is based on points member and maxPoints from Course.
     * Will call curve if input is true. 
     *  * Grading Scale:
     * >  89% -> A
     * >  79% -> B
     * >  59% -> C
     * >  35% -> D
     * <= 35% -> F
     * 
     * @input curved if true curving is done if not curving is ommitted
     * 
     * @return hashmap with final letter grades for students based on curving `points`.
     * @throws NullPointerException if class is empty
     */
    public HashMap<String, Integer> countOccurencesLetterGrades(boolean curved) 
            throws NullPointerException {
        HashMap<String, Integer> occur = new HashMap<String, Integer>();
        occur.put("A", 0);
        occur.put("B", 0);
        occur.put("C", 0);
        occur.put("D", 0);
        occur.put("F", 0);

        if (!curved) {
            List<Integer> collection = getValues();

            if (collection.isEmpty()) {
                throw new NullPointerException();
            }

            //values changed to match scale
            for (double value : collection) {
                if ((double) value / maxPoints * 100 > 89.0) {
                    occur.put("A", occur.get("A") + 1);
                } else if ((double) value / maxPoints * 100 > 79.0 && value / maxPoints <= 89.0) {
                    occur.put("B", occur.get("B") + 1);
                } else if ((double) value / maxPoints * 100 > 59.0 && value / maxPoints <= 79.0) {
                    occur.put("C", occur.get("C") + 1);
                } else if ((double) value / maxPoints * 100 > 35.0 && value / maxPoints <= 59.0) {
                    occur.put("D", occur.get("D") + 1);
                } else {
                    occur.put("F", occur.get("F") + 1);
                }
            }
        } else {
            //changed to match the correct input type
            for (String grade : curveLetterGrades().values()) {
                occur.put(grade, occur.get(grade) + 1);
            }
        }
        return occur;
    }

    /** This will be needed for assignment 3 (do not change in assignment 2)
     * Calculates final grades including a curve and returns final letter grade
     * of each student.
     * 
     * <p>Calculation is based on points member inherited from Course.
     * Curve is calculated by adding the positive difference between the student
     * with the highest non-negative points and maxPoints to all scores.
     * Grading Scale:
     * >  89% -> A
     * >  79% -> B
     * >  59% -> C
     * >  35% -> D
     * <= 35% -> F
     * 
     * <p>* eg.let points = [Alice:15, Bill:30, Cathy:45, Joe:70, Jane:80] and maxPoints = 100,
     * curve would be 100 - 80 = 20.
     * Adjusted points would be = [Alice:35, Bill:50, Cathy:65, Joe:90, Jane:100].
     * Adjusted percentages would be = [35%, 50%, 65%, 90%, 100%].
     * Returned HashMap points would be = [Alice:F, Bill:C, Cathy:C, Joe:A, Jane:A].
     *
     * @return hashmap with final letter grades for students based on curving `points`.
     * @throws NullPointerException if class is empty
     */
    public Map<String, String> curveLetterGrades() throws NullPointerException {
        List<Student> theClass = getStudents();
        if (theClass.isEmpty()) {
            throw new NullPointerException();
        }
        HashMap<String,String> finalGrades = new HashMap<String,String>();

        int curve = Math.abs(getMaxPoints() - calculateMax());

        //this is done because getStudents() produces duplicate values somewhere
        for (int i = 0; i < theClass.size(); i = i + 2) { 
            Student student = theClass.get(i);
            set_points(student.getAsurite(), getStudent_Points(student) + curve);

            double grade = getStudent_Points(student);

            if ((double)grade / maxPoints * 100 > 89.0) {
                finalGrades.put(student.getAsurite(), "A");
            } else if ((double)grade / maxPoints * 100 > 79.0 && grade / maxPoints <= 89.0) {
                finalGrades.put(student.getAsurite(), "B");
            } else if ((double)grade / maxPoints * 100 > 59.0 && grade / maxPoints <= 79.0) {
                finalGrades.put(student.getAsurite(), "C");
            } else if ((double)grade / maxPoints * 100 > 35.0 && grade / maxPoints <= 59.0) {
                finalGrades.put(student.getAsurite(), "D");
            } else {
                finalGrades.put(student.getAsurite(), "F");
            }
        }
        return finalGrades;
    }
}