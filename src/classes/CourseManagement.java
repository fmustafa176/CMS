package classes;

import java.util.ArrayList;
import java.util.List;

class CourseManagement extends Course {
  private Teacher assignedTeacher;
  private ArrayList<Student> enrolledStudents = new ArrayList<>();
  private ArrayList<Integer> grades = new ArrayList<>();

  public CourseManagement(String courseID, String title, int credits, Teacher assignedTeacher) {
    super(courseID, title, credits);
    this.assignedTeacher = assignedTeacher;
  }

  public void addStudent(Student student) {
    enrolledStudents.add(student);
    System.out.println("Student " + student.getStudentID() + " added to " + super.getTitle() + ".");
  }

  public void removeStudent(Student student) {
    if (enrolledStudents.remove(student)) {
      System.out.println("Student " + student.getStudentID() + " removed from " + super.getTitle() + ".");
    } 
    else {
      System.out.println("Student " + student.getStudentID() + " is not enrolled in " + super.getTitle() + ".");
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
      System.out.println("No grades available for the course " + super.getTitle() + ".");
      return;
    }
    int total = 0;
    for (int grade : grades) {
      total += grade;
    }
    double average = (double) total / grades.size();
    System.out.println("Average grade for " + super.getTitle() + ": " + average);
  }
}
