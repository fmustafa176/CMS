package classes;

import java.util.ArrayList;


public class Student extends Person{
  private String studentID;
  private String address;
  private ArrayList<Course> enrolledCourses;

  public Student(String name, String email, String dateOfBirth, String studentId, String studentAddress) { 
	super(name, email, dateOfBirth);
	this.studentID = studentId;
	this.address = studentAddress;
	this.enrolledCourses = new ArrayList<Course>();
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

  public void enrollInCourse(Course course) {
	enrolledCourses.add(course);
	System.out.println("Student " + studentID + " successfully enrolled in " + course.getTitle() + ".");
  }

  public String getEnrolledCourses() {
	if (enrolledCourses.isEmpty()) {
		return "No courses enrolled yet.";
	}

	StringBuilder coursesString = new StringBuilder("Courses enrolled by student " + studentID + ":\n");
	for (Course course : enrolledCourses) {
	  coursesString.append(course.getCourseDetails()).append("\n");
	}
	return coursesString.toString().trim();
  }

  @Override
  public String toString() {
    return String.format("%s\nStudent ID: %s\nAddress: %s\nDate of Birth: %s\nEnrolled Courses:\n%s",
       super.toString(), studentID, address, dateOfBirth, getEnrolledCourses());
    }

}

