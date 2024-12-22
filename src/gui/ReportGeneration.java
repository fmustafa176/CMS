import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class ReportGeneration extends JFrame {
    private JButton studentReportButton, teacherReportButton, systemStatsButton, exportButton, backButton;
    private JTextArea reportArea;
    private JScrollPane scrollPane;

    public ReportGeneration() {
        setTitle("Report Generation");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure app closes when window is closed
        setResizable(true);

        // Initialize components
        studentReportButton = createButton("Generate Student Enrollment Report", "Generate a report of student enrollments.");
        teacherReportButton = createButton("Generate Teacher Workload Report", "Generate a report of teacher workloads.");
        systemStatsButton = createButton("Generate System Statistics", "Generate overall system statistics.");
        exportButton = createButton("Export Report", "Export the generated report to a file.");
        backButton = createButton("Back", "Go back to the main menu.");

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Arial", Font.PLAIN, 12)); // Set font for better readability
        scrollPane = new JScrollPane(reportArea);

        studentReportButton.addActionListener(e -> generateStudentEnrollmentReport());
        teacherReportButton.addActionListener(e -> generateTeacherWorkloadReport());
        systemStatsButton.addActionListener(e -> generateSystemStatsReport());
        exportButton.addActionListener(e -> exportReportToFile());
        backButton.addActionListener(e -> goBackToMainMenu());

        // Panel for buttons
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        panel.add(studentReportButton);
        panel.add(teacherReportButton);
        panel.add(systemStatsButton);
        panel.add(exportButton);
        panel.add(backButton);

        // Layout setup
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
        List<Course> courses = University.getAllCourses(); // Assuming a method that returns all courses

        for (Course course : courses) {
            report.append("Course: ").append(course.getTitle()).append("\n");
            List<Student> enrolledStudents = course.getEnrolledStudents();
            for (Student student : enrolledStudents) {
                report.append("  Student ID: ").append(student.getStudentID())
                      .append(", Name: ").append(student.getName()).append("\n");
            }
            report.append("\n");
        }
        reportArea.setText(report.length() > 0 ? report.toString() : "No enrollment data available.");
    }

    private void generateTeacherWorkloadReport() {
        StringBuilder report = new StringBuilder();
        List<Teacher> teachers = University.getAllTeachers(); // Assuming a method that returns all teachers

        for (Teacher teacher : teachers) {
            report.append("Teacher: ").append(teacher.getName()).append("\n");
            List<Course> courses = teacher.getCourses();
            for (Course course : courses) {
                report.append("  Course: ").append(course.getTitle()).append("\n");
            }
            report.append("\n");
        }
        reportArea.setText(report.length() > 0 ? report.toString() : "No workload data available.");
    }

    private void generateSystemStatsReport() {
        StringBuilder report = new StringBuilder();
        int totalStudents = University.getTotalStudents(); // Assuming a method that returns total students
        int totalTeachers = University.getTotalTeachers(); // Assuming a method that returns total teachers
        int totalCourses = University.getTotalCourses(); // Assuming a method that returns total courses

        report.append("System Statistics:\n")
              .append("Total Students: ").append(totalStudents).append("\n")
              .append("Total Teachers: ").append(totalTeachers).append("\n")
              .append("Total Courses: ").append(totalCourses).append("\n");

        reportArea.setText(report.length() > 0 ? report.toString() : "No system data available.");
    }

    private void exportReportToFile() {
        String reportContent = reportArea.getText();
        if (reportContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No report to export.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Report");
        fileChooser.setSelectedFile(new File("Report.txt"));
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(reportContent);
                JOptionPane.showMessageDialog(this, "Report exported successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error exporting report: " + e.getMessage());
            }
        }
    }

    private void goBackToMainMenu() {
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportGeneration().setVisible(true));
    }
}
