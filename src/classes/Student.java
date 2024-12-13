package classes;

import java.util.ArrayList;


public class Student extends Person{
  private String studentID;
  private String address;
  private String dateOfBirth;
  private ArrayList<Course> enrolledCourses;

  public Student(String name, String email, String dateOfBirth, String studentId, String studentAddress) { 
    super(name, email, date_of_birth);
    this.studentId = studentId;
    this.studentAddress = studentAddress;
    this.dateOfBirth = dateOfBirth;
    this.enrolledCourses = new ArrayList<>();
  }

  public String getStudentID() {
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

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }


  public void enrollInCourse(Course course) {
    enrolledCourses.add(course);
    System.out.println("Student " + studentID + " successfully enrolled in " + course.getTitle() + ".");
  }

  public void displayCourses() {
    if (enrolledCourses.isEmpty()) {
      System.out.println("No courses enrolled yet.");
      return;
    }

    System.out.println("Courses enrolled by student " + studentID + ":");
    for (Course course : enrolledCourses) {
      System.out.println(course.getCourseDetails());
    }
  }
}

