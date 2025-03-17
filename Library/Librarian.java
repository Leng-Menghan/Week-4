package Library;

import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Exception.InputException;
import Exception.NumberOnlyException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Librarian extends User {
    Scanner scanner = new Scanner(System.in);

    // constructor for register
    public Librarian(String ID, String Name, String Address, String PhoneNumber, String Email, String password) {
        super(ID, Name, Address, PhoneNumber, Email, password);
    }

    // default constructor
    public Librarian() {
    };

    public void LibrarianFeatures() {
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        JLabel label = new JLabel("Welcome to Librarian Features");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);

        JPanel panelButton = new JPanel();
        panelButton.setBounds(0, 60, 500, 650);
        panelButton.setLayout(null);

        JButton button1 = new JButton("Manage Student");
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
                manageUser("S");
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

    public void manageUser(String role) {
        Database.GetDataFromUser();

        String type;
        if (role.equals("S")) {
            type = "Student";
        } else {
            type = "Librarian";
        }

        if (Database.UserList.isEmpty()) {
            System.out.println("No data in student list");
            return;
        }
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

        JPanel Title = new JPanel();
        Title.setBounds(0, 10, 700, 50);
        JLabel title = new JLabel("Manage " + type);
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
            if (user.ID.startsWith(role)) { // Filtering condition
                model.addRow(new Object[] { user.ID, user.Name, user.Address, user.PhoneNumber, user.Email,
                        user.getPassword() });
            }
        }

        // Create JTable with model
        JTable table = new JTable(model);

        // Set table styles
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(Color.BLUE);
        table.setSelectionForeground(Color.WHITE);

        // Style table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        scrollPane.setBounds(10, 145, 700, 200);
        frame.add(scrollPane, BorderLayout.CENTER);
        // Set frame visibility
        frame.setVisible(true);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //Have metter
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
                manageUser("S");
            }
        });

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegister(role);
            }
        });

        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser(role);
            }
        });

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser(role);
            }
        });

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUser(Search.getText(), role);
            }
        });
    }

    public void updateUser(String role) {
        Database.GetDataFromUser();
        JFrame frame1 = new JFrame();
        frame1.setSize(500, 200);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(null);

        JPanel panelTitle1 = new JPanel();
        panelTitle1.setBounds(0, 10, 500, 40);
        JLabel label1 = new JLabel("Update Book By ID");
        label1.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle1.add(label1);
        frame1.add(panelTitle1);

        JPanel panelInput1 = new JPanel();
        panelInput1.setBounds(0, 60, 500, 250);
        panelInput1.setLayout(null);
        frame1.add(panelInput1);

        JLabel BOOKID = new JLabel("Enter Book ID : ");
        BOOKID.setBounds(20, 0, 150, 30);
        BOOKID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput1.add(BOOKID);

        JTextField bookid = new JTextField(20);
        bookid.setBounds(160, 3, 300, 30);
        bookid.setFont(new Font("Arial", Font.PLAIN, 15));
        bookid.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        panelInput1.add(bookid);

        JButton goButton = new JButton("Go");
        goButton.setFont(new Font("Arial", Font.BOLD, 15));
        goButton.setBounds(150, 43, 200, 30);
        panelInput1.add(goButton);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
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
                        BorderFactory.createLineBorder(Color.BLACK), // Outer black border
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
                        BorderFactory.createLineBorder(Color.BLACK), // Outer black border
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
                        BorderFactory.createLineBorder(Color.BLACK), // Outer black border
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
                        BorderFactory.createLineBorder(Color.BLACK), // Outer black border
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
                        BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                        BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
                ));
                panelInput.add(password);

                JButton registerButton = new JButton("Register");
                registerButton.setFont(new Font("Arial", Font.BOLD, 15));
                registerButton.setBounds(200, 205, 100, 30);
                panelInput.add(registerButton);

                for (User u : Database.UserList) {
                    if (u.ID.startsWith("S")) {
                        if (u.ID.equals(bookid.getText())) {
                            name.setText(u.Name);
                            address.setText(u.Address);
                            phone.setText(u.PhoneNumber);
                            email.setText(u.Email);
                            password.setText(u.getPassword());
                        }
                    }
                }
                ;

                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Name = name.getText();
                        String Address = address.getText();
                        String Phone = phone.getText();
                        String Email = email.getText();
                        String Password = String.valueOf(password.getPassword());

                        if (Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Email.isEmpty()
                                || Password.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                        } else {
                            for (User u : Database.UserList) {
                                if (u.ID.startsWith(role)) {
                                    if (u.ID.equals(bookid.getText())) {
                                        String insertQuery = String.format(
                                                "Update User SET Name = '%s', Address = '%s', PhoneNumber = '%s', Email = '%s', Password = '%s' WHERE concat(role, ID) = '%s'",
                                                Name, Address, Phone, Email, Password, bookid.getText());
                                        MySQLConnection.executeUpdate(insertQuery);
                                        JOptionPane.showMessageDialog(frame, "Update successful!");
                                        frame.dispose();
                                    }
                                }
                            }
                            ;
                        }
                    }
                });
            }
        });
    };

    public void deleteUser(String role) {
        Database.GetDataFromUser();
        JFrame frame1 = new JFrame();
        frame1.setSize(500, 200);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(null);

        JPanel panelTitle1 = new JPanel();
        panelTitle1.setBounds(0, 10, 500, 40);
        JLabel label1 = new JLabel("Update Book By ID");
        label1.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle1.add(label1);
        frame1.add(panelTitle1);

        JPanel panelInput1 = new JPanel();
        panelInput1.setBounds(0, 60, 500, 250);
        panelInput1.setLayout(null);
        frame1.add(panelInput1);

        JLabel BOOKID = new JLabel("Enter Book ID : ");
        BOOKID.setBounds(20, 0, 150, 30);
        BOOKID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput1.add(BOOKID);

        JTextField bookid = new JTextField(20);
        bookid.setBounds(160, 3, 300, 30);
        bookid.setFont(new Font("Arial", Font.PLAIN, 15));
        bookid.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        panelInput1.add(bookid);

        JButton goButton = new JButton("Go");
        goButton.setFont(new Font("Arial", Font.BOLD, 15));
        goButton.setBounds(150, 43, 200, 30);
        panelInput1.add(goButton);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = bookid.getText();
                for (User u : Database.UserList) {
                    if (u.ID.startsWith(role)) {
                        if (u.ID.equals(id)) {
                            String deleteQuery = String.format("DELETE FROM User WHERE concat(role, ID) = '%s'", id);
                            MySQLConnection.executeUpdate(deleteQuery);
                            JOptionPane.showMessageDialog(frame1, "User deleted successfully!");
                            frame1.dispose();
                        }
                    }
                }
            }
        });
    }

    public void searchUser(String keyword, String role) {
        // Create frame
        JFrame frame = new JFrame("JTable with ArrayList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 400);
        frame.setLayout(null);
        // Column names
        String[] columnNames = { "ID", "Name", "Address", "Phone Number", "Email", "Password" };
        // Create table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Loop through userList and add rows directly to the table model
        int found = 0;
        for (User user : Database.UserList) {
            if (user.ID.startsWith(role)) {
                if (user.ID.equals(keyword)) {
                    model.addRow(new Object[] { user.ID, user.Name, user.Address, user.PhoneNumber, user.Email,
                            user.getPassword() });
                    found = 1;
                    break;
                }
            }
        }
        if (found == 0) {
            JOptionPane.showMessageDialog(frame, "User not found!");
            return;
        }

        // Create JTable with model
        JTable table = new JTable(model);

        // Set table styles
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(Color.BLUE);
        table.setSelectionForeground(Color.WHITE);

        // Style table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        scrollPane.setBounds(10, 145, 700, 200);
        frame.add(scrollPane, BorderLayout.CENTER);
        // Set frame visibility
        frame.setVisible(true);
    }




    public void manageBook() {
        Database.GetDataFromBook();
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
        // Create Button Refresh
        JButton Refresh = new JButton("Refresh");
        Refresh.setFont(new Font("Arial", Font.BOLD, 15));
        Refresh.setForeground(Color.WHITE);

        Refresh.setBounds(610, 10, 100, 30);
        Refresh.setBackground(Color.RED);

        frame.add(Refresh);

        JPanel Title = new JPanel();
        Title.setBounds(0, 10, 700, 50);
        JLabel title = new JLabel("Manage Book");
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

        // Convert ArrayList of objects to 2D array
        String[][] data = new String[Database.bookList.size()][7]; // 3 columns
        for (int i = 0; i < Database.bookList.size(); i++) {
            Book p = Database.bookList.get(i);
            data[i][0] = String.valueOf(p.bookid);
            data[i][1] = p.bookname;
            data[i][2] = p.category;
            data[i][3] = p.author;
            data[i][4] = String.valueOf(p.price);
            data[i][5] = String.valueOf(p.quantity);
            data[i][6] = p.publisher;
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
        Refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                manageBook();
            }
        });
        // Add action listeners
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateBook();
            }
        });

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook(Search.getText());
            }
        });
        // Set frame visibility
        frame.setVisible(true);
    }

    public void updateBook() {
        Database.GetDataFromBook();

        JFrame frame1 = new JFrame();
        frame1.setSize(500, 200);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(null);

        JPanel panelTitle1 = new JPanel();
        panelTitle1.setBounds(0, 10, 500, 40);
        JLabel label1 = new JLabel("Update Book By ID");
        label1.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle1.add(label1);
        frame1.add(panelTitle1);

        JPanel panelInput1 = new JPanel();
        panelInput1.setBounds(0, 60, 500, 250);
        panelInput1.setLayout(null);
        frame1.add(panelInput1);

        JLabel BOOKID = new JLabel("Enter Book ID : ");
        BOOKID.setBounds(20, 0, 150, 30);
        BOOKID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput1.add(BOOKID);

        JTextField bookid = new JTextField(20);
        bookid.setBounds(160, 3, 300, 30);
        bookid.setFont(new Font("Arial", Font.PLAIN, 15));
        bookid.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        panelInput1.add(bookid);

        JButton goButton = new JButton("Go");
        goButton.setFont(new Font("Arial", Font.BOLD, 15));
        goButton.setBounds(150, 43, 200, 30);
        panelInput1.add(goButton);

        goButton.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setSize(500, 420);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);

            JButton Back = new JButton("Back");
            Back.setFont(new Font("Arial", Font.BOLD, 15));
            Back.setForeground(Color.WHITE);
            Back.setBackground(Color.RED);
            Back.setBounds(10, 10, 80, 30);
            frame.add(Back);

            JPanel panelTitle = new JPanel();
            panelTitle.setBounds(0, 10, 500, 40);
            JLabel label = new JLabel("Add Book");
            label.setFont(new Font("Arial", Font.BOLD, 25));
            panelTitle.add(label);
            frame.add(panelTitle);

            JPanel panelInput = new JPanel();
            panelInput.setBounds(0, 60, 500, 400);
            panelInput.setLayout(null);
            frame.add(panelInput);

            JTextField bookname = createTextField(160, 3);
            JTextField category = createTextField(160, 43);
            JTextField author = createTextField(160, 83);
            JTextField price = createTextField(160, 123);
            JTextField qty = createTextField(160, 163);
            JTextField publisher = createTextField(160, 203);

            panelInput.add(createLabel("Book Name : ", 0));
            panelInput.add(createLabel("Category : ", 40));
            panelInput.add(createLabel("Author : ", 80));
            panelInput.add(createLabel("Price : ", 120));
            panelInput.add(createLabel("Qty : ", 160));
            panelInput.add(createLabel("Publisher : ", 200));

            panelInput.add(bookname);
            panelInput.add(category);
            panelInput.add(author);
            panelInput.add(price);
            panelInput.add(qty);
            panelInput.add(publisher);

            JButton AddButton = new JButton("Add Book");
            AddButton.setFont(new Font("Arial", Font.BOLD, 15));
            AddButton.setBounds(150, 243, 200, 30);
            panelInput.add(AddButton);

            for (Book b : Database.bookList) {
                if (String.valueOf(b.bookid).equals(bookid.getText())) {
                    bookname.setText(b.bookname);
                    category.setText(b.category);
                    author.setText(b.author);
                    price.setText(String.valueOf(b.price));
                    qty.setText(String.valueOf(b.quantity));
                    publisher.setText(b.publisher);
                    break;
                }
            }

            AddButton.addActionListener(a -> {
                String insertQuery = String.format(
                        "UPDATE Book SET Category = '%s', Name = '%s', Author = '%s', Price = '%s', Qty = '%s', Publisher = '%s' WHERE ID = '%s'",
                        category.getText(), bookname.getText(), author.getText(), price.getText(), qty.getText(),
                        publisher.getText(), bookid.getText());
                MySQLConnection.executeUpdate(insertQuery);
            });
        });

    }

    public void addBook() {
        JFrame frame = new JFrame();
        frame.setSize(500, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);
        Back.setBackground(Color.RED);
        Back.setBounds(10, 10, 80, 30);
        frame.add(Back);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        JLabel label = new JLabel("Add Book");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);
        frame.add(panelTitle);

        JPanel panelInput = new JPanel();
        panelInput.setBounds(0, 60, 500, 400);
        panelInput.setLayout(null);
        frame.add(panelInput);

        JTextField bookname = createTextField(160, 3);
        JTextField category = createTextField(160, 43);
        JTextField author = createTextField(160, 83);
        JTextField price = createTextField(160, 123);
        JTextField qty = createTextField(160, 163);
        JTextField publisher = createTextField(160, 203);

        panelInput.add(createLabel("Book Name : ", 0));
        panelInput.add(createLabel("Category : ", 40));
        panelInput.add(createLabel("Author : ", 80));
        panelInput.add(createLabel("Price : ", 120));
        panelInput.add(createLabel("Qty : ", 160));
        panelInput.add(createLabel("Publisher : ", 200));

        panelInput.add(bookname);
        panelInput.add(category);
        panelInput.add(author);
        panelInput.add(price);
        panelInput.add(qty);
        panelInput.add(publisher);

        JButton AddButton = new JButton("Add Book");
        AddButton.setFont(new Font("Arial", Font.BOLD, 15));
        AddButton.setBounds(150, 243, 200, 30);
        panelInput.add(AddButton);
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookname1 = bookname.getText();
                String category1 = category.getText();
                String author1 = author.getText();
                String price1 = price.getText();
                String quantity1 = qty.getText();
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
            };
        });
    }

    public void deleteBook() {
        Database.GetDataFromBook();
        if (Database.bookList.isEmpty()) {
            System.out.println("Book list is empty.");
            return;
        }
        Database.GetDataFromBook();
        JFrame frame1 = new JFrame();
        frame1.setSize(500, 200);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(null);

        JPanel panelTitle1 = new JPanel();
        panelTitle1.setBounds(0, 10, 500, 40);
        JLabel label1 = new JLabel("Update Book By ID");
        label1.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle1.add(label1);

        JPanel panelButton = new JPanel();
        panelButton.setBounds(0, 60, 500, 650);
        panelButton.setLayout(null);

        frame1.add(panelTitle1);

        JPanel panelInput1 = new JPanel();
        panelInput1.setBounds(0, 60, 500, 250);
        panelInput1.setLayout(null);
        frame1.add(panelInput1);

        JLabel BOOKID = new JLabel("Enter Book ID : ");
        BOOKID.setBounds(20, 0, 150, 30);
        BOOKID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput1.add(BOOKID);

        JTextField bookid = new JTextField(20);
        bookid.setBounds(160, 3, 300, 30);
        bookid.setFont(new Font("Arial", Font.PLAIN, 15));
        bookid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput1.add(bookid);

        JButton goButton = new JButton("Go");
        goButton.setFont(new Font("Arial", Font.BOLD, 15));
        goButton.setBounds(150, 43, 200, 30);
        panelInput1.add(goButton);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = bookid.getText();
                String deleteQuery = String.format("DELETE FROM Book WHERE ID = '%s'", ID);
                MySQLConnection.executeUpdate(deleteQuery);
            };
        });
    };




    public void manageBorrow() {
        Database.GetDataFromBorrow();

        // Create frame
        JFrame frame = new JFrame("JTable with ArrayList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 400);
        frame.setLayout(null);

        // Create Button Back
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);

        Back.setBounds(10, 10, 100, 30);
        Back.setBackground(Color.RED);

        frame.add(Back);

        // Create Title
        JPanel Title = new JPanel();
        Title.setBounds(0, 10, 1400, 50);
        JLabel title = new JLabel("Manage Borrow");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        Title.add(title);
        frame.add(Title);

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

        JButton Add = new JButton("Add");
        Add.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(Add);

        JButton Update = new JButton("Update");
        Update.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(Update);

        JButton Delete = new JButton("Delete");
        Delete.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(Delete);

        JButton FilterStudent = new JButton("Filter Borrow");
        FilterStudent.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(FilterStudent);

        JButton FilterLibrarian = new JButton("Filter Returned");
        FilterLibrarian.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(FilterLibrarian);
        frame.add(ActionPanel);

        // Column names
        String[] columnNames = { "Borrow ID", "Book ID", "Book Name", "Student ID", "Student Name", "Librarian ID",
                "Librarian Name",
                "Borrow Date", "Return Date", "Payment","Librarian ID", "Librarian Name", "Returned Date" };

        // Convert ArrayList of objects to 2D array
        String[][] data = new String[Database.borrowList.size()][13]; // 3 columns
        int i = 0;
        for (HashMap<String, Object> p : Database.borrowList) {
            data[i][0] = p.get("borrowId").toString();
            data[i][1] = p.get("bookId").toString();
            data[i][2] = p.get("bookName").toString();
            data[i][3] = p.get("studentId").toString();
            data[i][4] = p.get("studentName").toString();
            data[i][5] = p.get("librarianId").toString();
            data[i][6] = p.get("librarianName").toString();
            data[i][7] = p.get("borrowDate").toString();
            data[i][8] = p.get("returnDate").toString();
            data[i][9] = p.get("payment").toString();
            data[i][10] = p.get("librarianReturnId").toString();
            data[i][11] = p.get("librarianReturnName").toString();
            data[i][12] = p.get("ReturnedDate").toString();
            i++;
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
        scrollPane.setBounds(10, 145, 1370, 200);

        // Add scroll pane to frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set frame visibility
        frame.setVisible(true);
    }

    public void addBorrow() {
                        // Create frame
        JFrame frame = new JFrame("JTable with ArrayList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLayout(null);

        // Create Button Back
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);

        Back.setBounds(10, 10, 100, 30);
        Back.setBackground(Color.RED);

        frame.add(Back);

        // Create Title
        JPanel Title = new JPanel();
        Title.setBounds(0, 10, 500, 50);
        JLabel title = new JLabel("Update Borrow");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        Title.add(title);
        frame.add(Title);

        // Create Input Panel
        JPanel panelInput = new JPanel();
        panelInput.setBounds(0, 60, 500, 350);
        panelInput.setLayout(null);
        

        JLabel BookID = new JLabel("Book ID : ");
        BookID.setBounds(20, 0, 150, 30);
        BookID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(BookID);
        JTextField bookid = new JTextField(20);
        bookid.setBounds(160, 3, 300, 30);
        bookid.setFont(new Font("Arial", Font.PLAIN, 15));
        bookid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(bookid);

        JLabel StudentID = new JLabel("Studen ID : ");
        StudentID.setBounds(20, 40, 150, 30);
        StudentID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(StudentID);
        JTextField studentid = new JTextField(20);
        studentid.setBounds(160, 43, 300, 30);
        studentid.setFont(new Font("Arial", Font.PLAIN, 15));
        studentid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(studentid);

        JLabel LibrarianID = new JLabel("Librarian ID : ");
        LibrarianID.setBounds(20, 80, 150, 30);
        LibrarianID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(LibrarianID);
        JTextField librarianid = new JTextField(20);
        librarianid.setBounds(160, 83, 300, 30);
        librarianid.setFont(new Font("Arial", Font.PLAIN, 15));
        librarianid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(librarianid);

        JLabel BorrowDate = new JLabel("Borrow Date : ");
        BorrowDate.setBounds(20, 120, 150, 30);
        BorrowDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(BorrowDate);
        JTextField borrowdate = new JTextField(20);
        borrowdate.setBounds(160, 123, 300, 30);
        borrowdate.setFont(new Font("Arial", Font.PLAIN, 15));
        borrowdate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(borrowdate);

        JLabel ReturnDate = new JLabel("Return Date : ");
        ReturnDate.setBounds(20, 160, 150, 30);
        ReturnDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(ReturnDate);
        JTextField returndate = new JTextField(20);
        returndate.setBounds(160, 163, 300, 30);
        returndate.setFont(new Font("Arial", Font.PLAIN, 15));
        returndate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(returndate);

        JLabel LibrarianReturnID = new JLabel("Lib Return ID : ");
        LibrarianReturnID.setBounds(20, 200, 150, 30);
        LibrarianReturnID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(LibrarianReturnID);
        JTextField librarianreturnid = new JTextField(20);
        librarianreturnid.setBounds(160, 203, 300, 30);
        librarianreturnid.setFont(new Font("Arial", Font.PLAIN, 15));
        librarianreturnid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(librarianreturnid);

        JLabel ReturnedDate = new JLabel("Returned Date : ");
        ReturnedDate.setBounds(20, 240, 150, 30);
        ReturnedDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(ReturnedDate);
        JTextField returneddate = new JTextField(20);
        returneddate.setBounds(160, 243, 300, 30);
        returneddate.setFont(new Font("Arial", Font.PLAIN, 15));
        returneddate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(returneddate);

        JButton UpdateButton = new JButton("Update");
        UpdateButton.setFont(new Font("Arial", Font.BOLD, 15));
        UpdateButton.setBounds(175, 290, 150, 30);
        panelInput.add(UpdateButton);
        frame.add(panelInput);
        frame.setVisible(true);
        
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.GetDataFromBook();
                Database.GetDataFromUser();
                String payment = "";
                String bookname = "";
                String studentname = "";
                String librarianname = "";
                String librarianreturnname = "";
                int found = 0;
                for(Book b : Database.bookList) {
                    if(String.valueOf(b.bookid).equals(bookid.getText())) {
                        payment = String.valueOf(b.price);
                        bookname = b.bookname;
                        found = 1;
                        break;
                    }
                }
                for(User u : Database.UserList) {
                    if(String.valueOf(u.ID).equals(studentid.getText())) {
                        studentname = u.Name;
                        found = 1;
                    }
                    if(String.valueOf(u.ID).equals(librarianid.getText())) {
                        librarianname = u.Name;
                        found = 1;
                    }
                    if(String.valueOf(u.ID).equals(librarianreturnid.getText())) {
                        librarianreturnname = u.Name;
                        found = 1;
                    }else{
                        librarianreturnname = "None";
                    }
                }
                if(found == 0) {
                    JOptionPane.showMessageDialog(null, "Not found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                };
                String format = "INSERT INTO borrowlist (BookId, BookName, StudentId, StudentName, LibrarianId, LibrarianName, BorrowDate, ReturnDate, payment,LibrarianReturnId, LibrarianReturnName, ReturnedDate) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
                String insertQuery = String.format(format, bookid.getText(), bookname, studentid.getText(), studentname, librarianid.getText(), librarianname, borrowdate.getText(), returndate.getText(), payment ,librarianreturnid.getText(), librarianreturnname, returneddate.getText());
                MySQLConnection.executeUpdate(insertQuery);
                
                if(String.valueOf(librarianreturnid.getText()).equals("None") || String.valueOf(returneddate.getText()).equals("None")) {
                   String updateQty = "UPDATE Book SET Qty = Qty - 1 WHERE ID = '" + bookid.getText() + "'";
                    MySQLConnection.executeUpdate(updateQty);
                }
            }});
        };

    public void updateBorrow(int BorrowID) {
        // Create frame
        JFrame frame = new JFrame("JTable with ArrayList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLayout(null);

        // Create Button Back
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);

        Back.setBounds(10, 10, 100, 30);
        Back.setBackground(Color.RED);

        frame.add(Back);

        // Create Title
        JPanel Title = new JPanel();
        Title.setBounds(0, 10, 500, 50);
        JLabel title = new JLabel("Update Borrow");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        Title.add(title);
        frame.add(Title);

        // Create Input Panel
        JPanel panelInput = new JPanel();
        panelInput.setBounds(0, 60, 500, 350);
        panelInput.setLayout(null);
        

        JLabel BookID = new JLabel("Book ID : ");
        BookID.setBounds(20, 0, 150, 30);
        BookID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(BookID);
        JTextField bookid = new JTextField(20);
        bookid.setBounds(160, 3, 300, 30);
        bookid.setFont(new Font("Arial", Font.PLAIN, 15));
        bookid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(bookid);

        JLabel StudentID = new JLabel("Studen ID : ");
        StudentID.setBounds(20, 40, 150, 30);
        StudentID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(StudentID);
        JTextField studentid = new JTextField(20);
        studentid.setBounds(160, 43, 300, 30);
        studentid.setFont(new Font("Arial", Font.PLAIN, 15));
        studentid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(studentid);

        JLabel LibrarianID = new JLabel("Librarian ID : ");
        LibrarianID.setBounds(20, 80, 150, 30);
        LibrarianID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(LibrarianID);
        JTextField librarianid = new JTextField(20);
        librarianid.setBounds(160, 83, 300, 30);
        librarianid.setFont(new Font("Arial", Font.PLAIN, 15));
        librarianid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(librarianid);

        JLabel BorrowDate = new JLabel("Borrow Date : ");
        BorrowDate.setBounds(20, 120, 150, 30);
        BorrowDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(BorrowDate);
        JTextField borrowdate = new JTextField(20);
        borrowdate.setBounds(160, 123, 300, 30);
        borrowdate.setFont(new Font("Arial", Font.PLAIN, 15));
        borrowdate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(borrowdate);

        JLabel ReturnDate = new JLabel("Return Date : ");
        ReturnDate.setBounds(20, 160, 150, 30);
        ReturnDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(ReturnDate);
        JTextField returndate = new JTextField(20);
        returndate.setBounds(160, 163, 300, 30);
        returndate.setFont(new Font("Arial", Font.PLAIN, 15));
        returndate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(returndate);

        JLabel LibrarianReturnID = new JLabel("Lib Return ID : ");
        LibrarianReturnID.setBounds(20, 200, 150, 30);
        LibrarianReturnID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(LibrarianReturnID);
        JTextField librarianreturnid = new JTextField(20);
        librarianreturnid.setBounds(160, 203, 300, 30);
        librarianreturnid.setFont(new Font("Arial", Font.PLAIN, 15));
        librarianreturnid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(librarianreturnid);

        JLabel ReturnedDate = new JLabel("Returned Date : ");
        ReturnedDate.setBounds(20, 240, 150, 30);
        ReturnedDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(ReturnedDate);
        JTextField returneddate = new JTextField(20);
        returneddate.setBounds(160, 243, 300, 30);
        returneddate.setFont(new Font("Arial", Font.PLAIN, 15));
        returneddate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(returneddate);

        JButton UpdateButton = new JButton("Update");
        UpdateButton.setFont(new Font("Arial", Font.BOLD, 15));
        UpdateButton.setBounds(175, 290, 150, 30);
        panelInput.add(UpdateButton);
        frame.add(panelInput);
        frame.setVisible(true);
        Database.GetDataFromBorrow();
        for(HashMap<String, Object> borrow : Database.borrowList) {
            if(String.valueOf(borrow.get("borrowId")).equals(String.valueOf(BorrowID))) {
                bookid.setText(borrow.get("bookId").toString());
                studentid.setText(borrow.get("studentId").toString());
                librarianid.setText(borrow.get("librarianId").toString());
                borrowdate.setText(borrow.get("borrowDate").toString());
                returndate.setText(borrow.get("returnDate").toString());
                librarianreturnid.setText(borrow.get("librarianReturnId").toString());
                returneddate.setText(borrow.get("ReturnedDate").toString());
            }
        }
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.GetDataFromBook();
                Database.GetDataFromUser();
                String bookname = "";
                String studentname = "";
                String librarianname = "";
                String librarianreturnname = "";
                for(Book b : Database.bookList) {    
                    if(b.bookid == Integer.parseInt(bookid.getText())) {
                        bookname = b.bookname;
                        break;
                    }
                }
                for(User u : Database.UserList) {
                    if(u.ID.equals(studentid.getText())) {
                        studentname = u.Name;
                    }
                    if(u.ID.equals(librarianid.getText())) {
                        librarianname = u.Name;
                    }
                    if(u.ID.equals(librarianreturnid.getText())) {
                        librarianreturnname = u.Name;
                    }
                }

                String format = "UPDATE borrowlist SET BookId = '%s', BookName = '%s', StudentId = '%s', StudentName = '%s', LibrarianId = '%s', LibrarianName = '%s', BorrowDate = '%s', ReturnDate = '%s', LibrarianReturnId = '%s', LibrarianReturnName = '%s', ReturnedDate = '%s' WHERE borrowId = '%s'";
                String UpdateQuery = String.format(format, bookid.getText(), bookname, studentid.getText(), studentname, librarianid.getText(), librarianname, borrowdate.getText(), returndate.getText(), librarianreturnid.getText(), librarianreturnname, returneddate.getText(), BorrowID);
                MySQLConnection.executeUpdate(UpdateQuery);
            }});
    }

    public void deleteBorrow(int BorrowID) {
        Database.GetDataFromBorrow();
        for(HashMap<String, Object> borrow : Database.borrowList) {
            if(String.valueOf(borrow.get("borrowId")).equals(String.valueOf(BorrowID))) {
                String format = "DELETE FROM borrowlist WHERE borrowId = '%s'";
                String DeleteQuery = String.format(format, String.valueOf(BorrowID));
                MySQLConnection.executeUpdate(DeleteQuery);
            }
        }
    }

    public void searchBorrow(int BorrowID) {
        Database.GetDataFromBorrow();
                // Create frame
                JFrame frame = new JFrame("JTable with ArrayList");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1400, 400);
                frame.setLayout(null);

                // Create Button Back
                JButton Back = new JButton("Back");
                Back.setFont(new Font("Arial", Font.BOLD, 15));
                Back.setForeground(Color.WHITE);

                Back.setBounds(10, 10, 100, 30);
                Back.setBackground(Color.RED);

                frame.add(Back);

                // Column names
                        String[] columnNames = { "Borrow ID", "Book ID", "Book Name", "Student ID", "Student Name", "Librarian ID",
                        "Librarian Name",
                        "Borrow Date", "Return Date", "Payment","Librarian ID", "Librarian Name", "Returned Date" };

                // Create DefaultTableModel
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                // Add rows directly from bookList
                for (HashMap<String, Object> p : Database.borrowList){
                if(String.valueOf(p.get("borrowId")).equals(String.valueOf(BorrowID))) {
                        Object[] row = { p.get("borrowId"), p.get("bookId"), p.get("bookName"), p.get("studentId"), p.get("studentName"), p.get("librarianId"),p.get("librarianName"),p.get("borrowDate"),p.get("returnDate"),p.get("payment"),p.get("librarianReturnId"),p.get("librarianReturnName"),p.get("ReturnedDate") };
                        model.addRow(row);
                    }
                }
            
                
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
                scrollPane.setBounds(10, 145, 1370, 200);

                // Add scroll pane to frame
                frame.add(scrollPane, BorderLayout.CENTER);

                // Set frame visibility
                frame.setVisible(true);
            
        };
    

    // Display borrowed list
    public void displayBorrow() {
        Database.GetDataFromBorrow();
        if (Database.borrowList.isEmpty()) {
            System.out.println("Borrow list is empty.");
        }
        int count = 0;
        System.out.println(
                "#--------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println(
                "|                                                                                                                                |");
        System.out.println(
                "#                                      Borrowed list (Not returned yet) in Library Management System                             #");
        System.out.println(
                "|                                                                                                                                |");
        System.out.println(
                "#--------------------------------------------------------------------------------------------------------------------------------#");
        int check = 0;
        String format = "| %-6s | %-18s | %-5s | %-30s | %-6s | %-18s | %-25s |\n";
        System.out.println(
                "+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        System.out.printf(format, "Stu ID", "Name", "B ID", "Book Name", "Lib ID", "Librarian Name", "Borrow Date");
        System.out.println(
                "+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        for (HashMap<String, Object> b : Database.borrowList) {
            if (b.get("ReturnedDate").equals("None")) {
                System.out.printf(format, b.get("studentId"), b.get("studentName"), b.get("bookId"), b.get("bookName"),
                        b.get("librarianId"), b.get("librarianName"),
                        b.get("borrowDate") + " -> " + b.get("returnDate"));
                count++;
                check = 1;
            }
        }
        System.out.println(
                "+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        if (check == 0) {
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
        System.out.println("--------------- Total borrowed : " + count + " --------------- ");
    }

    // Display returned list
    public void displayReturn() {
        Database.GetDataFromBorrow();
        if (Database.borrowList.isEmpty()) {
            System.out.println("No data in borrow list");
            return;
        }
        int count = 0;
        System.out.println(
                "#--------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println(
                "|                                                                                                                                |");
        System.out.println(
                "#                                          Returned list in Library Management System                                            #");
        System.out.println(
                "|                                                                                                                                |");
        System.out.println(
                "#--------------------------------------------------------------------------------------------------------------------------------#");
        int check = 0;
        String format = "| %-6s | %-18s | %-5s | %-30s | %-6s | %-18s | %-25s |\n";
        System.out.println(
                "+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        System.out.printf(format, "Stu ID", "Name", "B ID", "Book Name", "Lib ID", "Librarian Name", "Returned Date");
        System.out.println(
                "+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        for (HashMap<String, Object> b : Database.borrowList) {
            if (!b.get("ReturnedDate").equals("None")) {
                System.out.printf(format, b.get("studentId"), b.get("studentName"), b.get("bookId"), b.get("bookName"),
                        b.get("librarianReturnId"), b.get("librarianReturnName"), b.get("ReturnedDate"));
                count++;
                check = 1;
                System.out.println(
                        "+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
            }
        }
        if (check == 0) {
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }

        System.out.println("--------------- Total returned : " + count + " --------------- ");
    }

    // Add book Finish Remain Exception

    // Delete book

    // Add book qty
    public void addBookQuantityByID() {
        Database.GetDataFromBook();
        if (Database.bookList.isEmpty()) {
            System.out.println("Book list is empty.");
            return;
        }
        int ID;
        while (true) {
            try {
                System.out.print("ID : ");
                ID = scanner.nextInt();
                NumberOnlyException test = new NumberOnlyException(String.valueOf(ID), "^?[0-9]+$");
                NumberOnlyException test2 = new NumberOnlyException(ID);
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        int foundBook = 0;
        for (Book b : Database.bookList) {
            if (b.bookid == ID) {
                {
                    System.out.println("How many books do you want to add?");
                    System.out.print("Enter quantity: ");
                    int newQuantity = scanner.nextInt();
                    int updateQty = b.quantity + newQuantity;
                    String Update = "UPDATE Book SET Qty = '" + updateQty + "' WHERE ID = '" + ID + "'";
                    MySQLConnection.executeUpdate(Update);
                    foundBook = 1;
                    break;
                }
            }
            if (foundBook == 0) {
                System.out.println("Book not found.");
            }
        }
    };

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField(20);
        textField.setBounds(x, y, 300, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        return textField;
    }

    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(20, y, 150, 30);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        return label;
    }
}
