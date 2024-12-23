package classes;

import java.util.ArrayList;

public class CourseManagement extends Course {
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

  public boolean removeStudent(String name) {
    for (int i = 0; i < enrolledStudents.size(); i++) {
        Student student = enrolledStudents.get(i);
        if (student.getName().equalsIgnoreCase(name)) {
            enrolledStudents.remove(i);
            grades.remove(i);
            return true;
        }
    }
    return false;
}


  public void addGrade(Student student, int grade) {
    if (enrolledStudents.contains(student)) {
      grades.add(grade);
    } 
    else {
      System.out.println("Student " + student.getStudentID() + " is not enrolled in the course.");
    }
  }

  public String getGrades() {
    if (enrolledStudents.isEmpty() || grades.isEmpty()) {
        return "No grades available. No students enrolled or grades not assigned yet.";
    }

    StringBuilder gradesReport = new StringBuilder("Grades for Enrolled Students:\n");
    for (int i = 0; i < enrolledStudents.size(); i++) {
        Student student = enrolledStudents.get(i);
        int grade = grades.get(i);
        gradesReport.append(String.format("Student ID: %s, Name: %s, Grade: %d\n",
                student.getStudentID(), student.getName(), grade));
    }
    return gradesReport.toString();
  }


  public double calculateAverageGrade() {
    if (grades.isEmpty()) {
        throw new IllegalStateException("No grades available for the course " + super.getTitle() + ".");
    }
    int total = 0;
    for (int grade : grades) {
        total += grade;
    }
    return (double) total / grades.size();
  }

}
