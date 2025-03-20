package Library;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Exception.EmailInputException;
import Exception.InputException;
import Exception.NumberOnlyException;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Admin extends Librarian {
    private String adminUsername = "admin";
    private String adminPassword = "123";
    private boolean Authenticated = false;

    public boolean adminLogin() {
        JDialog dialog = GUI.createdialog(500, 230); 

        GUI.createTitleDialog(dialog, 0, 10, 500, "Admin login");

        JButton Back = GUI.createButtonBackDialog(dialog);
        Back.addActionListener(e -> dialog.dispose());

        JPanel InputPanel = GUI.createInputPanelDialog(dialog, 0, 60, 500, 200);
        
        GUI.createLabelDialog("Username : ", 20, 0, InputPanel);
        JTextField usernameField = GUI.createTextFieldDialog(160, 3, InputPanel);
        
        GUI.createLabelDialog("Password : ", 20, 40, InputPanel);
        JTextField passwordField = GUI.createTextFieldDialog(160, 43, InputPanel);

        JButton loginButton = GUI.createButtonDialog("Login", 200, 83, 100, 30, InputPanel);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (adminUsername.equals(username) && adminPassword.equals(password)) {
                Authenticated = true;
                JOptionPane.showMessageDialog(dialog, "Login Successful!");
                dialog.dispose(); 
                return;
            }
            JOptionPane.showMessageDialog(dialog, "Invalid credentials. Try again.");
        });
        dialog.setModal(true);   //Stops everything until the user closes the dialog
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //Closes only the dialog, not the whole app
        dialog.setLocationRelativeTo(null); //Centers the dialog on the screen
        dialog.setVisible(true); // Shows the dialog and waits for user input
        return Authenticated; 
    }

    public void AdminFeatures() {
        JFrame frame = GUI.createFrame("Librarian Feature", 500, 300);

        GUI.createTitle(frame, 0, 10, 500, "Welcome to Librarian Features");
        
        JPanel panelButton = GUI.createInputPanel(frame, 0, 60, 500, 650);

        JButton button1 = GUI.createButton("Manage User", 150, 0, 200, 40, panelButton);
        JButton button2 = GUI.createButton("Manage Book", 150, 50, 200, 40, panelButton);
        JButton button3 = GUI.createButton("Manage Borrow", 150, 100, 200, 40, panelButton);
        JButton button4 = GUI.createButton("Log out", 150, 150, 200, 40, panelButton);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
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
                        try {
                            InputException exception1 = new InputException(Name.trim(), "^[A-Za-z ]+$");
                        }catch (InputException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            return;
                        }
                        String Address = address.getText();
                        String Phone = phone.getText();
                        try {
                            NumberOnlyException exception1 = new NumberOnlyException(Phone.trim(), "^[0-9]+$","Phone number only");
                        }catch (NumberOnlyException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            return;
                        }
                        String Email = email.getText();
                        try {
                            EmailInputException exception1 = new EmailInputException(Email.trim(), "^[a-zA-Z0-9._%+-]+@gmail\\.com$");
                        }catch (EmailInputException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            return;
                        }
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

    @Override
    public void addBorrow(){
// Create frame
        JFrame frame = GUI.createFrame("Add Borrow", 500, 370);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        // Create Title
        GUI.createTitle(frame, 0, 10, 500, "Add Borrow");
        
        // Create Input Panel
        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 300);

        GUI.createLabel("Book ID : ", 20, 0, panelInput);
        JTextField bookid = GUI.createTextField(160, 3, panelInput);
        
        GUI.createLabel("Student ID : ", 20, 40, panelInput);
        JTextField studentid = GUI.createTextField(160, 43, panelInput);

        GUI.createLabel("Borrow Date : ", 20, 80, panelInput);
        JTextField borrowdate = GUI.createTextField(160, 83, panelInput);

        GUI.createLabel("Return Date : ", 20, 120, panelInput);
        JTextField returndate = GUI.createTextField(160, 123, panelInput);

        JButton addButton = GUI.createButton("Add Borrow", 175, 207, 150, 30, panelInput);   
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.GetDataFromBook();
                Database.GetDataFromUser();
                frame.dispose();
                String payment = "";
                String bookname = "";
                String studentname = "";
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(bookid.getText().trim(), "^[0-9]+$","Number positive interger only");
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                //Check for exist
                int foundBook = 0;
                for (Book b : Database.bookList) {
                    if (String.valueOf(b.bookid).equals(bookid.getText())) {
                        bookname = b.bookname;
                        payment = String.valueOf(b.price * 0.1);
                        foundBook = 1;
                        break;
                    }
                }
                if (foundBook == 0) {
                    JOptionPane.showMessageDialog(null, "Book not found!");
                    return;
                }
                int foundStudent = 0;
                for (User u : Database.UserList) {
                    if (String.valueOf(u.ID).equals(studentid.getText())) {
                        studentname = u.Name;
                        foundStudent = 1;
                    }
                }

                for (HashMap<String, Object> b : Database.borrowList) {
                    if (String.valueOf(b.get("ReturnedDate")).equals("None")) {
                        if (String.valueOf(b.get("bookId")).equals(bookid.getText()) && String.valueOf(b.get("studentId")).equals(studentid.getText())) {
                            JOptionPane.showMessageDialog(null, "Student hasn't Return This book yet.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                String format = "INSERT INTO borrowlist (BookId, BookName, StudentId, StudentName, LibrarianId, LibrarianName, BorrowDate, ReturnDate, payment,LibrarianReturnId, LibrarianReturnName, ReturnedDate) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
                String insertQuery = String.format(format, bookid.getText(), bookname, studentid.getText(), studentname,
                        "Admin", "Admin", borrowdate.getText(), returndate.getText(), payment,
                        "None", "None", "None");
                MySQLConnection.executeUpdate(insertQuery);

                String updateQty = "UPDATE Book SET Qty = Qty - 1 WHERE ID = '" + bookid.getText() + "'";
                MySQLConnection.executeUpdate(updateQty);
            }
        });
    }

    @Override
    public void AddReturn(int BorrowID) {
        // Create frame
        JFrame frame = GUI.createFrame("Add Return", 500, 330);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        // Create Title
        GUI.createTitle(frame, 0, 10, 500, "Add Return By ID");

        // Create Input Panel
        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 220);

        GUI.createLabel("Book ID : ", 20, 0, panelInput);
        JTextField bookid = GUI.createTextField(160, 3, panelInput);

        GUI.createLabel("Student ID : ", 20, 40, panelInput);
        JTextField studentid = GUI.createTextField(160, 43, panelInput);

        GUI.createLabel("Returned Date : ", 20, 80, panelInput);
        JTextField returneddate = GUI.createTextField(160, 83, panelInput);

        JButton UpdateButton = GUI.createButton("Add Return", 175, 170, 150, 30, panelInput);   
        Database.GetDataFromBorrow();

        int found = 0;
        for (HashMap<String, Object> borrow : Database.borrowList) {
            if (String.valueOf(borrow.get("ReturnedDate")).equals("None") && String.valueOf(borrow.get("librarianReturnId")).equals("None")) {
                if (String.valueOf(borrow.get("borrowId")).equals(String.valueOf(BorrowID))) {
                    bookid.setText(borrow.get("bookId").toString());
                    studentid.setText(borrow.get("studentId").toString());
                    returneddate.setText(borrow.get("ReturnedDate").toString());
                    bookid.setEditable(false);
                    studentid.setEditable(false);
                    found =1;
                    break;
                }
            }
        }

        if(found == 0){
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Borrow Not found!!");
            return;
        }

        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.GetDataFromBook();
                Database.GetDataFromUser();
                Database.GetDataFromBorrow();
                frame.dispose();
                
                //Change from Borrow to Return
                if (!returneddate.equals("None")) {
                    String updateQty = "UPDATE Book SET Qty = Qty + 1 WHERE ID = '" + bookid.getText() + "'";
                    MySQLConnection.executeUpdate(updateQty);
                }else{
                    JOptionPane.showMessageDialog(null, "Return not successfully");
                    return;
                }

                String format = "UPDATE borrowlist SET LibrarianReturnId = '%s', LibrarianReturnName = '%s', ReturnedDate = '%s' WHERE borrowId = '%s'";
                String UpdateQuery = String.format(format, "Admin", "Admin", returneddate.getText(), BorrowID);
                MySQLConnection.executeUpdate(UpdateQuery);
                JOptionPane.showMessageDialog(null, "Return successfully");
            }
        });
    }

}
