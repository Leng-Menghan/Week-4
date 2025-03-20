package Library;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import Exception.EmailInputException;
import Exception.InputException;
public class User implements UserAction {
    protected String ID;
    protected String Name;
    protected String Address;
    protected String PhoneNumber;
    protected String Email;
    protected String Password;
    private boolean isAuthenticated = false; // Tracks login success
    
    // Constructor for register
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

    public void changePassword() {
        JFrame frame = GUI.createFrame("Change Password", 520, 230);

        GUI.createTitle(frame, 0, 10, 520,"Change Password");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 530, 250);

        GUI.createLabel("Current Password : ", 20, 0, panelInput);
        JTextField currentPassword = GUI.createTextField(180, 3, panelInput);

        GUI.createLabel("New Password : ", 20, 40, panelInput);
        JTextField newPassword = GUI.createTextField(180, 43, panelInput);
        
        JButton changeButton = GUI.createButton("Change", 200, 83, 100, 30, panelInput);
        changeButton.addActionListener(e -> {
            if (currentPassword.getText().equals(this.Password)) {
                String updatePassword = "UPDATE User SET Password = '" + newPassword.getText() + "' WHERE CONCAT(role, ID) = '" + this.ID + "'";
                MySQLConnection.executeUpdate(updatePassword);
                this.Password = newPassword.getText();
                JOptionPane.showMessageDialog(frame, "Password changed successfully!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Current password is incorrect.");
            }
        });
    }

    public void changeName(){
        JFrame frame = GUI.createFrame("Change Name", 520, 230);

        GUI.createTitle(frame, 0, 10, 520,"Change Name");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 530, 250);

        GUI.createLabel("New name : ", 20, 0, panelInput);
        JTextField newName = GUI.createTextField(180, 3, panelInput);

        GUI.createLabel("Your password : ", 20, 40, panelInput);
        JTextField currentPassword = GUI.createTextField(180, 43, panelInput);
        
        JButton changeButton = GUI.createButton("Change", 200, 83, 100, 30, panelInput);
        changeButton.addActionListener(e -> {
            if (currentPassword.getText().equals(this.Password)) {
                try {
                    InputException exception1 = new InputException(newName.getText().trim(), "^[A-Za-z ]+$");
                }catch (InputException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                String updateName = "UPDATE User SET Name = '" + newName.getText() + "' WHERE CONCAT(role, ID) = '" + this.ID + "'";
                MySQLConnection.executeUpdate(updateName);
                this.Name = newName.getText();
                JOptionPane.showMessageDialog(frame, "Name changed successfully!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Your password is incorrect.");
            }
        });
    }

    public void showInformation(){
        // Create frame
        JFrame frame = GUI.createFrame("Display Information", 500, 350);

        GUI.createTitle(frame, 0, 10, 500, "Your Information");

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        // Invoice Content Panel
        JPanel invoicePanel = new JPanel();
        invoicePanel.setBounds(20, 60, 450, 220);
        invoicePanel.setLayout(null);
        invoicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        invoicePanel.setBackground(Color.WHITE);
        frame.add(invoicePanel);
        // Labels for Invoice Details
        int yPosition = 10;
        JLabel StudentID = new JLabel("ID: " + this.ID);
        StudentID.setFont(new Font("Arial", Font.PLAIN, 16));
        StudentID.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(StudentID);
        yPosition += 40;

        JLabel StudentName = new JLabel("Name: " + this.Name);
        StudentName.setFont(new Font("Arial", Font.PLAIN, 16));
        StudentName.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(StudentName);
        yPosition += 40;

        JLabel LibrarianID = new JLabel("Phone Number: " + this.PhoneNumber);
        LibrarianID.setFont(new Font("Arial", Font.PLAIN, 16));
        LibrarianID.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(LibrarianID);
        yPosition += 40;

        JLabel LibrarianName = new JLabel("Address: " + this.Address);
        LibrarianName.setFont(new Font("Arial", Font.PLAIN, 16));
        LibrarianName.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(LibrarianName);
        yPosition += 40;

        JLabel Dates = new JLabel("Email : " + this.Email);
        Dates.setFont(new Font("Arial", Font.PLAIN, 16));
        Dates.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(Dates);
        yPosition += 40;
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
                    this.ID = user.ID;
                    this.Password = user.Password;
                    this.Name = user.Name;
                    this.Address = user.Address;
                    this.PhoneNumber = user.PhoneNumber;
                    this.Email = user.Email;
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
