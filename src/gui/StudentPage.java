package gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import classes.*;
import storage.University;

public class StudentPage extends JFrame {
    private JTextField studentIDField, studentNameField, emailField, dobField, addressField;
    private JButton enrollButton, displayCoursesButton, backButton, addStudentButton;
    private JComboBox<Course> courseComboBox;
    private ArrayList<Course> availableCourses;
    private ArrayList<Student> studentsDatabase;

    public StudentPage() {
        setTitle("Student Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Initialize data
        this.availableCourses = (ArrayList<Course>) University.getCourseRepo().getAll();
        this.studentsDatabase = (ArrayList<Student>) University.getStudentRepo().getAll();

        // Initialize GUI components
        studentIDField = new JTextField(20);
        studentNameField = new JTextField(20);
        emailField = new JTextField(20);
        dobField = new JTextField(20);
        addressField = new JTextField(20);
        courseComboBox = new JComboBox<>();
        enrollButton = createStyledButton("Enroll in Course");
        displayCoursesButton = createStyledButton("Display Courses");
        backButton = createStyledButton("Back");
        addStudentButton = createStyledButton("Add Student");

        // Populate course combo box
        for (Course course : availableCourses) {
            courseComboBox.addItem(course);
        }

        // Add action listeners
        enrollButton.addActionListener(e -> enrollStudentInCourse());
        displayCoursesButton.addActionListener(e -> displayEnrolledCourses());
        addStudentButton.addActionListener(e -> addNewStudent());
        backButton.addActionListener(e -> goBackToMenu());

        // Layout setup
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(studentNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(studentIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        add(dobField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Select Course:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        add(courseComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(addStudentButton, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        add(enrollButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(displayCoursesButton, gbc);
        gbc.gridx = 1; gbc.gridy = 7;
        add(backButton, gbc);
    }

    private void addNewStudent() {
        String name = studentNameField.getText().trim();
        String studentID = studentIDField.getText().trim();
        String email = emailField.getText().trim();
        String dob = dobField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty() || studentID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Student ID are required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student newStudent = new Student(name, email.isEmpty() ? "N/A" : email,
                dob.isEmpty() ? "N/A" : dob, studentID, address.isEmpty() ? "N/A" : address);

        studentsDatabase.add(newStudent);
        JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void enrollStudentInCourse() {
        String studentID = studentIDField.getText().trim();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
    
        if (studentID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide a valid Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        Student student = null;
        for (Student s : studentsDatabase) {
            if (s.getID().equals(studentID)) {
                student = s;
                break;
            }
        }
    
        if (student != null && selectedCourse != null) {
            student.enrollInCourse(selectedCourse);
            CourseManagement m = new CourseManagement(selectedCourse.getCourseID(), selectedCourse.getTitle(), selectedCourse.getCreditHrs(), null);
            m.addStudent(student);
            JOptionPane.showMessageDialog(this, "Student successfully enrolled in " + selectedCourse.getTitle());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found or invalid course selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void displayEnrolledCourses() {
        String studentID = studentIDField.getText().trim();
    
        if (studentID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide a valid Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Search for the student in the ArrayList
        Student student = null;
        for (Student s : studentsDatabase) {
            if (s.getID().equals(studentID)) {
                student = s;
                break;
            }
        }
    
        if (student != null) {
            String courses = student.getEnrolledCourses();
            if (courses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "The student is not enrolled in any courses.", "Enrolled Courses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, courses, "Enrolled Courses", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No student found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void goBackToMenu() {
        new HomePage().setVisible(true);
        dispose();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentPage().setVisible(true);
        });
    }
}
