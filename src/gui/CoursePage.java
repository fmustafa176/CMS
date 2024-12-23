package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import classes.*;
import storage.*;

public class CoursePage extends JFrame {
    private JTextField courseIDField, courseTitleField, creditHoursField;
    private JComboBox<Teacher> teacherDropdown;
    private JButton addCourseButton, calculateAverageButton, addStudentButton, removeStudentButton, backButton, searchByCreditsButton;

    private ArrayList<Course> courses;
    private ArrayList<Teacher> teachers;

    public CoursePage() {
        this.courses = (ArrayList<Course>) University.getCourseRepo().getAll();
        this.teachers = (ArrayList<Teacher>) University.getTeacherRepo().getAll();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Course Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        setLocationRelativeTo(null);

        courseIDField = new JTextField(20);
        courseTitleField = new JTextField(20);
        creditHoursField = new JTextField(20);
        teacherDropdown = new JComboBox<>(teachers.toArray(new Teacher[0]));

        addCourseButton = new JButton("Add Course");
        calculateAverageButton = new JButton("Calculate Average Grade");
        addStudentButton = new JButton("Add Student");
        removeStudentButton = new JButton("Remove Student");
        backButton = new JButton("Back to Main Menu");
        searchByCreditsButton = new JButton("Search by Minimum Credit Hours");

        add(new JLabel("Course ID:"));
        add(courseIDField);
        add(new JLabel("Course Title:"));
        add(courseTitleField);
        add(new JLabel("Credit Hours:"));
        add(creditHoursField);
        add(new JLabel("Assigned Teacher (Optional):"));
        add(teacherDropdown);
        add(addCourseButton);
        add(calculateAverageButton);
        add(addStudentButton);
        add(removeStudentButton);
        add(searchByCreditsButton); // Add the new search button
        add(backButton);

        addCourseButton.addActionListener(e -> addCourse());
        calculateAverageButton.addActionListener(e -> calculateAverageGrade());
        addStudentButton.addActionListener(e -> openAddStudentFrame());
        removeStudentButton.addActionListener(e -> openRemoveStudentFrame());
        backButton.addActionListener(e -> backToMainMenu());
        searchByCreditsButton.addActionListener(e -> searchByMinimumCredits());
    }

    private void addCourse() {
        String courseID = courseIDField.getText().trim();
        String title = courseTitleField.getText().trim();
        String creditHoursText = creditHoursField.getText().trim();
        Teacher assignedTeacher = (Teacher) teacherDropdown.getSelectedItem();

        if (courseID.isEmpty() || title.isEmpty() || creditHoursText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int creditHours;
        try {
            creditHours = Integer.parseInt(creditHoursText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Credit hours must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Course course = new Course(courseID, title, creditHours);
        courses.add(course);
        JOptionPane.showMessageDialog(this, "Course added successfully!");
    }

    private void calculateAverageGrade() {
        CourseManagement selectedCourse = getSelectedCourseManagement();
        if (selectedCourse != null) {
            try {
                double average = selectedCourse.calculateAverageGrade();
                JOptionPane.showMessageDialog(
                    this,
                    "Average Grade: " + average,
                    "Average Grade",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "No Grades Available",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Please select a course.",
                "No Course Selected",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void openAddStudentFrame() {
        JFrame addStudentFrame = new JFrame("Add Student");
        addStudentFrame.setSize(400, 200);
        addStudentFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField studentIDField = new JTextField(20);
        JTextField studentNameField = new JTextField(20);
        JButton addButton = new JButton("Add");

        addStudentFrame.add(new JLabel("Student ID:"));
        addStudentFrame.add(studentIDField);
        addStudentFrame.add(new JLabel("Student Name:"));
        addStudentFrame.add(studentNameField);
        addStudentFrame.add(addButton);

        addButton.addActionListener(e -> {
            String studentID = studentIDField.getText().trim();
            String name = studentNameField.getText().trim();

            if (studentID.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(addStudentFrame, "Both fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student newStudent = new Student(name, "Email Placeholder", "DOB Placeholder", studentID, "Address Placeholder");
            getSelectedCourseManagement().addStudent(newStudent);
            JOptionPane.showMessageDialog(addStudentFrame, "Student added successfully.");
        });

        addStudentFrame.setVisible(true);
    }

    private void openRemoveStudentFrame() {
        JFrame removeStudentFrame = new JFrame("Remove Student");
        removeStudentFrame.setSize(400, 150);
        removeStudentFrame.setLayout(new GridLayout(2, 2, 10, 10));

        JTextField studentIDField = new JTextField(20);
        JButton removeButton = new JButton("Remove");

        removeStudentFrame.add(new JLabel("Student ID:"));
        removeStudentFrame.add(studentIDField);
        removeStudentFrame.add(removeButton);

        removeButton.addActionListener(e -> {
            String studentID = studentIDField.getText().trim();
            CourseManagement course = getSelectedCourseManagement();
            if (course != null && course.removeStudent(studentID)) {
                JOptionPane.showMessageDialog(removeStudentFrame, "Student removed successfully.");
            } else {
                JOptionPane.showMessageDialog(removeStudentFrame, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeStudentFrame.setVisible(true);
    }

    private void backToMainMenu() {
        dispose();
        new HomePage().setVisible(true); // Replace `HomePage` with the actual main menu class
    }

    private void searchByMinimumCredits() {
        String input = JOptionPane.showInputDialog(this, "Enter Minimum Credit Hours:");
        if (input != null && !input.trim().isEmpty()) {
            try {
                int minCredits = Integer.parseInt(input.trim());
                String result = University.filterCoursesByCredits(minCredits);
                JOptionPane.showMessageDialog(this, result, "Filtered Courses", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private CourseManagement getSelectedCourseManagement() {
        // Placeholder: Implement logic to get the selected CourseManagement object
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CoursePage().setVisible(true));
    }
}
