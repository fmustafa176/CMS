package classes;

import java.io.Serializable;

import storage.University;

public class Course implements Serializable, GetIdentity, Reportable {
    private String courseID;
    private String title;
    private int creditHrs;
    private static int totalCourses;

    public Course(String courseID, String title, int creditHrs) {
        this.courseID = courseID;
        this.title = title;
        this.creditHrs = creditHrs;
        addCourse();
        University.getCourseRepo().add(this);
    }

    public static void addCourse() {
        totalCourses++;
    }

    public static void removeCourse() {
        if (totalCourses > 0) 
            totalCourses--;
    }

    public static void setCourses(int a) {
        totalCourses = a;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    public int getCreditHrs() {
        return creditHrs;
    }

    @Override
    public String generateReport() {
        return String.format("Course Report:\nCourse ID: %s\nTitle: %s\nCredits: %d", 
                              courseID, title, creditHrs);
    }

    @Override
    public String exportToFile() {
        return String.format("Course %s (%s) exported to file successfully.", courseID, title);
    }

    public String getCourseDetails() {
        return String.format("%s: %s (%d credits)", courseID, title, creditHrs);
    }

    @Override
    public String toString() {
        return String.format("Course ID: %s, Title: %s, Credits: %d", courseID, title, creditHrs);
    }
}
