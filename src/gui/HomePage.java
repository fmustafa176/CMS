package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("School Management System");
        setSize(1200, 650);  // Larger window size for a more spacious layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window on the screen
        setResizable(true);  // Allow resizing

        // Set Layout for the frame
        setLayout(new BorderLayout());

        // Create a Panel for the buttons on the left and right
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // Vertical button layout with spacing

        // Set the preferred width of the button panel
        buttonPanel.setPreferredSize(new Dimension(300, getHeight()));  // Increase width to 300px

        // Create buttons for each page
        JButton studentButton = createButton("Student Management", "student_icon.png");
        JButton teacherButton = createButton("Teacher Management", "teacher_icon.png");
        JButton courseButton = createButton("Course Management", "course_icon.png");
        JButton reportButton = createButton("Generate Report", "report_icon.png");
        JButton exitButton = createButton("Exit", "exit_icon.png");

        // Add buttons to the button panel
        buttonPanel.add(studentButton);
        buttonPanel.add(teacherButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(exitButton);

        // Set button panel to the left side of the frame
        add(buttonPanel, BorderLayout.WEST);

        // Create a Panel for the center content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Add an image in the center (optional, remove if not needed)
        ImageIcon imageIcon = new ImageIcon("C:\\Desktop\\GUI\\image.png"); // Replace with your image path
        JLabel imageLabel = new JLabel(imageIcon);
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        // Set background color to white for the center panel
        centerPanel.setBackground(Color.WHITE); // White background
        add(centerPanel, BorderLayout.CENTER);

        // Action listeners for the buttons
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentPage().setVisible(true);
                dispose(); // Close current window
            }
        });
        
        teacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TeacherPage().setVisible(true);
                dispose();
            }
        });

        courseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CoursePage().setVisible(true);
                dispose();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReportPage().setVisible(true);
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        });
    }

    // Helper method to create styled buttons with icons and hover effects
    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Modern font
        button.setForeground(Color.WHITE); // Text color
        button.setBackground(new Color(70, 130, 180)); // Button color
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // Add border
        button.setPreferredSize(new Dimension(250, 60)); // Button size

        // Add Icon to button
        button.setIcon(new ImageIcon(iconPath)); // Set the icon for the button
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Hover effect on button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 220)); // Change color on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Revert back to original color
            }
        });

        // Rounded borders
        Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
        button.setBorder(border);

        // Add shadow effect on the button
        button.setOpaque(true);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
}
