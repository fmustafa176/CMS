package classes;

import java.util.ArrayList;
import java.util.List;

class CourseManagement {
  private String courseID;
  private String title;
  private int credits;
  private Teacher assignedTeacher;
  private ArrayList<Student> enrolledStudents = new ArrayList<>();
  private ArrayList<Integer> grades = new ArrayList<>();

  public CourseManagement(String courseID, String title, int credits, Teacher assignedTeacher) {
    this.courseID = courseID;
    this.title = title;
    this.credits = credits;
    this.assignedTeacher = assignedTeacher;
  }

  public void addStudent(Student student) {
    enrolledStudents.add(student);
    System.out.println("Student " + student.getStudentID() + " added to " + title + ".");
  }

  public void removeStudent(Student student) {
    if (enrolledStudents.remove(student)) {
      System.out.println("Student " + student.getStudentID() + " removed from " + title + ".");
    } 
    else {
      System.out.println("Student " + student.getStudentID() + " is not enrolled in " + title + ".");
    }
  }

  public void addGrade(Student student, int grade) {
    if (enrolledStudents.contains(student)) {
      grades.add(grade);
    } 
    else {
      System.out.println("Student " + student.getStudentID() + " is not enrolled in the course.");
    }
  }

  public void calculateAverageGrade() {
    if (grades.isEmpty()) {
      System.out.println("No grades available for the course " + title + ".");
      return;
    }
    int total = 0;
    for (int grade : grades) {
      total += grade;
    }
    double average = (double) total / grades.size();
    System.out.println("Average grade for " + title + ": " + average);
  }
}
