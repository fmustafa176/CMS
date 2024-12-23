package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import classes.*;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("School Management System");
        setSize(1200, 650); // Larger window for a spacious layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(true); // Allow resizing
        setLayout(new BorderLayout());

        // Left button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 15, 15));
        buttonPanel.setPreferredSize(new Dimension(250, getHeight()));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Central display panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel(new ImageIcon("image.png"), JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Add left panel and center panel to the frame
        add(buttonPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        // Create and add buttons with hover effects
        JButton studentButton = createButton("Manage Students", null);
        JButton teacherButton = createButton("Manage Teachers", null);
        JButton courseButton = createButton("Manage Courses", null);
        JButton reportButton = createButton("View Reports", null);
        JButton exitButton = createButton("Exit", null);

        buttonPanel.add(studentButton);
        buttonPanel.add(teacherButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(exitButton);

        // Button actions
        studentButton.addActionListener(e -> {
            new StudentPage().setVisible(true);
            dispose();
        });

        teacherButton.addActionListener(e -> {
            new TeacherPage().setVisible(true);
            dispose();
        });

        courseButton.addActionListener(e -> {
            new CoursePage().setVisible(true);
            dispose();
        });

        reportButton.addActionListener(e -> {
            new ReportPage().setVisible(true);
            dispose();
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

    // Helper method to create buttons with consistent styling
    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setPreferredSize(new Dimension(200, 50));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(100, 150, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
        return button;
    }

    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to Computer Science", 3));
        courses.add(new Course("MATH201", "Calculus II", 4));

        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Dr. Alice Smith", "alice.smith@example.com", "1975-08-15", "T001", 48, "Computer Science"));
        teachers.add(new Teacher("Dr. Robert Johnson", "robert.johnson@example.com", "1980-04-22", "T002", 43, "Mathematics"));

        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
