package classes;

import java.util.ArrayList;

class Teacher extends Person{
  private String TeacherId;
  private String specialization;
  private ArrayList<Course> listOfCoursesTaught;

  public Teacher(String name, String email, String dateOfbirth, String teacherId, int age, String specialization){
    super(name, email, dateOfbirth);
    this.TeacherId = teacherId;
    this.Specialization = specialization;
    this.listOfCoursesTaught = new ArrayList<>();
  }

  public String getTeacherId(){
    return TeacherId;
  }

  public String getSpecialization(){
    return specialization;
  }

  public void setTeacherId(String teacherId){
    this.TeacherId = teacherId;
  }

  public void setSpecialization(String specialization){
    this.specialization = specialization;
  }

  public void assignCourse(Course course) {
    listOfCoursesTaught.add(course);
    System.out.println("Teacher " + getName() + " assigned to teach " + course.getTitle() + ".");
  }

  public void displayCourses() {
    if (listOfCoursesTaught.isEmpty()) {
      System.out.println("No courses assigned yet.");
      return;
    }

    System.out.println("Courses taught by teacher " + getName() + ":");
    for (Course course : listOfCoursesTaught) {
      System.out.println(course.getCourseDetails());
    }
  }

  @Override
  public String getDetails() {
    return super.getDetails() + "| Teacher ID: " + teacherId + "| Specialization: " + specialization;
  } 
}
