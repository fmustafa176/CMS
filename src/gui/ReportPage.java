package gui;

import javax.swing.*;

import java.awt.*;
// import java.awt.event.*;
// import java.io.*;
import java.util.*;
import java.util.List;

import classes.*;
import storage.*;

class ReportPage extends JFrame {
    private JButton studentReportButton, teacherReportButton, systemStatsButton, backButton;
    private JTextArea reportArea;
    private JScrollPane scrollPane;

    public ReportPage() {
        setTitle("Report Generation");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(true);

        studentReportButton = createButton("Generate Student Enrollment Report", "Generate a report of student enrollments.");
        teacherReportButton = createButton("Generate Teacher Workload Report", "Generate a report of teacher workloads.");
        systemStatsButton = createButton("Generate System Statistics", "Generate overall system statistics.");
        backButton = createButton("Back", "Go back to the main menu.");

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Arial", Font.PLAIN, 12)); // Set font for better readability
        scrollPane = new JScrollPane(reportArea);

        studentReportButton.addActionListener(e -> generateStudentEnrollmentReport());
        teacherReportButton.addActionListener(e -> generateTeacherWorkloadReport());
        systemStatsButton.addActionListener(e -> generateSystemStatsReport());
        backButton.addActionListener(e -> goBackToMainMenu());

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        panel.add(studentReportButton);
        panel.add(teacherReportButton);
        panel.add(systemStatsButton);
        panel.add(backButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setToolTipText(tooltip);
        button.setPreferredSize(new Dimension(200, 40)); // Set button size for consistency
        return button;
    }

    private void generateStudentEnrollmentReport() {
        StringBuilder report = new StringBuilder();
        ArrayList<Course> courses = new ArrayList<>(University.getCourseRepo().getAll());
        reportArea.setText(getName());
        for (Course course : courses) {
            report.append("Course: ").append(course.getTitle()).append("\n");
            report.append(course.getCourseDetails());
            
        }
        reportArea.setText(report.length() > 0 ? report.toString() : "No enrollment data available.");
    }

    private void generateTeacherWorkloadReport() {
        StringBuilder report = new StringBuilder();
        List<Teacher> teachers = new ArrayList<>(University.getTeacherRepo().getAll()); 

        for (Teacher teacher : teachers) {
            report.append("Teacher: ").append(teacher.getName()).append("\n");
            report.append(teacher.getTaughtCourses());
        }
        reportArea.setText(report.length() > 0 ? report.toString() : "No workload data available.");
    }

    private void generateSystemStatsReport() {
        StringBuilder report = new StringBuilder();
        int totalStudents = University.getTotalStudents(); 
        int totalTeachers = University.getTotalTeachers(); 
        int totalCourses = University.getTotalCourses(); 

        report.append("System Statistics:\n")
              .append("Total Students: ").append(totalStudents).append("\n")
              .append("Total Teachers: ").append(totalTeachers).append("\n")
              .append("Total Courses: ").append(totalCourses).append("\n");

        reportArea.setText(report.length() > 0 ? report.toString() : "No system data available.");
    }

    private void goBackToMainMenu() {
        HomePage menu = new HomePage();
        menu.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportPage().setVisible(true));
    }
}
