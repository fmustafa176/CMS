package classes;

class Course {
  private String courseID;
  private String title;
  private int credits;

  public Course(String courseID, String title, int credits) {
    this.courseID = courseID;
    this.title = title;
    this.credits = credits;
  }

  public String getCourseID() {
    return courseID;
  }

  public String getTitle() {
    return title;
  }

  public int getCredits() {
    return credits;
  }

  public void displayCourse() {
    System.out.println(courseID + ": " + title + " (" + credits + " credits)");
  }
}
