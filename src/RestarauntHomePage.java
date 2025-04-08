import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RestarauntHomePage extends JFrame {
    private JLabel backgroundLabel;
    private int bgIndex = 0;
    private final String[] backgroundImages = {
            "imgsrc/download.jpg",
            "imgsrc/download1.jpg",
            "imgsrc/download2.jpg",
            "imgsrc/download3.jpg",
            "imgsrc/download4.jpg",
            "imgsrc/download5.jpg"
    };

    private boolean isLoggedIn = false;

    public RestarauntHomePage() {
        setTitle("Diwali Restaurant");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel using BorderLayout to divide into left, center, right
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Logo (Left)
        JLabel logoLabel = new JLabel(new ImageIcon("imgsrc/Diwali.png"));
        logoLabel.setPreferredSize(new Dimension(100, 100));
        topPanel.add(logoLabel, BorderLayout.WEST);

        // Title (Center) with border
        JLabel titleLabel = new JLabel("Diwali Restaurant", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                "Welcome",
                0,
                0,
                new Font("Serif", Font.BOLD, 18),
                Color.WHITE
        ));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Login Panel (Right)
        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS)); // Vertically stack the buttons

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        loginButton.setFocusPainted(false);
        signUpButton.setFocusPainted(false);

        loginPanel.add(loginButton);
        loginPanel.add(Box.createVerticalStrut(10)); // Space between buttons
        loginPanel.add(signUpButton);

        topPanel.add(loginPanel, BorderLayout.EAST);

        // Center Button for Takeaway / Dine-In (stacked vertically)
        JButton takeawayButton = new JButton("Takeaway");
        JButton dineInButton = new JButton("Dine-In");

        takeawayButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        dineInButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        takeawayButton.setFocusPainted(false);
        dineInButton.setFocusPainted(false);

        // Adjusting button sizes
        takeawayButton.setPreferredSize(new Dimension(150, 40)); // Smaller size
        dineInButton.setPreferredSize(new Dimension(150, 40));    // Smaller size

        // Center panel for the buttons (stacked vertically in the center)
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Align buttons in center

        // Add buttons to centerPanel
        centerPanel.add(takeawayButton);
        centerPanel.add(dineInButton);

        // Add top panel and center-aligned panel
        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        updateBackground();
        backgroundLabel.add(topPanel, BorderLayout.NORTH);
        backgroundLabel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(backgroundLabel);

        // Action for Takeaway button
        takeawayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isLoggedIn) {
                    JOptionPane.showMessageDialog(RestarauntHomePage.this, "Takeaway option selected!");
                } else {
                    JOptionPane.showMessageDialog(RestarauntHomePage.this, "Please login first!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action for Dine-In button
        dineInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isLoggedIn) {
                    JOptionPane.showMessageDialog(RestarauntHomePage.this, "Dine-In option selected!");
                } else {
                    JOptionPane.showMessageDialog(RestarauntHomePage.this, "Please login first!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Login Button Logic
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField userField = new JTextField();
                JPasswordField passField = new JPasswordField();
                Object[] message = {
                        "Username:", userField,
                        "Password:", passField
                };

                int option = JOptionPane.showConfirmDialog(RestarauntHomePage.this, message, "Login", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String user = userField.getText();
                    String pass = new String(passField.getPassword());
                    if (!user.isEmpty() && !pass.isEmpty()) {
                        isLoggedIn = true;
                        JOptionPane.showMessageDialog(RestarauntHomePage.this, "Logged in as " + user);
                    } else {
                        JOptionPane.showMessageDialog(RestarauntHomePage.this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Sign Up Button Logic
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Example logic for sign up (you can replace with your own logic)
                JOptionPane.showMessageDialog(RestarauntHomePage.this, "Sign Up Page Coming Soon!");
            }
        });

        startBackgroundImageRotation();
        setVisible(true);
    }

    private void updateBackground() {
        ImageIcon icon = new ImageIcon(backgroundImages[bgIndex]);
        Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(img));
    }

    private void startBackgroundImageRotation() {
        Thread bgThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            bgIndex = (bgIndex + 1) % backgroundImages.length;
                            updateBackground();
                        }
                    });
                }
            }
        });
        bgThread.setDaemon(true);
        bgThread.start();
    }

    public static void main(String[] args) {
        new RestarauntHomePage();
    }
}



