package Library;

import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Exception.InputException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends Librarian {
    private String adminUsername = "admin";
    private String adminPassword = "123";
    Scanner scanner = new Scanner(System.in);

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
            return username.equals(adminUsername) && password.equals(adminPassword);
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

    public void AdminFeatures() {
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        JLabel label = new JLabel("Welcome to Admin Features");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);

        JPanel panelButton = new JPanel();
        panelButton.setBounds(0, 60, 500, 650);
        panelButton.setLayout(null);

        JButton button1 = new JButton("Manage User");
        button1.setFont(new Font("Arial", Font.BOLD, 15));
        button1.setBounds(150, 0, 200, 40);
        JButton button2 = new JButton("Manage Book");
        button2.setFont(new Font("Arial", Font.BOLD, 15));
        button2.setBounds(150, 50, 200, 40);
        JButton button3 = new JButton("Manage Borrowed");
        button3.setFont(new Font("Arial", Font.BOLD, 15));
        button3.setBounds(150, 100, 200, 40);

        JButton button4 = new JButton("Log out");
        button4.setFont(new Font("Arial", Font.BOLD, 15));
        button4.setBounds(150, 150, 200, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                manageAllUser();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageBook();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageBorrow();
            }
        });

        panelButton.add(button1);
        panelButton.add(button2);
        panelButton.add(button3);
        panelButton.add(button4);
        frame.add(panelButton);
        frame.add(panelTitle);
    }

    public void manageAllUser() {
        Database.GetDataFromUser();

        // Create frame
        JFrame frame = GUI.createFrame("Manage All User", 730, 400);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        JPanel Title = new JPanel();
        Title.setBounds(0, 10, 700, 50);
        JLabel title = new JLabel("Manage User");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        Title.add(title);
        frame.add(Title);

        JPanel ActionPanel = new JPanel();
        ActionPanel.setBounds(0, 100, 700, 40);

        JButton Add = new JButton("Add");
        Add.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(Add);

        JButton Update = new JButton("Update");
        Update.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(Update);

        JButton Delete = new JButton("Delete");
        Delete.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(Delete);

        JPanel SearchPanel = new JPanel();
        SearchPanel.setBounds(0, 60, 700, 50);
        SearchPanel.setLayout(new FlowLayout());

        JLabel SearchLabel = new JLabel("Search By ID: ");
        SearchLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JTextField Search = new JTextField(20);
        Search.setFont(new Font("Arial", Font.BOLD, 15));
        Search.setPreferredSize(new Dimension(150, 30));
        JButton SearchButton = new JButton("Search");
        SearchButton.setFont(new Font("Arial", Font.BOLD, 15));
        SearchPanel.add(SearchLabel);
        SearchPanel.add(Search);
        SearchPanel.add(SearchButton);

        frame.add(ActionPanel);
        frame.add(SearchPanel);

        // Column names
        String[] columnNames = { "ID", "Name", "Address", "Phone Number", "Email", "Password" };
        // Create table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Loop through userList and add rows directly to the table model
        for (User user : Database.UserList) {
            model.addRow(new Object[] { user.ID, user.Name, user.Address, user.PhoneNumber, user.Email, user.getPassword() });
        }

        JTable table = GUI.createTable(frame, model, 10, 145, 700, 200);

        // Create Button Refresh
        JButton Refresh = new JButton("Refresh");
        Refresh.setFont(new Font("Arial", Font.BOLD, 15));
        Refresh.setForeground(Color.WHITE);

        Refresh.setBounds(610, 10, 100, 30);
        Refresh.setBackground(Color.RED);

        frame.add(Refresh);

        Refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                manageAllUser();
            }
        });

        // Add action listener to Add button
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = GUI.createFrame("Add User", 500, 200);
                GUI.createTitle(frame1, 0, 10, 500, "Please select Type of User");

                 // Create Button Back
                JButton Back = GUI.createButtonBack(frame1);
                Back.addActionListener(a -> frame1.dispose());
                JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);

                JRadioButton Librarian = new JRadioButton("Librairan");
                Librarian.setBounds(100, 0, 150, 30);
                Librarian.setFont(new Font("Arial", Font.BOLD, 15));
                panelInput1.add(Librarian);

                JRadioButton Student = new JRadioButton("Student");
                Student.setBounds(310, 0, 150, 30);
                Student.setFont(new Font("Arial", Font.BOLD, 15));
                panelInput1.add(Student);

                ButtonGroup group = new ButtonGroup();
                group.add(Librarian);
                group.add(Student);

                JButton goButton = GUI.createButton("Go", 150, 43, 200, 30, panelInput1);
                goButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Librarian.isSelected()) {
                            UserRegister("L");
                        } else if (Student.isSelected()) {
                            UserRegister("S");
                        }
                    }
                });
            }
        });

        // Add action listener to update button
        Update.addActionListener(e -> {
            UpdateUser();
            }
        );

        // Add action listener to delete button
        Delete.addActionListener(e -> {
            DeleteUser();
        });

        // Add action listener to Search button
        SearchButton.addActionListener(e -> {
                SearchUser(Search.getText());
            }
        );
    }

    public void UpdateUser(){
        Database.GetDataFromUser();
        JFrame frame1 = GUI.createFrame("Update User", 500, 200);

        GUI.createTitle(frame1, 0, 10, 500, "Update User By ID");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame1);
        Back.addActionListener(e -> frame1.dispose());
        JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);
        
        GUI.createLabel("Enter User ID", 20, 0, panelInput1);

        JTextField studentid = GUI.createTextField(160, 3, panelInput1);

        JButton goButton = GUI.createButton("Go'", 150, 43, 200, 30, panelInput1);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                JFrame frame = GUI.createFrame("Update User", 500, 350);

                GUI.createTitle(frame, 0, 10, 500,"Update User Information");
                JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 250);
                // Create Button Back
                JButton Back = GUI.createButtonBack(frame);
                Back.addActionListener(a -> frame.dispose());

                GUI.createLabel("Name : ", 20, 0, panelInput);
                JTextField name = GUI.createTextField(160, 3, panelInput);

                GUI.createLabel("Address : ", 20, 40, panelInput);
                JTextField address = GUI.createTextField(160, 43, panelInput);

                GUI.createLabel("Phone Number : ", 20, 80, panelInput);
                JTextField phone = GUI.createTextField(160, 83, panelInput);

                GUI.createLabel("Email : ", 20, 120, panelInput);
                JTextField email = GUI.createTextField(160, 123, panelInput);

                GUI.createLabel("Password : ", 20, 160, panelInput);
                JTextField password = GUI.createTextField(160, 163, panelInput);

                int found = 0;
                for (User u : Database.UserList) {
                    if (u.ID.equals(studentid.getText())) {
                        name.setText(u.Name);
                        address.setText(u.Address);
                        phone.setText(u.PhoneNumber);
                        email.setText(u.Email);
                        password.setText(u.getPassword());
                        found = 1;
                        break;
                    }
                };

                if(found == 0){
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "User Not found!!");
                    return;
                }

                JButton registerButton = GUI.createButton("Update", 200, 205, 100, 30, panelInput);
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Name = name.getText();
                        String Address = address.getText();
                        String Phone = phone.getText();
                        String Email = email.getText();
                        String Password = password.getText();

                        if (Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                        } else {
                            String insertQuery = String.format(
                                    "Update User SET Name = '%s', Address = '%s', PhoneNumber = '%s', Email = '%s', Password = '%s' WHERE concat(role, ID) = '%s'",
                                    Name, Address, Phone, Email, Password, studentid.getText());
                            MySQLConnection.executeUpdate(insertQuery);
                            JOptionPane.showMessageDialog(frame, "Update successful!");
                            frame.dispose();
                        }
                    }
                });
            }
        });
    }

    public void DeleteUser(){
        Database.GetDataFromUser();
        JFrame frame1 = GUI.createFrame("Delete Student", 500, 200);

        GUI.createTitle(frame1, 0, 10, 500, "Delete Student by ID");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame1);
        Back.addActionListener(e -> frame1.dispose());
        JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);

        GUI.createLabel("Enter Student ID : ", 20, 0 , panelInput1);

        JTextField StudentID = GUI.createTextField(160, 3, panelInput1);
        
        JButton goButton = GUI.createButton("Go", 150, 43, 200, 30, panelInput1);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (User u : Database.UserList) {
                    if (u.ID.equals(StudentID.getText())) {
                        String deleteQuery = String.format("DELETE FROM User WHERE concat(role, ID) = '%s'", StudentID.getText());
                        MySQLConnection.executeUpdate(deleteQuery);
                        JOptionPane.showMessageDialog(frame1, "User deleted successfully!");
                        frame1.dispose();
                        return;
                    }
                }
                frame1.dispose();
                JOptionPane.showMessageDialog(frame1, "User Not found!!");
            }
        });
    }

    public void SearchUser(String keyword){
        // Create frame
        JFrame frame = GUI.createFrame("Search User", 730, 150);

        GUI.createTitle(frame, 0, 0, 730, "Result of " + keyword);

        //Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());
        
        // Column names
        String[] columnNames = { "ID", "Name", "Address", "Phone Number", "Email", "Password" };
        
        // Create table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Loop through userList and add rows directly to the table model
        int found = 0;
        for (User user : Database.UserList) {
            if (user.ID.equals(keyword)) {
                model.addRow(new Object[] { user.ID, user.Name, user.Address, user.PhoneNumber, user.Email, user.getPassword() });
                found = 1;
                break;
            }
        }

        JTable table = GUI.createTable(frame, model, 10, 50, 700, 50);

        if (found == 0) {
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "User not found!");
            return;
        }
    }
}
