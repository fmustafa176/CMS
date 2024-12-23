package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import classes.*;
import storage.University;

public class StudentPage extends JFrame {
    private JTextField studentIDField, studentNameField, emailField, dobField, addressField;
    private JButton searchStudentButton, displayCoursesButton, backButton, addStudentButton;
    private JList<Course> courseList;
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
        courseList = new JList<>(availableCourses.toArray(new Course[0]));
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        searchStudentButton = createStyledButton("Search Student");
        displayCoursesButton = createStyledButton("Display Courses");
        backButton = createStyledButton("Back");
        addStudentButton = createStyledButton("Add Student");

        // Add action listeners
        searchStudentButton.addActionListener(e -> new SearchStudentFrame());
        displayCoursesButton.addActionListener(e -> new DisplayCoursesFrame());
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
        add(new JLabel("Select Courses:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        add(new JScrollPane(courseList), gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(addStudentButton, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        add(searchStudentButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(displayCoursesButton, gbc);
        gbc.gridx = 1; gbc.gridy = 7;
        add(backButton, gbc);
    }

    private void addNewStudent() {
        try {
            String name = studentNameField.getText().trim();
            String studentID = studentIDField.getText().trim();
            String email = emailField.getText().trim();
            String dob = dobField.getText().trim();
            String address = addressField.getText().trim();
            ArrayList<Course> selectedCourses = new ArrayList<>(courseList.getSelectedValuesList());

            // Validation (similar to before)
            if (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
                throw new IllegalArgumentException("Name must contain only letters and spaces.");
            }
            if (studentID.isEmpty() || !studentID.matches("\\d+")) {
                throw new IllegalArgumentException("Student ID must contain only digits.");
            }
            if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                throw new IllegalArgumentException("Invalid email format.");
            }
            if (!dob.isEmpty() && !dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Date of Birth must be in YYYY-MM-DD format.");
            }

            // Create a new student object
            Student newStudent = new Student(
                name,
                email.isEmpty() ? "N/A" : email,
                dob.isEmpty() ? "N/A" : dob,
                studentID,
                address.isEmpty() ? "N/A" : address
            );
            for (Course course : selectedCourses){
                newStudent.enrollInCourse(course);
            }

            // Add to database
            studentsDatabase.add(newStudent);
            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    // Inner class for Search Student frame
    private class SearchStudentFrame extends JFrame {
        public SearchStudentFrame() {
            setTitle("Search Student");
            setSize(400, 300);
            setLocationRelativeTo(StudentPage.this);
            setLayout(new BorderLayout());
            JTextField nameField = new JTextField(20);
            JButton searchButton = createStyledButton("Search");
            add(new JLabel("Enter Student Name:"), BorderLayout.NORTH);
            add(nameField, BorderLayout.CENTER);
            add(searchButton, BorderLayout.SOUTH);

            searchButton.addActionListener(e -> {
                String result = University.searchStudentByName(nameField.getText().trim());
                JOptionPane.showMessageDialog(this, result, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            });

            setVisible(true);
        }
    }

    // Inner class for Display Courses frame
    private class DisplayCoursesFrame extends JFrame {
        public DisplayCoursesFrame() {
            setTitle("Display Courses");
            setSize(400, 300);
            setLocationRelativeTo(StudentPage.this);
            setLayout(new BorderLayout());
            JTextField idField = new JTextField(20);
            JButton displayButton = createStyledButton("Display");
            add(new JLabel("Enter Student ID:"), BorderLayout.NORTH);
            add(idField, BorderLayout.CENTER);
            add(displayButton, BorderLayout.SOUTH);

            displayButton.addActionListener(e -> {
                String studentID = idField.getText().trim();
                Student student = studentsDatabase.stream()
                                                  .filter(s -> s.getID().equals(studentID))
                                                  .findFirst()
                                                  .orElse(null);
                if (student != null) {
                    String courses = student.getEnrolledCourses();
                    JOptionPane.showMessageDialog(this, courses, "Enrolled Courses", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentPage().setVisible(true));
    }
}
