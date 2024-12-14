package classes;

class Course {
  private String courseID;
  private String title;
  private int creditHrs;

  public Course(String courseID, String title, int creditHrs) {
    this.courseID = courseID;
    this.title = title;
    this.creditHrs = creditHrs;
  }

  public String getCourseID() {
    return courseID;
  }

  public String getTitle() {
    return title;
  }

  public int getCreditHrs() {
    return creditHrs;
  }

  //Accessory method, quite like toString but works properly where we have to return for an array of courses instead of just one
  public String getCourseDetails() {
    return String.format("%s: %s (%d credits)", courseID, title, creditHrs);
  }

  @Override
  public String toString() {
    return String.format("Course ID: %s\nTitle: %s\nCredits: %d", courseID, title, creditHrs);
  }
}
