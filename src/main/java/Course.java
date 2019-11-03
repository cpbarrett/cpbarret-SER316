package main.java;

/**
 * class for managing course statistics
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Course {

    public HashMap<String, Integer> points = new HashMap<>(); // maps student names (asurite) to their points
    private String Name; // course name
    private int maxPoints;


    public Course(String name) {
        this(name, 100);

    }

    public Course(String name, int maxPoints) {
        this.SetName(name);
        this.maxPoints = maxPoints;
    }

    public String GetName() {
        return Name;
    }

    public void SetName(String name) {
        this.Name = name;
    }


    public void printCourseStats() {
        ArrayList<Integer> values = new ArrayList<Integer>(points.values());

        System.out.print("Average Grades without max and without min: ");
        System.out.println(this.calculateAverageWithoutMinWithoutMax());
    }


    public double calculateAverageWithoutMinWithoutMax() throws NullPointerException {
        ArrayList<Integer> collection = new ArrayList<Integer>(points.values());

        int counter = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if(collection.size() == 1)
            return collection.get(0);

        else if(collection.size() == 2 ){
            return (double)(collection.get(0) + collection.get(1))/2;
        }
        else {
            int allPoints = 0;
            for(int point: collection){
                if (point >= 0) {

                    counter = counter++;
                    if (point < min){
                        min = point;
                    }
                    if (point > max){
                        max = point;
                    }
                    allPoints = allPoints + point;
                }
            }

            int totalPoints = allPoints-max-min;
            return totalPoints/(double)(counter-1);

        }
    }

    // REACH at least 95% Code Coverage (assign 3)
    // drop a student from course.
    public boolean dropStudent(String asurite) {
        boolean removeFromPoints = points.remove(asurite) != null;
        boolean removeFromStudents = students.remove(new Student(asurite, null));
        return removeFromPoints == removeFromStudents;
    }

    // REACH at least 95% Code coverage  (assign 3)
    // Students should only be added when they are not yet in the course (names (asurite member) needs to be unique)
    ArrayList<Student> students  = new ArrayList<Student>();
    public boolean addStudent(Student s) {
        if(students != null && points.putIfAbsent(s.getAsurite(), -1) == null){
            students.add(s);
            return students.add(s);
        }
        return false;
    }

    public void set_points(String name, int points) {
        if(!this.points.containsKey(name))
            addStudent(new Student(name, null));
        this.points.put(name, points);
    }

    public HashMap<String, Integer> getPoints(){
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


    public ArrayList<Student> getStudents(){
        return students;
    }

    public ArrayList<Double> calculatePercentiles(ArrayList<Integer> collection) throws NullPointerException{
        if (collection == null) {
            throw new NullPointerException();
        }


        int maxMarks = calculateMax();
        System.out.println("Test: " + maxMarks);
        double eachPercentile = 0.0;
        ArrayList<Double> percentileList = new ArrayList<Double>();

        for (int element : collection) {

            if (element > 0) {

                eachPercentile = (double)(element / maxMarks  * 100);
                percentileList.add(eachPercentile);
            }
        }
        System.out.println(percentileList);
        return percentileList;
    }

    public int calculateMax() throws NullPointerException {
        ArrayList<Integer> collection = new ArrayList<Integer>(points.values());

        if(collection == null) {
            return 0;
        }

        if(collection.size() == 1) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        for(int point: collection) {
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
     * <p>
     * Calculation is based on points member and maxPoints from Course.
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
     * @throws NullPointerException
     */
    public HashMap<String, Integer> countOccurencesLetterGrades(boolean curved) throws NullPointerException{
        HashMap<String, Integer> occur = new HashMap<String, Integer>();
        occur.put("A", 0);
        occur.put("B", 0);
        occur.put("C", 0);
        occur.put("D", 0);
        occur.put("F", 0);

        if(!curved) {
            ArrayList<Integer> collection = new ArrayList<Integer>(points.values());
            if (collection.isEmpty()) {
                throw new NullPointerException();
            }

            for (double value : collection) {
                if ((double)value/maxPoints*100 > 89.0) {
                    occur.put("A", occur.get("A") + 1);
                } else if ((double)value/maxPoints * 100 > 80.0 && value/maxPoints <= 89.0) {
                    occur.put("B", occur.get("B") + 1);
                } else if ((double)value/maxPoints * 100 > 50.0 && value/maxPoints <= 65) {
                    occur.put("C", occur.get("C") + 1);
                } else if ((double)value/maxPoints*100 > 35.0 && value/maxPoints <= 50.0) {
                    occur.put("D", occur.get("D") + 1);
                } else {
                    occur.put("F", occur.get("F") + 1);
                }
            }
        } else {
            for(String grade : curveLetterGrades().values())
                occur.put(grade, occur.get(occur) + 1);
        }
        return occur;

    }

    /** This will be needed for assignment 3 (do not change in assignment 2)
     * Calculates final grades including a curve and returns final letter grade
     * of each student.
     * <p>
     * Calculation is based on points member inherited from Course.
     * Curve is calculated by adding the positive difference between the student
     * with the highest non-negative points and maxPoints to all scores.
     * Grading Scale:
     * >  89% -> A
     * >  79% -> B
     * >  59% -> C
     * >  35% -> D
     * <= 35% -> F
     * <p>
     * eg.let points = [Alice:15, Bill:30, Cathy:45, Joe:70, Jane:80] and maxPoints = 100,
     * curve would be 100 - 80 = 20.
     * Adjusted points would be = [Alice:35, Bill:50, Cathy:65, Joe:90, Jane:100].
     * Adjusted percentages would be = [35%, 50%, 65%, 90%, 100%].
     * Returned HashMap points would be = [Alice:F, Bill:D, Cathy:C, Joe:A, Jane:A].
     *
     * @return hashmap with final letter grades for students based on curving `points`.
     * @throws NullPointerException
     */
    public Map<String, String> curveLetterGrades() throws NullPointerException { //TODO verify no side effect with points.
        return null; //implement me in assign 3 (not in assign 2)
    }





}