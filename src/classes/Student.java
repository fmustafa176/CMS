package classes;

import java.util.ArrayList;

import storage.University;

public class Student extends Person {
    private String studentID;
    private String address;
    private ArrayList<Course> enrolledCourses;
    private static int totalStudents = 0;

    public Student(String name, String email, String dateOfBirth, String studentID, String address) {
        super(name, email, dateOfBirth);
        this.studentID = studentID;
        this.address = address;
        this.enrolledCourses = new ArrayList<>();
        addStudent();
        University.getStudentRepo().add(this);
    }

    public String getStudentID() {
        return studentID;
    }

    public String getID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void addStudent() {
        totalStudents++;
    }

    public static void setStudent(int a) {
        totalStudents = a;
    }

    public static void removeStudent() {
        if (totalStudents > 0) {
            totalStudents--;
        }
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    @Override
    public String generateReport() {
        return String.format("Student Report:\nName: %s\nID: %s\nAddress: %s\nDate of Birth: %s\nEnrolled Courses:\n%s",
                getName(), studentID, address, getDateOfBirth(), getEnrolledCourses());
    }

    @Override
    public String exportToFile() {
        return "Report for student " + studentID + " exported to file.";
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public String getEnrolledCourses() {
        if (enrolledCourses.isEmpty()) {
            return "No courses enrolled yet.";
        }

        StringBuilder coursesString = new StringBuilder();
        for (Course course : enrolledCourses) {
            coursesString.append(course.getCourseDetails()).append("\n");
        }
        return coursesString.toString().trim();
    }

    @Override
    public String toString() {
        return String.format("Student Details:\n%s\nStudent ID: %s\nAddress: %s\nDate of Birth: %s\nEnrolled Courses:\n%s",
                super.toString(), studentID, address, getDateOfBirth(), getEnrolledCourses());
    }
}
