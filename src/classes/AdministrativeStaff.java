package classes;

import java.util.List;

public class AdministrativeStaff extends Person {
    private String staffID;
    private String role;
    private String departmentName;

    public AdministrativeStaff(String name, String email, String dateOfBirth, String staffID, String role, String departmentName) {
        super(name, email, dateOfBirth);
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

    @Override
    public String generateReport() {
        return String.format(
            "Administrative Staff Report:\n" +
            "Staff ID: %s\n" +
            "Name: %s\n" +
            "Role: %s\n" +
            "Department: %s\n",
            staffID, getName(), role, departmentName
        );
    }

    public String generateSummary(List<Person> people, List<Course> courses) {
        long studentCount = people.stream().filter(person -> person instanceof Student).count();
        long teacherCount = people.stream().filter(person -> person instanceof Teacher).count();
        int courseCount = courses.size();

        return String.format(
            "Summary Report:\n" +
            "Total Students: %d\n" +
            "Total Teachers: %d\n" +
            "Total Courses: %d\n",
            studentCount, teacherCount, courseCount
        );
    }

    @Override
    public String exportToFile() {
        // Stub for export logic, returning a placeholder message
        return String.format("Administrative Staff %s (%s) exported to file successfully.", staffID, getName());
    }

    @Override
    public String toString() {
        return String.format(
            "Administrative Staff Details:\n" +
            "%s\n" +
            "Staff ID: %s\n" +
            "Role: %s\n" +
            "Department: %s",
            super.toString(), staffID, role, departmentName
        );
    }
}
