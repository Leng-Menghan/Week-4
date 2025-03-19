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
        String roleName = null;
        if(role == "S"){
            roleName = "Student";
        }else if(role == "L"){
            roleName = "Librarian";
        }
        JFrame frame = GUI.createFrame("User Register", 500, 350);

        GUI.createTitle(frame, 0, 10, 500, roleName + " Register");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 250);

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
        
        JButton registerButton = GUI.createButton("Register", 200, 205, 100, 30, panelInput);
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

        // Create frame
        JFrame frame = GUI.createFrame("Search User", 730, 150);

        GUI.createTitle(frame, 0, 0, 730, "Result of " + keyword);

        //Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        // Column names
        String[] columnNames = { "ID", "Name", "Category", "Author", "Price", "Quantity", "Publisher" };

        // Create table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add rows directly from bookList
        int found = 0;
        for (Book book : Database.bookList) {
            if(String.valueOf(book.bookid).equals(keyword)) {
                Object[] row = { book.bookid, book.bookname, book.category, book.author, book.price, book.quantity, book.publisher };
                model.addRow(row);
                found = 1 ;
            }
        }
        if(found == 0){
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Book Not found!!");
        }

        // Create JTable with model
        JTable table = GUI.createTable(frame, model, 10, 50, 700, 50);
    }


}
