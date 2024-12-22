package classes;

import java.util.ArrayList;

public class AdministrativeStaff extends Person{
  private String staffID;
  private String role;
  private String departmentName;

  public AdministrativeStaff(String name, String email, String dateOfbirth, String staffID, String role, String departmentName) {
    super(name, email, dateOfbirth);
    this.staffID = staffID;
    this.role = role;
    this.departmentName = departmentName;
  }

  public String getStaffID() {
    return staffID;
  }

  public String getID() {
    return staffID;
  }

  public String getRole() {
    return role;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public String generateReport(ArrayList<Person> people, ArrayList<Course> courses) {
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
    return String.format(
        "%s\nStaff ID: %s\n  role: %s\n  departmentName: %s",
        super.toString(), staffID, role, departmentName
    );
  }

}