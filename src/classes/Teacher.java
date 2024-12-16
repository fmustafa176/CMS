package classes;

import java.util.ArrayList;

class Teacher extends Person{
  private String teacherID;
  private String specialization;
  private ArrayList<Course> listOfCoursesTaught;

  public Teacher(String name, String email, String dateOfbirth, String teacherID, int age, String specialization){
    super(name, email, dateOfbirth);
    this.teacherID = teacherID;
    this.specialization = specialization;
    this.listOfCoursesTaught = new ArrayList<>();
  }

  public String getteacherID(){
    return teacherID;
  }

  public String getSpecialization(){
    return specialization;
  }

  public void setteacherID(String teacherID){
    this.teacherID = teacherID;
  }

  public void setSpecialization(String specialization){
    this.specialization = specialization;
  }

  public void assignCourse(Course course) {
    listOfCoursesTaught.add(course);
    System.out.println("Teacher " + getName() + " assigned to teach " + course.getTitle() + ".");
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
    return String.format("Teacher Details: %s\nTeacher ID: %s\nSpecialization: %s\nCourses Taught:\n%s",
      super.toString(), teacherID, specialization, getTaughtCourses());
  }

}
