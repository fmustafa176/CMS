package classes;

import java.util.ArrayList;

import storage.University;

public class Teacher extends Person {
    private String teacherID;
    private String specialization;
    private ArrayList<Course> listOfCoursesTaught;
    private static int totalTeachers = 0;

    public Teacher(String name, String email, String dateOfBirth, String teacherID, int age, String specialization) {
        super(name, email, dateOfBirth);
        this.teacherID = teacherID;
        this.specialization = specialization;
        this.listOfCoursesTaught = new ArrayList<>();
        addTeacher();
        University.getTeacherRepo().add(this);
    }

    public static void addTeacher() {
        totalTeachers++;
    }

    public static void removeTeacher() {
        if (totalTeachers > 0) 
            totalTeachers--;
    }

    public static void setTeacher(int a) {
        totalTeachers = a;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getID() {
        return teacherID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void assignCourse(Course course) {
        listOfCoursesTaught.add(course);
    }

    public String getTaughtCourses() {
        if (listOfCoursesTaught.isEmpty()) {
            return "No courses assigned yet.";
        }

        StringBuilder coursesString = new StringBuilder();
        for (Course course : listOfCoursesTaught) {
            coursesString.append(course.getCourseDetails()).append("\n");
        }
        return coursesString.toString().trim();
    }

    @Override
    public String toString() {
        return String.format("Teacher Details: %s, Teacher ID: %s, Specialization: %s ,Courses Taught: %s",
            super.toString(), teacherID, specialization, getTaughtCourses());
    }

    @Override
    public String generateReport() {
        return String.format("Teacher Report:\nName: %s\nID: %s\nSpecialization: %s\nCourses Taught:\n%s",
            getName(), teacherID, specialization, getTaughtCourses());
    }

    @Override
    public String exportToFile() {
        return "Report for " + teacherID + " exported to file.";
    }
}
