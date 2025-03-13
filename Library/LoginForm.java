package Library;

import javax.swing.*;
import java.awt.*;

public class LoginForm {    
    public boolean adminLogin() {
        @FunctionalInterface
        interface LoginAction {
            boolean execute(String username, String password);
        }
    
        JFrame frame = new JFrame("Login Form");
        frame.setSize(400, 250);
        frame.setLayout(null);

        // Title Label
        JLabel label = new JLabel("Login Form", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBounds(0, 10, 400, 30);
        frame.add(label);

        // Username Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 60, 80, 25);
        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(140, 60, 200, 25);
        frame.add(usernameLabel);
        frame.add(usernameField);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 25);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 100, 200, 25);
        frame.add(passwordLabel);
        frame.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 150, 100, 30);
        frame.add(loginButton);

        LoginAction loginAction = (username, password) -> {
            return username.equals("admin") && password.equals("123");
        };

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (loginAction.execute(username, password)) {
                frame.dispose(); 
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Try again.");
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Block execution until login is done
        while (frame.isVisible()) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return loginAction.execute(usernameField.getText(), String.valueOf(passwordField.getPassword()));
    }
    
    public void DisplayOptionUser(){
        JFrame frame = new JFrame();
            frame.setSize(500, 250);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);

            JPanel panel = new JPanel();
            JLabel label = new JLabel("Welcome to Library Management System");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(label);
            panel.setBounds(0, 10, 500, 40);

            JPanel panel2 = new JPanel();
            panel2.setBounds(0, 60, 500, 400);
            panel2.setLayout(null);

            JButton button1 = new JButton("Admin");
            button1.setFont(new Font("Arial", Font.BOLD, 15));
            button1.setBounds(150,0,200,40);

            JButton button2 = new JButton("Librarian");
            button2.setFont(new Font("Arial", Font.BOLD, 15));
            button2.setBounds(150,50,200,40);
            
            JButton button3 = new JButton("Student");
            button3.setFont(new Font("Arial", Font.BOLD, 15));
            button3.setBounds(150,100,200,40);


            panel2.add(button1);
            panel2.add(button2);
            panel2.add(button3);
            
            frame.add(panel2);
            frame.add(panel);
    }
    
    public void DisplayAdminOption(){
        JFrame frame = new JFrame();
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        JLabel label = new JLabel("Welcome to Admin Functions");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);
        String[] adminOptions = {
            "Display Librarian",
            "Display Student",
            "Display Book",
            "Add Book",
            "Add Book Quantity By ISBN",
            "Remove Book By ISBN",
            "Search Book",
            "Display Borrowed Books",
            "Display Returned Books",
            "Search Student By ID",
            "Search Librarian By ID",
            "Logout"
        };
        JPanel panelButton = new JPanel();
        panelButton.setBounds(0, 60, 500, 650);
        panelButton.setLayout(null);
        for (int i = 0; i < adminOptions.length; i++) {
            JButton button = new JButton(adminOptions[i]);
            button.setFont(new Font("Arial", Font.BOLD, 15));
            button.setBounds(125, i * 50, 250, 40);
            int index = i; 
            button.addActionListener(e -> {
                switch (index) {
                    case 0: System.out.println("Displaying Librarians"); break;
                    case 1: System.out.println("Displaying Students"); break;
                    case 2: System.out.println("Displaying Books"); break;
                    case 3: System.out.println("Adding Book"); break;
                    case 4: System.out.println("Adding Book Quantity By ISBN"); break;
                    case 5: System.out.println("Removing Book By ISBN"); break;
                    case 6: System.out.println("Searching Book"); break;
                    case 7: System.out.println("Displaying Borrowed Books"); break;
                    case 8: System.out.println("Displaying Returned Books"); break;
                    case 9: System.out.println("Searching Student By ID"); break;
                    case 10: System.out.println("Searching Librarian By ID"); break;
                    case 11: System.out.println("Logging out"); break;
                    default: break;
                }
            });
            panelButton.add(button);
        }
        frame.add(panelTitle);
        frame.add(panelButton);
    }
    
    public static void main(String[] args) {
        //new LoginForm().DisplayOptionUser();
        new LoginForm().DisplayAdminOption();
    }
}
