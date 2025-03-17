package Library;

import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import Exception.InputException;
import Exception.NumberOnlyException;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class User implements UserAction {
    public String ID;
    public String Name;
    public String Address;
    public String PhoneNumber;
    public String Email;
    public String Password;
    Scanner scanner = new Scanner(System.in);

    // Constructor
    public User(String ID, String Name, String Address, String PhoneNumber, String Email, String password) {
        this.Name = Name;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Password = password;
        this.ID = ID;
    }

    // default constructor
    public User() {};

    // get password
    public String getPassword() {
        return this.Password;
    }


    public void UserRegister(String role){
        JFrame frame = new JFrame();
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        frame.add(panelTitle);

        JLabel label = new JLabel("Welcome to User Register");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);

        JPanel panelInput = new JPanel();
        panelInput.setBounds(0, 60, 500, 250);
        panelInput.setLayout(null);
        frame.add(panelInput);

        JLabel Name = new JLabel("Name : ");
        Name.setBounds(20, 0, 150, 30);
        Name.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(Name);
        JTextField name = new JTextField(20);
        name.setBounds(160, 3, 300, 30);
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),  // Outer black border
            BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(name);

        JLabel Address = new JLabel("Address : ");
        Address.setBounds(20, 40, 150, 30);
        Address.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(Address);
        JTextField address = new JTextField(20);
        address.setBounds(160, 43, 300, 30);
        address.setFont(new Font("Arial", Font.PLAIN, 15));
        address.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),  // Outer black border
            BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(address);

        JLabel PhoneNumber = new JLabel("Phone Number : ");
        PhoneNumber.setBounds(20, 80, 150, 30);
        PhoneNumber.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(PhoneNumber);
        JTextField phone = new JTextField(20);
        phone.setBounds(160, 83, 300, 30);
        phone.setFont(new Font("Arial", Font.PLAIN, 15));
        phone.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),  // Outer black border
            BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(phone);

        JLabel Email = new JLabel("Email : ");
        Email.setBounds(20, 120, 150, 30);        
        Email.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(Email);
        JTextField email = new JTextField(20);
        email.setBounds(160, 123, 300, 30);
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),  // Outer black border
            BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(email);

        JLabel Password = new JLabel("Password : ");
        Password.setBounds(20, 160, 150, 30);
        Password.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(Password);
        JPasswordField password = new JPasswordField(20);
        password.setBounds(160, 163, 300, 30);
        password.setFont(new Font("Arial", Font.PLAIN, 15));
        password.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),  // Outer black border
            BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(password);
        
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerButton.setBounds(200, 205, 100, 30);
        panelInput.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String Address = address.getText();
                String Phone = phone.getText();
                String Email = email.getText();
                String Password = String.valueOf(password.getPassword());

                if (Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                } else {
                    String insertQuery = String.format("INSERT INTO User (role ,Name, Address, PhoneNumber, Email, Password) VALUES ('%s','%s', '%s', '%s', '%s', '%s')", role ,Name, Address, Phone, Email, Password);
                    MySQLConnection.executeUpdate(insertQuery);
                    String GetID = "select concat(user.role, user.ID) as userID from user where user.Email = '"+ Email +"' and user.Password = '"+ Password +"'";
                    ResultSet rs = MySQLConnection.executeQuery(GetID);
                    try {
                        if (rs.next()) {  // Check if there is a result
                            ID = rs.getString("userID");
                        } 
                    } catch (SQLException a) {
                        a.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                    frame.dispose();
                }
            }
        });
};

    public boolean userLogin(String role){
    @FunctionalInterface
    interface LoginAction {
        boolean execute(String email, String password);
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
    JLabel EmailLabel = new JLabel("Email:");
    EmailLabel.setBounds(50, 60, 80, 25);
    JTextField EmailField = new JTextField(20);
    EmailField.setBounds(140, 60, 200, 25);
    frame.add(EmailLabel);
    frame.add(EmailField);

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
    Database.GetDataFromUser();
    LoginAction loginAction = (email, password) -> {
        for(User user : Database.UserList) {
            if(user.ID.startsWith(role)){
                if (user.Email.equals(email) && user.getPassword().equals(password)) {
                    ID = user.ID;
                    return true;
                }
            }
        }
        return false;
    };

    loginButton.addActionListener(e -> {
        String email = EmailField.getText();
        String password = new String(passwordField.getPassword());
        if (loginAction.execute(email, password)) {
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

    return loginAction.execute(String.valueOf(EmailField.getText()), String.valueOf(passwordField.getPassword()));
}

    //Login
    public boolean login(String role) {
        Database.GetDataFromUser();
        String email;
        while (true) {
            try {
                System.out.print("Enter email : ");
                email = scanner.nextLine();
                InputException test = new InputException(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "Invalid email format");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        String password;
        while(true){
            try{
                System.out.print("Enter password : ");
                password = scanner.nextLine();
                InputException test = new InputException(password, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        for(User u : Database.UserList) {
            if(u.ID.startsWith(role)){ {
                if (u.Email.equals(email) && u.getPassword().equals(password)) {
                    System.out.println("User logged in.");
                    ID = u.ID;
                    Name = u.Name;
                    return true;
                }
            }
        }
    }
        return false;
    }

    //Register
    public void register(String role) {
        Database.GetDataFromUser();
        String name;
        String address;
        String phoneNumber;
        String email;
        String password;
            System.out.println("Please Register");
            while (true) {
                try {
                    System.out.print("Enter name : ");
                    name = scanner.nextLine();
                    InputException test = new InputException(name, "^[a-zA-Z ]+$", "Characters only");
                    break;
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter address : ");
                    address = scanner.nextLine();
                    InputException test = new InputException(address, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                    break;
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter phone number : ");
                    phoneNumber = scanner.nextLine();
                    NumberOnlyException test = new NumberOnlyException(phoneNumber, "^[0-9 ]+$");
                    break;
                } catch (NumberOnlyException e) {
                    System.out.println(e.getMessage());
                }
            }
    
        while(true){
            while (true) {
                try {
                    System.out.print("Enter email : ");
                    email = scanner.nextLine();
                    InputException test = new InputException(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "Invalid email format");
                    break;
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }
    
            while (true) {
                try {
                    System.out.print("Enter password : ");
                    password = scanner.nextLine();
                    InputException test = new InputException(password, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                    break;
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            int found = 0;
            for (User u : Database.UserList) {
                if(u.ID.startsWith(role)) {
                    if (u.Email.equals(email) && u.getPassword().equals(password)) {
                        System.out.println("User already exists. Please try again.");
                        found = 1;
                        break;
                    }
                };
            }
            if(found == 0) break;
        }

        String insertQuery = String.format(
                "INSERT INTO User (role, Name, Address, PhoneNumber, Email, Password) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                role, name, address, phoneNumber, email, password);
        MySQLConnection.executeUpdate(insertQuery);
        Database.GetDataFromUser();
        
        String GetID = "select concat(user.role, user.ID) as userID, user.Name from user where user.Email = '"+ email +"' and user.Password = '"+ password +"'";
        ResultSet rs = MySQLConnection.executeQuery(GetID);
        try {
            while (rs != null && rs.next()) {
                ID = rs.getString("userID");
                Name = rs.getString("Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySQLConnection.closeConnection();
    }
    
    //Change Password
    public void changePassword() {
        Database.GetDataFromUser();
        String currentPassword;
         while(true){
            try{
                System.out.print("Enter current password: ");
                currentPassword = scanner.nextLine();
                InputException test = new InputException(currentPassword, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String newPassword;
        while(true){
           try{
                System.out.print("Enter new password: ");
               newPassword = scanner.nextLine();
               InputException test = new InputException(newPassword, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
               break;
           } catch (InputException e) {
               System.out.println(e.getMessage());
           }
        }
        
        for(User u : Database.UserList) {
            if(ID.equals(u.ID)) {
                if (u.getPassword().equals(currentPassword)) {
                    String updateQuery = String.format("UPDATE User SET Password = '%s' WHERE ID = '%s'", newPassword, ID);
                    MySQLConnection.executeUpdate(updateQuery);
                    System.out.println("Password changed successfully.");
                    return;
                }
                break;
            }
        }

        System.out.println("Invalid current password.");        
    }
    
    //Change Name
    public void changeName() {
        Database.GetDataFromUser();
        String newName;
        while(true){
            try{
                System.out.print("Enter new name: ");
                newName = scanner.nextLine();
                InputException test = new InputException(newName, "^[a-zA-Z ]+$","Characters only");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        String Password;
        while(true){
           try{
                System.out.print("Enter password: ");
               Password = scanner.nextLine();
               InputException test = new InputException(Password, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
               break;
           } catch (InputException e) {
               System.out.println(e.getMessage());
           }
        }
        for(User u : Database.UserList) {
            if(ID.equals(u.ID)) {
                if (u.getPassword().equals(Password)) {
                    String updateQuery = String.format("UPDATE User SET Name = '%s' WHERE concat(role, ID) = '%s'", newName, ID);
                    MySQLConnection.executeUpdate(updateQuery);
                    System.out.println("Name changed successfully.");
                    return;
                }
                break;
            }
        }
    }
    
    // Searh Book
    public void searchBook(String keyword) {
        Database.GetDataFromBook();
        if(Database.bookList.isEmpty()) {
            System.out.println("Book list is empty.");
            return;
        }
        JFrame frame = new JFrame("JTable with ArrayList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 400);
        frame.setLayout(null);

                // Column names
                String[] columnNames = { "ID", "Name", "Category", "Author", "Price", "Quantity", "Publisher" };

                // Create DefaultTableModel
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
                // Add rows directly from bookList
                for (Book book : Database.bookList) {
                    if(book.bookname.toLowerCase().contains(keyword.toLowerCase())) {
                        Object[] row = { book.bookid, book.bookname, book.category, book.author, book.price, book.quantity, book.publisher };
                        model.addRow(row);
                    }
                }
        
                // Create JTable with model
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
        
                // Create JFrame to display table
                frame.add(scrollPane, BorderLayout.CENTER);
                frame.setVisible(true);
    }

    // Display Book
    public void displayBook() {
        Database.GetDataFromBook();
        if(Database.bookList.isEmpty()){
            System.out.println("Book list is empty.");
            return;
        }
        System.out.println(
                "#---------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println(
                "|                                                                                                                                 |");
        System.out.println(
                "#                                           Book list in Library Management System                                                #");
        System.out.println(
                "|                                                                                                                                 |");
        System.out.println(
                "#---------------------------------------------------------------------------------------------------------------------------------#");
        String format = "| %-3s |  %-27s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
        System.out.println(
                "+-----+------------------------------+----------------------+----------------------+---------+----------+-----------------+");
        System.out.printf(format, "ID",  "Name", "Author", "Category", "Price", "Quantity", "Publisher");
        System.out.println(
                "+-----+------------------------------+----------------------+----------------------+---------+----------+-----------------+");
        int count = 0;
        for (Book b : Database.bookList) {
            System.out.println(b);
            count++;
        }
        System.out.println("| Total Books : " + count + "   |\n");
    }

    @Override
    public String toString() {
        String format = "| %-5s | %-20s | %-20s | %-15s | %-20s | %-15s |";
        String result = String.format(format, ID, Name, Address, PhoneNumber, Email, Password);
        return result;
    }

}
