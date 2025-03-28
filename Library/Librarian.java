package Library;

import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import Exception.NumberOnlyException;
import Exception.EmailInputException;
import Exception.InputException;
public class Librarian extends User {
    LocalDate today = LocalDate.now();
    LocalDate futureDate = today.plusDays(14);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void LibrarianFeatures() {
        JFrame frame = GUI.createFrame("Librarian Feature", 500, 350);

        GUI.createTitle(frame, 0, 10, 500, "Welcome to Librarian Features");
        
        JPanel panelButton = GUI.createInputPanel(frame, 0, 60, 500, 300);

        JButton button1 = GUI.createButton("Manage Student", 150, 0, 200, 40, panelButton);
        JButton button2 = GUI.createButton("Manage Book", 150, 50, 200, 40, panelButton);
        JButton button3 = GUI.createButton("Manage Borrow", 150, 100, 200, 40, panelButton);
        JButton button7 = GUI.createButton("Your Profile", 150, 150, 200, 40, panelButton);
        JButton button4 = GUI.createButton("Log out", 150, 200, 200, 40, panelButton);

        button7.addActionListener(e -> showInformation());
        button1.addActionListener(e -> manageStudent());
        button2.addActionListener(e -> manageBook());
        button3.addActionListener(e -> manageBorrow());
        button4.addActionListener(e -> frame.dispose());
    }

    public void manageStudent() {
        Database.GetDataFromUser();
        // Create frame
        JFrame frame = GUI.createFrame("Manage Student", 730, 400);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);

        //create Title
        GUI.createTitle(frame, 0,10,700,"Manage Student");
        
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

        JLabel SearchLabel = new JLabel("Search : ");
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
            if (user.ID.startsWith("S")) { // Filtering condition
                model.addRow(new Object[] { user.ID, user.Name, user.Address, user.PhoneNumber, user.Email, user.getPassword() });
            }
        }

        JTable table = GUI.createTable(frame, model, 10, 145, 700, 200);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                StudentRegister();
            }
        });

        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                updateStudent();
            }
        });

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deleteStudent();
            }
        });

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent(Search.getText());
            }
        });
    }

    public void StudentRegister(){
        JFrame frame = GUI.createFrame("User Register", 500, 350);

        GUI.createTitle(frame, 0, 10, 500,  "Student Register");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> {
            frame.dispose();
            manageStudent();
        });

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
                Database.GetDataFromUser();
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
                    for(User user : Database.UserList){
                        if(user.ID.startsWith("S")){
                            if(user.Email.equals(Email) && user.getPassword().equals(Password)){
                                JOptionPane.showMessageDialog(frame, "Email already exists.");
                                return;
                            }
                        }
                    }
                    String insertQuery = String.format("INSERT INTO User (role ,Name, Address, PhoneNumber, Email, Password) VALUES ('%s','%s', '%s', '%s', '%s', '%s')", "S" ,Name, Address, Phone, Email, Password);
                    MySQLConnection.executeUpdate(insertQuery);
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                    frame.dispose();
                    manageStudent();
                }
            }
        });
    };

    public void updateStudent() {
        Database.GetDataFromUser();
        JFrame frame1 = GUI.createFrame("Update Student", 500, 200);

        GUI.createTitle(frame1, 0, 10, 500, "Update Student By ID");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame1);
        Back.addActionListener(e -> {
            frame1.dispose();
            manageStudent();
        });
        JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);
        
        GUI.createLabel("Enter Student ID", 20, 0, panelInput1);

        JTextField studentid = GUI.createTextField(160, 3, panelInput1);

        JButton goButton = GUI.createButton("Go'", 150, 43, 200, 30, panelInput1);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                JFrame frame = GUI.createFrame("Update Student", 500, 350);

                GUI.createTitle(frame, 0, 10, 500,"Update Student Information");
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
                    if (u.ID.startsWith("S")) {
                        if (u.ID.equals(studentid.getText())) {
                            name.setText(u.Name);
                            address.setText(u.Address);
                            phone.setText(u.PhoneNumber);
                            email.setText(u.Email);
                            password.setText(u.getPassword());
                            email.setEditable(false);
                            found = 1;
                            break;
                        }
                    }
                };
                if(found == 0){
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "User Not found!!");
                    manageStudent();
                    return;
                }

                JButton UpdateButton = GUI.createButton("Update", 200, 205, 100, 30, panelInput);
                UpdateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Database.GetDataFromUser();
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
                            manageStudent();
                        }
                    }
                });
            }
        });
    };

    public void deleteStudent() {
        Database.GetDataFromUser();
        JFrame frame1 = GUI.createFrame("Delete Student", 500, 200);

        GUI.createTitle(frame1, 0, 10, 500, "Delete Student by ID");
        // Create Button Back
        JButton Back = GUI.createButtonBack(frame1);
        Back.addActionListener(e -> {
            frame1.dispose();
            manageStudent();
        });
        JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);

        GUI.createLabel("Enter Student ID : ", 20, 0 , panelInput1);

        JTextField StudentID = GUI.createTextField(160, 3, panelInput1);
        
        JButton goButton = GUI.createButton("Go", 150, 43, 200, 30, panelInput1);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (User u : Database.UserList) {
                    if (u.ID.startsWith("S")) {
                        if (u.ID.equals(StudentID.getText())) {
                            String deleteQuery = String.format("DELETE FROM User WHERE concat(role, ID) = '%s'", StudentID.getText());
                            MySQLConnection.executeUpdate(deleteQuery);
                            JOptionPane.showMessageDialog(frame1, "User deleted successfully!");
                            frame1.dispose();
                            manageStudent();
                            return;
                        }
                    }
                }
                frame1.dispose();
                JOptionPane.showMessageDialog(frame1, "User Not found!!");
                manageStudent();
            }
        });
    }

    public void searchStudent(String keyword) {
        // Loop through userList and add rows directly to the table model
        int found = 0;
        for (User user : Database.UserList) {
            if (user.ID.startsWith("S")) {
                if (user.ID.equals(keyword)) {
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
                    model.addRow(new Object[] { user.ID, user.Name, user.Address, user.PhoneNumber, user.Email, user.getPassword() });
                    found = 1;
                    JTable table = GUI.createTable(frame, model, 10, 50, 700, 50);
                    break;
                }
            }
        }
        if (found == 0) {
            JOptionPane.showMessageDialog(null, "User not found!");
            return;
        }
    }

    
    public void manageBook() {
        Database.GetDataFromBook();
        // Create frame
        JFrame frame = GUI.createFrame("Manage Book", 730, 400);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        GUI.createTitle(frame,0,10,730,"Manage Book");

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

        JLabel SearchLabel = new JLabel("Search : ");
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
        String[] columnNames = { "ID", "Name", "Category", "Author", "Price", "Quantity", "Publisher" };

        // Create DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Add rows directly from bookList
        for (Book book : Database.bookList) {
                Object[] row = { book.bookid, book.bookname, book.category, book.author, book.price, book.quantity, book.publisher };
                model.addRow(row);
        }

        JTable table = GUI.createTable(frame, model, 10, 145, 700, 200);

        // Add action listeners
        Add.addActionListener(e -> {
            addBook();
            frame.dispose();
        });

        Update.addActionListener(e -> {
            updateBook();
            frame.dispose();
        });

        Delete.addActionListener(e -> {
            deleteBook();
            frame.dispose();
        });

        Back.addActionListener(e -> frame.dispose());

        SearchButton.addActionListener(e -> {
            try {
                NumberOnlyException exception1 = new NumberOnlyException(Search.getText().trim(), "^[1-9]+$","Number positive interger only");
                searchBook(Search.getText());
            }catch (NumberOnlyException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
            }
        );

    }

    public void updateBook() {
        Database.GetDataFromBook();
        JFrame frame1 = GUI.createFrame("Update Book By ID", 500, 200);

         // Create Button Back
        JButton Back1 = GUI.createButtonBack(frame1);
        Back1.addActionListener(e -> {
            frame1.dispose();
            manageBook();
        });

        GUI.createTitle(frame1, 0, 10, 500, "Update Book By ID");
        
        JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);
        GUI.createLabel("Enter Book ID", 20, 0, panelInput1);
        JTextField bookid = GUI.createTextField(160, 3, panelInput1);

        JButton goButton = GUI.createButton("Go", 150, 43, 200, 30, panelInput1);

        goButton.addActionListener(e -> {
            frame1.dispose();
            try {
                NumberOnlyException exception1 = new NumberOnlyException(bookid.getText().trim(), "^[1-9]+$","Number positive interger only");
            }catch (NumberOnlyException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                manageBook();
                return;
            }
            JFrame frame = GUI.createFrame("Update Book", 500, 420);

            JButton Back = GUI.createButtonBack(frame);
            GUI.createTitle(frame,0,10,500,"Update Book");
            Back.addActionListener(a -> frame.dispose());

            JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 400);
            JTextField bookname = GUI.createTextField(160, 3,panelInput);
            JTextField category = GUI.createTextField(160, 43,panelInput);
            JTextField author = GUI.createTextField(160, 83, panelInput);
            JTextField price = GUI.createTextField(160, 123, panelInput);
            JTextField qty = GUI.createTextField(160, 163, panelInput);
            JTextField publisher = GUI.createTextField(160, 203, panelInput);
            GUI.createLabel("Book Name : ",20, 0, panelInput);
            GUI.createLabel("Category : ",20, 40, panelInput);
            GUI.createLabel("Author : ",20, 80, panelInput);
            GUI.createLabel("Price : ",20, 120, panelInput);
            GUI.createLabel("Qty : ",20, 160, panelInput);
            GUI.createLabel("Publisher : ",20, 200, panelInput);

            JButton UpdateButton = GUI.createButton("Update", 150, 243, 200, 30, panelInput);

            int found = 0;
            for (Book b : Database.bookList) {
                if (String.valueOf(b.bookid).equals(bookid.getText())) {
                    bookname.setText(b.bookname);
                    category.setText(b.category);
                    author.setText(b.author);
                    price.setText(String.valueOf(b.price));
                    qty.setText(String.valueOf(b.quantity));
                    publisher.setText(b.publisher);
                    found = 1;
                    break;
                }
            }
            if(found == 0){
                frame.dispose();
                JOptionPane.showMessageDialog(frame, "Book Not found!!");
                manageBook();
                return;
            }

            UpdateButton.addActionListener(a -> {
                Database.GetDataFromBook();
                try {
                    InputException exception1 = new InputException(author.getText().trim(), "^[A-Za-z ]+$");;
                }catch (InputException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(qty.getText().trim(), "^[0-9]+$","Number positive interger only");
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                String insertQuery = String.format(
                        "UPDATE Book SET Category = '%s', Name = '%s', Author = '%s', Price = '%s', Qty = '%s', Publisher = '%s' WHERE ID = '%s'",
                        category.getText(), bookname.getText(), author.getText(), price.getText(), qty.getText(),publisher.getText(), bookid.getText());
                MySQLConnection.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null, "Book Updated Successfully");
                frame.dispose();
                manageBook();
            });


        });

    }

    public void addBook() {
        JFrame frame = GUI.createFrame("Add Book", 500, 420);

        JButton Back = GUI.createButtonBack(frame);
        GUI.createTitle(frame,0,10,500,"Add Book");
        Back.addActionListener(e -> {
            frame.dispose();
            manageBook();
        });

        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 400);
        JTextField bookname = GUI.createTextField(160, 3,panelInput);
        JTextField category = GUI.createTextField(160, 43,panelInput);
        JTextField author = GUI.createTextField(160, 83, panelInput);
        JTextField price = GUI.createTextField(160, 123, panelInput);
        JTextField qty = GUI.createTextField(160, 163, panelInput);
        JTextField publisher = GUI.createTextField(160, 203, panelInput);
        GUI.createLabel("Book Name : ",20, 0, panelInput);
        GUI.createLabel("Category : ",20, 40, panelInput);
        GUI.createLabel("Author : ",20, 80, panelInput);
        GUI.createLabel("Price : ",20, 120, panelInput);
        GUI.createLabel("Qty : ",20, 160, panelInput);
        GUI.createLabel("Publisher : ",20, 200, panelInput);

        JButton AddButton = GUI.createButton("Add Book", 150, 243, 200, 30, panelInput);
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookname1 = bookname.getText();
                
                String category1 = category.getText();
                String author1 = author.getText();
                try {
                    InputException exception1 = new InputException(author1.trim(), "^[A-Za-z ]+$");
                }catch (InputException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                String price1 = price.getText();
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(price1.trim(), "^([1-9][0-9]*|0\\.[0-9]+|[1-9][0-9]*\\.[0-9]+)$","Number positive only");
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

                String quantity1 = qty.getText();
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(quantity1.trim(), "^[0-9]+$","Number positive interger only");
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                String publisher1 = publisher.getText();
                for (Book b : Database.bookList) {
                    if (b.bookname.equals(bookname1)) {
                        JOptionPane.showMessageDialog(null, "Book already exists");
                        return;
                    }
                }
                String insertQuery = String.format(
                        "INSERT INTO Book (Category, Name, Author, Price, Qty, Publisher) VALUES ( '%s', '%s', '%s', '%s','%s', '%s')",
                        category1, bookname1, author1, price1, quantity1, publisher1);
                MySQLConnection.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null, "Book Added Successfully");
                frame.dispose();
                manageBook();   
            };
        });
    }

    public void deleteBook() {
        Database.GetDataFromBook();
        JFrame frame1 = GUI.createFrame("Delete Book", 500, 200);

        GUI.createTitle(frame1,0,10,500,"Delete Book By ID");

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame1);
        Back.addActionListener(a -> {
            frame1.dispose();
            manageBook();
        });

        JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);
        GUI.createLabel("Enter Book ID : ",20, 0, panelInput1);
        JTextField bookid = GUI.createTextField(160, 3, panelInput1);

        JButton goButton = GUI.createButton("Go", 150, 43, 200, 30, panelInput1);
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(bookid.getText().trim(), "^[1-9]+$","Number positive interger only");
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    manageBook();
                    return;
                }
                for(Book b : Database.bookList){
                    if(String.valueOf(b.bookid).equals(bookid.getText())){
                        String deleteQuery = String.format("DELETE FROM Book WHERE ID = '%s'", bookid.getText());
                        MySQLConnection.executeUpdate(deleteQuery);
                        frame1.dispose();
                        manageBook();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame1, "Book Not found!!");
                frame1.dispose();
                manageBook();
            };
        });
    };


    public void manageBorrow() {
        Database.GetDataFromBorrow();
        // Create frame
        JFrame frame = GUI.createFrame("Manage Borrow", 1400, 400);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> {
            frame.dispose();
        });

        // Create Title
        GUI.createTitle(frame, 0, 10, 1400, "Manage Borrow");

        // Create Search Panel
        JPanel SearchPanel = new JPanel();
        SearchPanel.setBounds(0, 60, 1400, 45);
        SearchPanel.setLayout(new FlowLayout());
        JLabel SearchLabel = new JLabel("Search : ");
        SearchLabel.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField Search = new JTextField(20);
        Search.setFont(new Font("Arial", Font.BOLD, 15));
        Search.setPreferredSize(new Dimension(150, 30));
        JButton SearchButton = new JButton("Search");
        SearchButton.setFont(new Font("Arial", Font.BOLD, 15));
        SearchPanel.add(SearchLabel);
        SearchPanel.add(Search);
        SearchPanel.add(SearchButton);
        frame.add(SearchPanel);

        // Create Action Panel
        JPanel ActionPanel = new JPanel();
        ActionPanel.setBounds(0, 100, 1400, 40);

        JButton AddBorrow = new JButton("Add Borrow");
        AddBorrow.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(AddBorrow);

        JButton addReturn = new JButton("Add Return");
        addReturn.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(addReturn);

        JButton Delete = new JButton("Delete Return");
        Delete.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(Delete);

        frame.add(ActionPanel);

        // Column names
        String[] columnNames = { "Borrow ID", "Book ID", "Book Name", "Student ID", "Student Name", "Librarian ID",
                "Librarian Name",
                "Borrow Date", "Return Date", "Payment", "Librarian ID", "Librarian Name", "Returned Date" };
        
        // Create DefaultTableModel
        //For all
        DefaultTableModel modelAll = new DefaultTableModel(columnNames, 0);
        for (HashMap<String, Object> p : Database.borrowList) {
            Object[] row = {p.get("borrowId").toString(), p.get("bookId").toString(), p.get("bookName").toString()
            ,p.get("studentId").toString(), p.get("studentName").toString(), p.get("librarianId").toString()
            ,p.get("librarianName").toString(), p.get("borrowDate").toString(), p.get("returnDate").toString()  
            ,p.get("payment").toString(), p.get("librarianReturnId").toString(), p.get("librarianReturnName").toString()
            ,p.get("ReturnedDate").toString()};
            modelAll.addRow(row);
        }
        JTable tableAll = GUI.createTable(frame, modelAll, 10, 145, 1370, 200);

        // Set a custom renderer for the rows
        tableAll.setDefaultRenderer(Object.class, new CustomTableCellRenderer());

        addReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame1 = GUI.createFrame("Return Book", 500, 200);

                GUI.createTitle(frame1,0,10,500,"Return Book");
                // Create Button Back
                JButton Back = GUI.createButtonBack(frame1);
                Back.addActionListener(a -> {
                    frame1.dispose();
                    manageBorrow();
                });
                JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);
                GUI.createLabel("Enter Borrow ID : ",20, 0, panelInput1);
                JTextField borrowid = GUI.createTextField(160, 3, panelInput1);

                JButton goButton = GUI.createButton("Go", 150, 43, 200, 30, panelInput1);
                goButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                        try {
                            NumberOnlyException exception1 = new NumberOnlyException(borrowid.getText().trim(), "^[0-9]+$","Number positive interger only");
                            AddReturn(Integer.parseInt(borrowid.getText()));
                        }catch (NumberOnlyException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            manageBorrow();
                            return;
                        }
                    }
                });
            }
        });

        AddBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                addBorrow();
            }
        });

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame1 = GUI.createFrame("Delete Returned", 500, 200);

                GUI.createTitle(frame1,0,10,500,"Delete Returned By ID");
                // Create Button Back
                JButton Back = GUI.createButtonBack(frame1);
                Back.addActionListener(a -> {
                    frame1.dispose();
                    manageBorrow();
                });
                
                JPanel panelInput1 = GUI.createInputPanel(frame1, 0, 60, 500, 250);
                GUI.createLabel("Enter Borrow ID : ",20, 0, panelInput1);
                JTextField borrowid = GUI.createTextField(160, 3, panelInput1);

                JButton goButton = GUI.createButton("Go", 150, 43, 200, 30, panelInput1);

                goButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                        try {
                            NumberOnlyException exception1 = new NumberOnlyException(borrowid.getText().trim(), "^[0-9]+$","Number positive interger only");
                            deleteReturned(Integer.parseInt(borrowid.getText()));
                        }catch (NumberOnlyException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            manageBorrow();
                            return;
                        }
                    }
                });
            }
        });

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(Search.getText().trim(), "^[0-9]+$","Number positive interger only");
                    int borrowID = Integer.parseInt(Search.getText().trim());
                    searchBorrow(borrowID);
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        Back.addActionListener(e ->{
            frame.dispose();
        });

    }

    public void addBorrow() {
        // Create frame
        JFrame frame = GUI.createFrame("Add Borrow", 500, 370);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> {
            frame.dispose();
            manageBorrow();
        });

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
        borrowdate.setText(today.format(formatter));

        GUI.createLabel("Return Date : ", 20, 120, panelInput);
        JTextField returndate = GUI.createTextField(160, 123, panelInput);
        returndate.setText(futureDate.format(formatter));

        JButton addButton = GUI.createButton("Add Borrow", 175, 167, 150, 30, panelInput);   
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
                int bookAvailable = 0;
                for (Book b : Database.bookList) {
                    if (String.valueOf(b.bookid).equals(bookid.getText())) {
                        bookname = b.bookname;
                        payment = String.valueOf(b.price * 0.1);
                        foundBook = 1;
                        if(b.quantity > 0) {
                            bookAvailable = 1;
                        }
                        break;
                    }
                }
                if (foundBook == 0) {
                    JOptionPane.showMessageDialog(null, "Book not found!");
                    addBorrow();
                    return;
                }
                if(bookAvailable == 0) {
                    JOptionPane.showMessageDialog(null, "Book not available!");
                    addBorrow();
                    return;
                }
                int foundStudent = 0;
                for (User u : Database.UserList) {
                    if (String.valueOf(u.ID).equals(studentid.getText())) {
                        studentname = u.Name;
                        foundStudent = 1;
                    }
                }
                if(foundStudent == 0) {
                    JOptionPane.showMessageDialog(null, "Student not found!");
                    addBorrow();
                    return;
                }

                for (HashMap<String, Object> b : Database.borrowList) {
                    if (String.valueOf(b.get("ReturnedDate")).equals("None")) {
                        if (String.valueOf(b.get("bookId")).equals(bookid.getText()) && String.valueOf(b.get("studentId")).equals(studentid.getText())) {
                            JOptionPane.showMessageDialog(null, "Student hasn't Return This book yet.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                String insertQuery = "INSERT INTO borrowlist (BookId, BookName, StudentId, StudentName, LibrarianId, LibrarianName, BorrowDate, ReturnDate, payment, LibrarianReturnId, LibrarianReturnName, ReturnedDate) " +
                    "VALUES ('" + bookid.getText() + "', '" + bookname + "', '" + studentid.getText() + "', '" + studentname + "', '"+ ID +"', '"+ Name +"', '" + borrowdate.getText() + "', '" + returndate.getText() + "', " +
                    payment + ", 'None', 'None', NULL)";

                MySQLConnection.executeUpdate(insertQuery);

                String updateQty = "UPDATE Book SET Qty = Qty - 1 WHERE ID = '" + bookid.getText() + "'";
                MySQLConnection.executeUpdate(updateQty);
                JOptionPane.showMessageDialog(null, "Borrow successfully");
                manageBorrow();
            }
        });
    };

    public void AddReturn(int BorrowID) {
        Database.GetDataFromBorrow();
        int found = 0;
        for (HashMap<String, Object> Borrow1 : Database.borrowList) {
            if (String.valueOf(Borrow1.get("ReturnedDate")).equals("None") && String.valueOf(Borrow1.get("librarianReturnId")).equals("None")) {
                if (String.valueOf(Borrow1.get("borrowId")).equals(String.valueOf(BorrowID))) {
                            // Create frame
                JFrame frame = GUI.createFrame("Add Return", 500, 330);

                // Create Button Back
                JButton Back = GUI.createButtonBack(frame);
                Back.addActionListener(e -> {
                    frame.dispose();
                    manageBorrow();
                });

                // Create Title
                GUI.createTitle(frame, 0, 10, 500, "Add Return By ID");

                // Create Input Panel
                JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 220);

                GUI.createLabel("Book ID : ", 20, 0, panelInput);
                JTextField bookid = GUI.createTextField(160, 3, panelInput);

                GUI.createLabel("Student ID : ", 20, 40, panelInput);
                JTextField studentid = GUI.createTextField(160, 43, panelInput);

                GUI.createLabel("Returned Date : ", 20, 120, panelInput);
                JTextField returneddate = GUI.createTextField(160, 123, panelInput);

                JButton UpdateButton = GUI.createButton("Add Return", 175, 170, 150, 30, panelInput);   

                bookid.setText(Borrow1.get("bookId").toString());
                studentid.setText(Borrow1.get("studentId").toString());
                returneddate.setText(today.format(formatter));
                bookid.setEditable(false);
                studentid.setEditable(false);
                found =1;
                
                UpdateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Database.GetDataFromBook();
                        Database.GetDataFromUser();
                        Database.GetDataFromBorrow();
                        frame.dispose();
                        
                        //Change from Borrow to Return
                        if (!returneddate.getText().equals("None")) {
                            String updateQty = "UPDATE Book SET Qty = Qty + 1 WHERE ID = '" + bookid.getText() + "'";
                            MySQLConnection.executeUpdate(updateQty);
                        }else{
                            JOptionPane.showMessageDialog(null, "Return not successfully");
                            return;
                        }
        
                        String format = "UPDATE borrowlist SET LibrarianReturnId = '%s', LibrarianReturnName = '%s', ReturnedDate = '%s' WHERE borrowId = '%s'";
                        String UpdateQuery = String.format(format, ID, Name, returneddate.getText(), BorrowID);
                        MySQLConnection.executeUpdate(UpdateQuery);
                        JOptionPane.showMessageDialog(null, "Return successfully");
                        manageBorrow();
                    }
                });
                break;
            }
        }
    }
        if(found == 0){
            JOptionPane.showMessageDialog(null, "Borrow Not found!!");
            manageBorrow();
            return;
        }
    }

    public void deleteReturned(int BorrowID) {
        Database.GetDataFromBorrow();
        for (HashMap<String, Object> borrow : Database.borrowList) {
            if (!String.valueOf(borrow.get("librarianReturnId")).equals("None") && !String.valueOf(borrow.get("ReturnedDate")).equals("None")) {
                if (String.valueOf(borrow.get("borrowId")).equals(String.valueOf(BorrowID))) {
                    String format = "DELETE FROM borrowlist WHERE borrowId = '%s'";
                    String DeleteQuery = String.format(format, String.valueOf(BorrowID));
                    MySQLConnection.executeUpdate(DeleteQuery);
                    JOptionPane.showMessageDialog(null, "Deleted successfully");
                    manageBorrow();
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Returned Not found", "Error", JOptionPane.ERROR_MESSAGE);
        manageBorrow();
    }

    public void searchBorrow(int BorrowID) {
        Database.GetDataFromBorrow();
        
        // Create DefaultTableModel
        int found = 0;

        for (HashMap<String, Object> p : Database.borrowList) {
            if (String.valueOf(p.get("borrowId")).equals(String.valueOf(BorrowID))) {
                // Create frame
                JFrame frame = GUI.createFrame("Search Borrow", 1400, 170);

                // Create Title
                GUI.createTitle(frame, 0, 10, 1400, "Search Borrow");

                // Create Button Back
                JButton Back = GUI.createButtonBack(frame);
                Back.addActionListener(e -> frame.dispose());

                // Column names
                String[] columnNames = { "Borrow ID", "Book ID", "Book Name", "Student ID", "Student Name", "Librarian ID",
                        "Librarian Name",
                        "Borrow Date", "Return Date", "Payment", "Librarian ID", "Librarian Name", "Returned Date" };
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                Object[] row = { p.get("borrowId"), p.get("bookId"), p.get("bookName"), p.get("studentId"),
                        p.get("studentName"), p.get("librarianId"), p.get("librarianName"), p.get("borrowDate"),
                        p.get("returnDate"), p.get("payment"), p.get("librarianReturnId"), p.get("librarianReturnName"),
                        p.get("ReturnedDate") };
                model.addRow(row);
                found = 1;
                JTable table = GUI.createTable(frame, model, 10, 60, 1370, 50);
                break;
            }
        }
        

        if(found == 0) {
            JOptionPane.showMessageDialog(null, "Not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    };

    // Custom TableCellRenderer
    static class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            // Get the value of the "ReturnedDate" column (index 12)
            String returnedDate = (String) table.getValueAt(row, 12); // Column index for "ReturnedDate"

            // Set the background color based on the "ReturnedDate"
            if ("None".equals(returnedDate)) {
                c.setBackground(Color.YELLOW);  // Set yellow for "None"
            } else {
                c.setBackground(Color.WHITE);   // Set white for other values
            }

            return c;
        }
    }
    
}
