package Library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import Exception.EmailInputException;
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
        JDialog dialog = GUI.createdialog(500, 230); 

        GUI.createTitleDialog(dialog, 0, 10, 500, "User login");

        JButton Back = GUI.createButtonBackDialog(dialog);
        Back.addActionListener(e -> dialog.dispose());

        JPanel InputPanel = GUI.createInputPanelDialog(dialog, 0, 60, 500, 200);
        
        GUI.createLabelDialog("Email : ", 20, 0, InputPanel);
        JTextField emailField = GUI.createTextFieldDialog(160, 3, InputPanel);
        
        GUI.createLabelDialog("Password : ", 20, 40, InputPanel);
        JTextField passwordField = GUI.createTextFieldDialog(160, 43, InputPanel);

        JButton loginButton = GUI.createButtonDialog("Login", 200, 83, 100, 30, InputPanel);

        Database.GetDataFromUser();
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            try {
                    EmailInputException exception1 = new EmailInputException(email.trim(), "^[a-zA-Z0-9._%+-]+@gmail\\.com$");
                }catch (EmailInputException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
            String password = passwordField.getText();

            for (User user : Database.UserList) {
                if (user.ID.startsWith(role) && user.Email.equals(email) && user.getPassword().equals(password)) {
                    ID = user.ID;
                    isAuthenticated = true;
                    JOptionPane.showMessageDialog(dialog, "Login Successful!");
                    dialog.dispose(); 
                    return;
                }
            }

            JOptionPane.showMessageDialog(dialog, "Invalid credentials. Try again.");
        });
        dialog.setModal(true);  // This makes the dialog block execution until closed
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return isAuthenticated; 
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
