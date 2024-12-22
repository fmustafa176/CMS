package storage;

import java.util.ArrayList;
import java.util.List;

import classes.*;

public class University {
    private static Repository<Student> studentRepo = new Repository<>();
    private static Repository<Teacher> teacherRepo = new Repository<>();
    private static Repository<AdministrativeStaff> adminRepo = new Repository<>();
    private static Repository<Course> courseRepo = new Repository<>();

    public static void loadData() {
        FileHandling.loadData("university_data.ser", studentRepo, teacherRepo, adminRepo, courseRepo);
    }

    public static void saveData() {
        FileHandling.saveData("university_data.ser", studentRepo, teacherRepo, adminRepo, courseRepo);
    }

    public static Repository<Student> getStudentRepo() {
        return studentRepo;
    }

    public static Repository<Teacher> getTeacherRepo() {
        return teacherRepo;
    }

    public static Repository<AdministrativeStaff> getAdminRepo() {
        return adminRepo;
    }

    public static Repository<Course> getCourseRepo() {
        return courseRepo;
    }

    public static int getTotalStudents() {
        return studentRepo.getAll().size();
    }

    public static int getTotalCourses() {
        return courseRepo.getAll().size();
    }

    public static int getTotalTeachers() {
        return teacherRepo.getAll().size();
    }

    public static String displaySystemStats() {
        return "Total Students: " + getTotalStudents() + "\n" +
               "Total Teachers: " + getTotalTeachers() + "\n" +
               "Total Courses: " + getTotalCourses();
    }

    public static String searchStudentByName(String name) {
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : studentRepo.getAll()) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingStudents.add(student);
            }
        }
        if (matchingStudents.isEmpty()) {
            return "No matching students found.";
        }

        StringBuilder result = new StringBuilder("Found students:\n");
        for (Student student : matchingStudents) {
            result.append(student.getName()).append("\n");
        }
        return result.toString().trim();
    }

    // Method to filter courses by minimum credits
    public static String filterCoursesByCredits(int minCredits) {
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : courseRepo.getAll()) {
            if (course.getCreditHrs() >= minCredits) {
                filteredCourses.add(course);
            }
        }
        if (filteredCourses.isEmpty()) {
            return "No courses with at least " + minCredits + " credits found.";
        }

        StringBuilder result = new StringBuilder("Courses with at least " + minCredits + " credits:\n");
        for (Course course : filteredCourses) {
            result.append(course.getTitle()).append("\n");
        }
        return result.toString().trim();
    }
}