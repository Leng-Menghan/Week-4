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
    
    private boolean isAuthenticated = false; // Tracks login success
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

    public boolean userLogin(String role){
        JDialog dialog = new JDialog(); // Modal dialog to block execution
        dialog.setModal(true);
        dialog.setSize(400, 250);
        dialog.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 60, 80, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(140, 60, 200, 25);
        dialog.add(emailLabel);
        dialog.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(140, 100, 200, 25);
        dialog.add(passwordLabel);
        dialog.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 150, 100, 30);
        dialog.add(loginButton);

        Database.GetDataFromUser();

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            for (User user : Database.UserList) {
                if (user.ID.startsWith(role) && user.Email.equals(email) && user.getPassword().equals(password)) {
                    ID = user.ID;
                    isAuthenticated = true;
                    JOptionPane.showMessageDialog(dialog, "Login Successful!");
                    dialog.dispose(); // Close the login form
                    return;
                }
            }

            JOptionPane.showMessageDialog(dialog, "Invalid credentials. Try again.");
        });

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true); // This blocks execution until the dialog closes

        return isAuthenticated; // Return true if login was successful
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
