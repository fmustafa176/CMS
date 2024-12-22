package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import classes.*;
import storage.University;

class TeacherPage extends JFrame {
    private JTextField teacherIDField, teacherNameField, specializationField;
    private JComboBox<Course> courseComboBox;
    private JButton assignButton, displayCoursesButton, backButton, addTeacherButton;

    private ArrayList<Teacher> teachers;
    private ArrayList<Course> courses;

    public TeacherPage() {
        setTitle("Teacher Management");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.courses = (ArrayList<Course>) University.getCourseRepo().getAll();
        teachers = new ArrayList<>();

        // Initialize components
        teacherIDField = createTextField("Enter Teacher ID");
        teacherNameField = createTextField("Enter Teacher Name");
        specializationField = createTextField("Enter Specialization");
        courseComboBox = new JComboBox<>(courses.toArray(new Course[0]));

        assignButton = createButton("Assign Course", "Assign a course to the teacher");
        displayCoursesButton = createButton("Display Courses", "Show all courses assigned to the teacher");
        backButton = createButton("Back", "Return to the main menu");
        addTeacherButton = createButton("Add Teacher", "Add a new teacher");

        assignButton.addActionListener(e -> assignCourseToTeacher());
        displayCoursesButton.addActionListener(e -> displayCoursesTaught());
        backButton.addActionListener(e -> goBackToMainMenu());
        addTeacherButton.addActionListener(e -> addNewTeacher());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Teacher Details"));
        inputPanel.add(new JLabel("Teacher ID:"));
        inputPanel.add(teacherIDField);
        inputPanel.add(new JLabel("Teacher Name:"));
        inputPanel.add(teacherNameField);
        inputPanel.add(new JLabel("Specialization:"));
        inputPanel.add(specializationField);
        inputPanel.add(new JLabel("Select Course:"));
        inputPanel.add(courseComboBox);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonPanel.add(addTeacherButton);
        buttonPanel.add(assignButton);
        buttonPanel.add(displayCoursesButton);
        buttonPanel.add(backButton);

        // Main layout
        setLayout(new BorderLayout(15, 15));
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        // Styling
        getContentPane().setBackground(new Color(240, 248, 255));
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

    private void assignCourseToTeacher() {
        String teacherID = teacherIDField.getText().trim();
        String teacherName = teacherNameField.getText().trim();
        String specialization = specializationField.getText().trim();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();

        if (teacherID.isEmpty() || teacherName.isEmpty() || specialization.isEmpty() || selectedCourse == null) {
            JOptionPane.showMessageDialog(this, "All fields must be filled, and a course must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Teacher teacher = findTeacherByID(teacherID);
        if (teacher == null) {
            teacher = new Teacher(teacherID, "N/A", "N/A", teacherName, 0, specialization);
            teachers.add(teacher);
        }

        if (!teacher.getTaughtCourses().contains((CharSequence) selectedCourse)) {
            teacher.assignCourse(selectedCourse);
            JOptionPane.showMessageDialog(this, "Course assigned successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "This course is already assigned to the teacher.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayCoursesTaught() {
        String teacherID = teacherIDField.getText().trim();

        if (teacherID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a Teacher ID to display courses.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Teacher teacher = findTeacherByID(teacherID);
        if (teacher == null) {
            JOptionPane.showMessageDialog(this, "No teacher found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder coursesList = new StringBuilder("Courses taught by " + teacher.getName() + ":\n");
        coursesList.append(teacher.getTaughtCourses());

        JOptionPane.showMessageDialog(this, coursesList.toString(), "Courses Taught", JOptionPane.INFORMATION_MESSAGE);
    }

    private Teacher findTeacherByID(String teacherID) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherID().equals(teacherID)) {
                return teacher;
            }
        }
        return null;
    }

    private void addNewTeacher() {
        String teacherID = teacherIDField.getText().trim();
        String teacherName = teacherNameField.getText().trim();
        String specialization = specializationField.getText().trim();

        if (teacherID.isEmpty() || teacherName.isEmpty() || specialization.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled to add a new teacher.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (findTeacherByID(teacherID) == null) {
            Teacher newTeacher = new Teacher(teacherID, "N/A", "N/A", teacherName, 0, specialization);
            teachers.add(newTeacher);
            JOptionPane.showMessageDialog(this, "New teacher added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Teacher with this ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackToMainMenu() {
        HomePage menu = new HomePage();
        menu.setVisible(true);
        this.dispose();
    }
}
