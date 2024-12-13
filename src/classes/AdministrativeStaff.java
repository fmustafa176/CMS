package classes;

class AdministrativeStaff extends Person{
  private int StaffID;
  private String role;
  private String departmentName;

  public AdministrativeStaff(String name, String email, String dateOfbirth, int staffID, String role, String departmentName) {
    super(name, email, dateOfbirth);
    this.staffID = staffID;
    this.role = role;
    this.departmentName = departmentName;
  }

  public int getStaffID() {
    return staffID;
  }

  public String getRole() {
    return role;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public String generateReport(List<Person> people, List<Course> courses) {
    int studentCount = 0;
    int teacherCount = 0;

    for (Person person : people) {
      if (person instanceof Student) {
        studentCount++;
      } 
      else if (person instanceof Teacher) {
        teacherCount++;
      }
    }

    String report = "Administrative Report:\n";
    report += "Total Students: " + studentCount + "\n";
    report += "Total Teachers: " + teacherCount + "\n";
    report += "Total Courses: " + courses.size() + "\n";

    return report;
  }

  @Override
  public String toString() {
    return super.getDetails() + "| staffID= " + staffID + "| role= " + role  + "| departmentName= " + departmentName;
  }
}