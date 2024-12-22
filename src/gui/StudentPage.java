package gui;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;
import java.util.*;
import classes.*;
import storage.University;

class StudentPage extends JFrame {
    private JTextField studentIDField, studentNameField;
    private JButton enrollButton, displayCoursesButton, backButton;
    private JComboBox<Course> courseComboBox;
    private ArrayList<Course> availableCourses; // List to hold available courses
    private HashMap<String, Student> studentsDatabase; // To manage students by their ID

    public StudentPage() {
        setTitle("Student Management");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true); // Allow window resizing

        // Initialize components
        studentIDField = new JTextField(20);
        studentNameField = new JTextField(20);
        courseComboBox = new JComboBox<>();
        enrollButton = createButton("Enroll in Course", "Enroll student in the selected course");
        displayCoursesButton = createButton("Display Courses", "Display the list of courses a student is enrolled in");
        backButton = createButton("Back", "Go back to the main menu");

        // Initialize the courses and students database
        availableCourses = new ArrayList<>(University.getCourseRepo().getAll());
        
        // Populate course combo box
        for (Course course : availableCourses) {
            courseComboBox.addItem(course);
        }

        // Add actions for buttons
        enrollButton.addActionListener(e -> enrollStudentInCourse());
        displayCoursesButton.addActionListener(e -> displayEnrolledCourses());
        backButton.addActionListener(e -> goBackToMainMenu());

        // Layout setup using GridBagLayout for better alignment
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(studentIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(studentNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Select Course:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        add(courseComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(enrollButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        add(displayCoursesButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(backButton, gbc);
    }

    private void enrollStudentInCourse() {
        String studentID = studentIDField.getText().trim();
        String studentName = studentNameField.getText().trim();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();

        if (studentID.isEmpty() || studentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If student doesn't exist in the system, create a new student
        if (!studentsDatabase.containsKey(studentID)) {
            Student newStudent = new Student(studentName, "Email Placeholder", "DOB Placeholder", studentID, "Address Placeholder");
            studentsDatabase.put(studentID, newStudent);
        }

        Student student = studentsDatabase.get(studentID);
        if (selectedCourse != null) {
            student.enrollInCourse(selectedCourse);
            JOptionPane.showMessageDialog(this, "Student " + studentID + " successfully enrolled in " + selectedCourse.getTitle());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid course.");
        }
    }

    private void displayEnrolledCourses() {
        String studentID = studentIDField.getText().trim();
        Student student = studentsDatabase.get(studentID);
        if (student != null) {
            String courses = student.getEnrolledCourses();
            
            JOptionPane.showMessageDialog(this, courses, "Enrolled Courses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No student found with the given ID.");
        }
    }

    private void goBackToMainMenu() {
        HomePage homePage = new HomePage(); // Replace with actual Main Menu class
        homePage.setVisible(true);
        this.dispose(); // Close the current window
    }

    private JButton createButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setToolTipText(tooltip);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentPage().setVisible(true));
    }
}
