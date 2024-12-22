package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import classes.*;

class CoursePage extends JFrame {
    private JTextField courseIDField, courseTitleField, courseCreditsField;
    private JButton addStudentButton, removeStudentButton, calculateAvgButton, backButton;

    private ArrayList<Student> enrolledStudents;
    private Teacher assignedTeacher;

    public CoursePage() {
        setTitle("Course Management");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enrolledStudents = new ArrayList<>();

        // Initialize components
        courseIDField = createTextField("Enter Course ID");
        courseTitleField = createTextField("Enter Course Title");
        courseCreditsField = createTextField("Enter Course Credits");

        addStudentButton = createButton("Add Student", "Add a new student to the course");
        removeStudentButton = createButton("Remove Student", "Remove a student from the course");
        calculateAvgButton = createButton("Calculate Average Grade", "Calculate the average grade of enrolled students");
        backButton = createButton("Back", "Return to the main menu");

        // Action Listeners
        addStudentButton.addActionListener(e -> addStudentToCourse());
        removeStudentButton.addActionListener(e -> removeStudentFromCourse());
        calculateAvgButton.addActionListener(e -> calculateAverageGrade());
        backButton.addActionListener(e -> goBackToMainMenu());

        // Panel for course details
        JPanel coursePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        coursePanel.setBorder(BorderFactory.createTitledBorder("Course Details"));
        coursePanel.add(new JLabel("Course ID:"));
        coursePanel.add(courseIDField);
        coursePanel.add(new JLabel("Course Title:"));
        coursePanel.add(courseTitleField);
        coursePanel.add(new JLabel("Credits:"));
        coursePanel.add(courseCreditsField);

        // Panel for actions
        JPanel actionPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        actionPanel.add(addStudentButton);
        actionPanel.add(removeStudentButton);
        actionPanel.add(calculateAvgButton);

        // Back button panel
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.add(backButton);

        // Main layout
        setLayout(new BorderLayout(15, 15));
        add(coursePanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        // Styling
        getContentPane().setBackground(new Color(245, 245, 245));
        pack();
    }

    private JTextField createTextField(String tooltip) {
        JTextField field = new JTextField(20);
        field.setToolTipText(tooltip);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }

    private JButton createButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setToolTipText(tooltip);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void addStudentToCourse() {
        String studentName = JOptionPane.showInputDialog(this, "Enter Student Name:");
        String studentID = JOptionPane.showInputDialog(this, "Enter Student ID:");

        if (studentName == null || studentName.isEmpty() || studentID == null || studentID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student Name and ID must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student newStudent = new Student(studentName, "Email Placeholder", "DOB Placeholder", studentID, "Address Placeholder");
        enrolledStudents.add(newStudent);
        JOptionPane.showMessageDialog(this, "Student " + studentName + " added to the course.");
    }

    private void removeStudentFromCourse() {
        String studentID = JOptionPane.showInputDialog(this, "Enter Student ID to Remove:");

        if (studentID == null || studentID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student ID must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean removed = enrolledStudents.removeIf(student -> student.getStudentID().equals(studentID));
        if (removed) {
            JOptionPane.showMessageDialog(this, "Student with ID " + studentID + " removed from the course.");
        } else {
            JOptionPane.showMessageDialog(this, "No student found with ID " + studentID + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateAverageGrade() {
        if (enrolledStudents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students enrolled in the course.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalGrades = 0.0;
        for (Student student : enrolledStudents) {
            totalGrades += Math.random() * 100; // Placeholder for actual grade calculation
        }

        double averageGrade = totalGrades / enrolledStudents.size();
        JOptionPane.showMessageDialog(this, String.format("Average grade for the course: %.2f", averageGrade), "Average Grade", JOptionPane.INFORMATION_MESSAGE);
    }

    private void goBackToMainMenu() {
        int response = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to return to the main menu?", 
            "Confirm Exit", 
            JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            HomePage mainMenu = new HomePage(); // Replace with actual Main Menu class
            mainMenu.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CoursePage().setVisible(true));
    }
}
