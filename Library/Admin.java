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
                frame.dispose();
                manageBook();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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
        JFrame frame = new JFrame("JTable with ArrayList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 400);
        frame.setLayout(null);

        // Create Button Back
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);

        Back.setBounds(10, 10, 100, 30);
        Back.setBackground(Color.RED);

        frame.add(Back);

        // Column names
        String[] columnNames = { "ID", "Name", "Address", "Phone Number", "Email", "Password" };

        // Convert ArrayList of objects to 2D array
        String[][] data = new String[Database.UserList.size()][6]; // 3 columns

        JPanel Title = new JPanel();
        Title.setBounds(0, 10, 700, 50);
        JLabel title = new JLabel("Manage User");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        Title.add(title);
        frame.add(Title);

        JPanel ActionPanel = new JPanel();
        ActionPanel.setBounds(0, 100, 700, 40);

        JButton FilterStudent = new JButton("Filter Student");
        FilterStudent.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(FilterStudent);

        JButton FilterLibrarian = new JButton("Filter Librarian");
        FilterLibrarian.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(FilterLibrarian);

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
        for (int i = 0; i < Database.UserList.size(); i++) {
            User p = Database.UserList.get(i);
            data[i][0] = p.ID;
            data[i][1] = p.Name;
            data[i][2] = p.Address;
            data[i][3] = p.PhoneNumber;
            data[i][4] = p.Email;
            data[i][5] = p.getPassword();
        }
        // Create JTable with DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Set font and row height
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        // Set header font and background color
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);

        // Set grid color and selection background
        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(Color.BLUE);
        table.setSelectionForeground(Color.WHITE);

        // Remove column reordering
        header.setReorderingAllowed(false);

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 145, 700, 200);

        // Add scroll pane to frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set frame visibility
        frame.setVisible(true);

        // Add action listener to Back button
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AdminFeatures();
            }
        });

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


        // Add action listener to FilterStudent button
        FilterStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                manageUser("S");
            }
        });

        // Add action listener to FilterLibrarian button
        FilterLibrarian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                manageUser("L");
            }
        });

        // Add action listener to Search button
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Search.getText().startsWith("S")){
                    searchUser(Search.getText(), "S");
                }if(Search.getText().startsWith("L")){
                    searchUser(Search.getText(), "L");
                }
            }
        });
    }

    // Search Librarian by id
    public void searchLibrarianByID() {
        Database.GetDataFromUser();
        if (Database.UserList.isEmpty()) {
            System.out.println("User list is empty.");
            return;
        }
        String librarianID;
        while (true) {
            try {
                System.out.print("Enter Librarian ID     : ");
                librarianID = scanner.nextLine();
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$",
                        "Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Result of librarian with ID : " + librarianID);
        System.out.println("___________________________________________________\n");
        for (User l : Database.UserList) {
            if (librarianID.equals(l.ID)) {
                System.out.println("ID        : " + l.ID);
                System.out.println("Name      : " + l.Name);
                System.out.println("Address   : " + l.Address);
                System.out.println("Phone     : " + l.PhoneNumber);
                System.out.println("Email     : " + l.Email);
                System.out.println("___________________________________________________");
                return;
            }
        }
        System.out.println("                Librarian not found");
        System.out.println("___________________________________________________");
    }

    // Display Librarian
    public void displayLibrarain() {
        Database.GetDataFromUser();
        if (Database.UserList.isEmpty()) {
            System.out.println("No data in Librarain list");
            return;
        }
        System.out.println(
                "#-----------------------------------------------------------------------------------------------------------------#");
        System.out.println(
                "|                                                                                                                 |");
        System.out.println(
                "#                                      Librarain list in Library Management System                                #");
        System.out.println(
                "|                                                                                                                 |");
        System.out.println(
                "#-----------------------------------------------------------------------------------------------------------------#");
        int count = 0;
        String format = "| %-5s | %-20s | %-20s | %-15s | %-20s | %-15s |\n";
        System.out.println(
                "+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.printf(format, " ID", "Name", "Address", "Phone Number", "Email", "Password");
        System.out.println(
                "+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        for (User l : Database.UserList) {
            if (l.ID.charAt(0) == 'L') {
                System.out.println(l);
                System.out.println(
                        "+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
                count++;
            }
        }

        System.out.println("--------------- Total librarain : " + count + " --------------- ");
    }

    

}
