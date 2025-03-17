package Library;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Exception.InputException;
import Exception.NumberOnlyException;
import java.awt.*;

public class Student extends User {
    Scanner scanner = new Scanner(System.in);

    // Constructor
    public Student(String ID, String Name, String Address, String PhoneNumber, String Email, String password) {
        super(ID, Name, Address, PhoneNumber, Email, password);
    }

    public Student() {
    };

    public void StudentFeatures() {
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        JLabel label = new JLabel("Welcome to Student Features");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);

        JPanel panelButton = new JPanel();
        panelButton.setBounds(0, 60, 500, 650);
        panelButton.setLayout(null);

        JButton button1 = new JButton("Display Books");
        button1.setFont(new Font("Arial", Font.BOLD, 15));
        button1.setBounds(150, 0, 200, 40);
        JButton button2 = new JButton("Borrow Book");
        button2.setFont(new Font("Arial", Font.BOLD, 15));
        button2.setBounds(150, 50, 200, 40);
        JButton button3 = new JButton("Return Book");
        button3.setFont(new Font("Arial", Font.BOLD, 15));
        button3.setBounds(150, 100, 200, 40);

        JButton button4 = new JButton("Log out");
        button4.setFont(new Font("Arial", Font.BOLD, 15));
        button4.setBounds(150, 150, 200, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayAction();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrowAction();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnAction();
            }
        });
        panelButton.add(button1);
        panelButton.add(button2);
        panelButton.add(button3);
        panelButton.add(button4);
        frame.add(panelButton);
        frame.add(panelTitle);
    }

    public void BorrowAction() {
        Database.GetDataFromUser();
        Database.GetDataFromBook();

        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Create Button Back
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);

        Back.setBounds(10, 10, 80, 30);
        Back.setBackground(Color.RED);
        Back.setLayout(null);

        frame.add(Back);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        frame.add(panelTitle);

        JLabel label = new JLabel("Borrow Action");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);

        JPanel panelInput = new JPanel();
        panelInput.setBounds(0, 60, 500, 250);
        panelInput.setLayout(null);
        frame.add(panelInput);

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

        JLabel LibrarianID = new JLabel("Librarian ID : ");
        LibrarianID.setBounds(20, 40, 150, 30);
        LibrarianID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(LibrarianID);
        JTextField librarianid = new JTextField(20);
        librarianid.setBounds(160, 43, 300, 30);
        librarianid.setFont(new Font("Arial", Font.PLAIN, 15));
        librarianid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(librarianid);

        JLabel BorrowDate = new JLabel("Borrow Date : ");
        BorrowDate.setBounds(20, 80, 150, 30);
        BorrowDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(BorrowDate);
        JTextField borrowdate = new JTextField(20);
        borrowdate.setBounds(160, 83, 300, 30);
        borrowdate.setFont(new Font("Arial", Font.PLAIN, 15));
        borrowdate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(borrowdate);

        JLabel ReturnDate = new JLabel("Return Date: ");
        ReturnDate.setBounds(20, 120, 150, 30);
        ReturnDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(ReturnDate);
        JTextField returndate = new JTextField(20);
        returndate.setBounds(160, 123, 300, 30);
        returndate.setFont(new Font("Arial", Font.PLAIN, 15));
        returndate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(returndate);

        JButton BorrowButton = new JButton("Borrow");
        BorrowButton.setFont(new Font("Arial", Font.BOLD, 15));
        BorrowButton.setBounds(200, 163, 100, 30);
        panelInput.add(BorrowButton);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        BorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = "";
                String librarianName = "";
                String bookName = "";
                String payment = "";

                for (Book book : Database.bookList) {
                    if (book.bookid == Integer.parseInt(bookid.getText())) {
                        bookName = book.bookname;
                        payment = String.valueOf(book.price * 0.1);
                        break;
                    }
                }

                for (User user : Database.UserList) {
                    if (user.ID.equals(ID)) {
                        studentName = user.Name;
                    }
                    if (user.ID.equals(librarianid.getText())) {
                        librarianName = user.Name;
                    }
                }
                DisplayInvoice(bookid.getText(), bookName, ID, studentName, librarianid.getText(), librarianName,
                        borrowdate.getText(), returndate.getText(), payment);
                frame.dispose();
            }
        });
    }

    public void ReturnAction() {
        Database.GetDataFromBorrow();
        JFrame frame = new JFrame();
        frame.setSize(500, 260);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Create Button Back
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);

        Back.setBounds(10, 10, 80, 30);
        Back.setBackground(Color.RED);
        Back.setLayout(null);

        frame.add(Back);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        frame.add(panelTitle);

        JLabel label = new JLabel("Return Action");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);

        JPanel panelInput = new JPanel();
        panelInput.setBounds(0, 60, 500, 250);
        panelInput.setLayout(null);
        frame.add(panelInput);

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

        JLabel LibrarianID = new JLabel("Librarian ID : ");
        LibrarianID.setBounds(20, 40, 150, 30);
        LibrarianID.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(LibrarianID);
        JTextField librarianid = new JTextField(20);
        librarianid.setBounds(160, 43, 300, 30);
        librarianid.setFont(new Font("Arial", Font.PLAIN, 15));
        librarianid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(librarianid);

        JLabel ReturnedDate = new JLabel("Return Date : ");
        ReturnedDate.setBounds(20, 80, 150, 30);
        ReturnedDate.setFont(new Font("Arial", Font.BOLD, 15));
        panelInput.add(ReturnedDate);
        JTextField returneddate = new JTextField(20);
        returneddate.setBounds(160, 83, 300, 30);
        returneddate.setFont(new Font("Arial", Font.PLAIN, 15));
        returneddate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(0, 10, 0, 10) // Inner padding (top, left, bottom, right)
        ));
        panelInput.add(returneddate);

        JButton registerButton = new JButton("Borrow");
        registerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerButton.setBounds(200, 123, 100, 30);
        panelInput.add(registerButton);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.GetDataFromUser();
                String librarianReturnName = "";
                for (User user : Database.UserList) {
                    if(user.ID.equals(librarianid.getText())) {
                        librarianReturnName = user.Name;
                        break;
                    }
                }
                String InsertQuery = String.format(
                        "UPDATE borrowlist SET ReturnedDate = '%s', LibrarianReturnID = '%s', LibrarianReturnName = '%s' WHERE BookID = '%s' and StudentId = '%s'",
                        returneddate.getText(), librarianid.getText(), librarianReturnName, bookid.getText(), ID);
                MySQLConnection.executeUpdate(InsertQuery);
                
                String updateQty = "UPDATE Book SET Qty = Qty + 1 WHERE ID = '" + bookid.getText() + "'";
                MySQLConnection.executeUpdate(updateQty);
                frame.dispose();
            }
        });
    };

    public void DisplayAction() {
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
        JLabel title = new JLabel("Display Book");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        Title.add(title);
        frame.add(Title);

        JPanel ActionPanel = new JPanel();
        ActionPanel.setBounds(0, 100, 700, 40);

        JButton BorrowButton = new JButton("Borrow Book");
        BorrowButton.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(BorrowButton);

        JButton ReturnButton = new JButton("Return Book");
        ReturnButton.setFont(new Font("Arial", Font.BOLD, 15));
        ActionPanel.add(ReturnButton);

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

        frame.setVisible(true);

        // Add action listener to Back button
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add action listener to Refresh button
        Refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DisplayAction();
            }
        });

        // Add action listener to Borrow button
        BorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BorrowAction();
            }
        });

        // Add action listener to Return button
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ReturnAction();
            }
        });

        // Add action listener to Search button
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook(Search.getText());
            }
        });

    }

    public void DisplayInvoice(String bookId, String bookName, String studentId, String studentName, String librarianId,
            String Librarianname, String borrowDate, String returnDate, String payment) {
        // Create frame
        JFrame frame = new JFrame("Library Invoice");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 10, 500, 40);
        frame.add(panelTitle);

        JLabel label = new JLabel("Invoice Details Borrowing");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);

        // Invoice Content Panel
        JPanel invoicePanel = new JPanel();
        invoicePanel.setBounds(20, 60, 450, 330);
        invoicePanel.setLayout(null);
        invoicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        invoicePanel.setBackground(Color.WHITE);
        frame.add(invoicePanel);

        // Labels for Invoice Details
        int yPosition = 10;
        JLabel StudentID = new JLabel("Student ID: " + studentId);
        StudentID.setFont(new Font("Arial", Font.PLAIN, 16));
        StudentID.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(StudentID);
        yPosition += 40;

        JLabel StudentName = new JLabel("Student Name: " + studentName);
        StudentName.setFont(new Font("Arial", Font.PLAIN, 16));
        StudentName.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(StudentName);
        yPosition += 40;

        JLabel LibrarianID = new JLabel("Librarian ID: " + librarianId);
        LibrarianID.setFont(new Font("Arial", Font.PLAIN, 16));
        LibrarianID.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(LibrarianID);
        yPosition += 40;

        JLabel LibrarianName = new JLabel("Librarian Name: " + Librarianname);
        LibrarianName.setFont(new Font("Arial", Font.PLAIN, 16));
        LibrarianName.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(LibrarianName);
        yPosition += 40;

        JLabel Dates = new JLabel("Date: " + borrowDate + "  |  " + returnDate);
        Dates.setFont(new Font("Arial", Font.PLAIN, 16));
        Dates.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(Dates);
        yPosition += 40;

        // Signature Area
        JLabel BookID = new JLabel("Book ID: " + bookId);
        BookID.setFont(new Font("Arial", Font.PLAIN, 16));
        BookID.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(BookID);
        yPosition += 40;

        // Signature Area
        JLabel BookName = new JLabel("Book Name : " + bookName);
        BookName.setFont(new Font("Arial", Font.PLAIN, 16));
        BookName.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(BookName);
        yPosition += 40;

        // Payment Info
        JLabel Payment = new JLabel("Payment : $ " + payment);
        Payment.setFont(new Font("Arial", Font.BOLD, 18));
        Payment.setForeground(Color.GREEN);
        Payment.setBounds(20, yPosition, 400, 30);
        invoicePanel.add(Payment);

        // Print Button
        JButton btnAccept = new JButton("Accept");
        btnAccept.setFont(new Font("Arial", Font.BOLD, 15));
        btnAccept.setBounds(180, 405, 140, 40);
        frame.add(btnAccept);

        btnAccept.addActionListener(e -> {
            String format = "INSERT INTO borrowlist (BookId, BookName, StudentId, StudentName, LibrarianId, LibrarianName, BorrowDate, ReturnDate, payment,LibrarianReturnId, LibrarianReturnName, ReturnedDate) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
            String InsertQuery = String.format(format, bookId, bookName, studentId, studentName, librarianId,
                    Librarianname, borrowDate, returnDate, payment,"None", "None", "None");
            MySQLConnection.executeUpdate(InsertQuery);
            String updateQty = "UPDATE Book SET Qty = Qty - 1 WHERE ID = '" + bookId + "'";
            MySQLConnection.executeUpdate(updateQty);
            frame.dispose();
        });

        // Display Frame
        frame.setVisible(true);
    }

    // Return
    public void Returned() {
        Database.GetDataFromUser();
        Database.GetDataFromBorrow();
        Database.GetDataFromBook();

        String bookID;
        while (true) {
            try {
                System.out.print("Enter book ID : ");
                bookID = scanner.nextLine();
                NumberOnlyException test1 = new NumberOnlyException(bookID, "^-?[0-9]+$");
                NumberOnlyException test2 = new NumberOnlyException(Integer.parseInt(bookID));
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        String librarianID;
        while (true) {
            try {
                System.out.print("Enter librarian ID : ");
                librarianID = scanner.nextLine();
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$",
                        "Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }

        String LirbrarianReturnName = "";
        for (User l : Database.UserList) {
            if (librarianID.equals(l.ID)) {
                LirbrarianReturnName = l.Name;
                break;
            }
        }
        System.out.print("Enter return Date : ");
        String returnedDate = scanner.nextLine();
        int IsReturned = 0;
        for (HashMap<String, Object> b : Database.borrowList) {
            if (String.valueOf(b.get("ReturnedDate")).equals("None")) {
                if (String.valueOf(b.get("bookId")).equals(bookID) && String.valueOf(b.get("studentId")).equals(ID)) {
                    String Update = ("Update BorrowList set LibrarianReturnId='" + librarianID
                            + "', LibrarianReturnName='" + LirbrarianReturnName + "', ReturnedDate='" + returnedDate
                            + "' where BookId='" + bookID + "' and StudentId='" + ID + "'");
                    MySQLConnection.executeUpdate(Update);
                    for (Book B : Database.bookList) {
                        if (B.bookid == Integer.parseInt(bookID)) {
                            String UpdateB = "Update Book set Qty=Qty+1 where ID='" + B.bookid + "'";
                            MySQLConnection.executeUpdate(UpdateB);
                            break;
                        }
                    }
                    IsReturned = 1;
                    break;
                } else
                    IsReturned = 2;
            }
        }
        if (IsReturned == 0) {
            System.out.println("You have already returned this book !!");
        } else if (IsReturned == 1) {
            System.out.println("Returned Success");
        } else if (IsReturned == 2) {
            System.out.println("You haven't borrow this Book!!");
        }
    }

    // Borrow
    public void Borrow() {
        Database.GetDataFromUser();
        Database.GetDataFromBook();
        Database.GetDataFromBorrow();
        HashMap<String, Object> borrow = new HashMap<>();

        String bookID;
        while (true) {
            try {
                System.out.print("Enter book ID : ");
                bookID = scanner.nextLine();
                NumberOnlyException test1 = new NumberOnlyException(bookID, "^-?[0-9]+$");
                NumberOnlyException test2 = new NumberOnlyException(Integer.parseInt(bookID));
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        String bookName = "";
        int foundBook = 0;
        for (Book b : Database.bookList) {
            if (Integer.parseInt(bookID) == b.bookid) {
                borrow.put("bookName", b.bookname);
                bookName = b.bookname;
                foundBook = 1;
                break;
            }
        }
        if (foundBook == 0) {
            System.out.println("Book doesn't exist.");
            Database.TmpBorrow.clear();
            return;
        }
        for (Book b : Database.bookList) {
            if (Integer.parseInt(bookID) == b.bookid) {
                borrow.put("payForBorrow", b.price * 0.1);
                break;
            }
        }

        String librarianID;
        while (true) {
            try {
                System.out.print("Enter librarian ID : ");
                librarianID = scanner.nextLine();
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$",
                        "Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        String librarianName = "";
        int foundLibrarian = 0;
        for (User l : Database.UserList) {
            if (librarianID.equals(l.ID)) {
                borrow.put("librarianName", l.Name);
                librarianName = l.Name;
                foundLibrarian = 1;
                break;
            }
        }
        if (foundLibrarian == 0) {
            System.out.println("Librarian doesn't exist.");
            Database.TmpBorrow.clear();
            return;
        }

        System.out.print("Enter borrow Date : ");
        String borrowDate = scanner.nextLine();
        System.out.print("Enter return Date : ");
        String returnDate = scanner.nextLine();

        borrow.put("bookId", bookID);
        borrow.put("studentId", ID);
        borrow.put("studentName", Name);
        borrow.put("librarianId", librarianID);
        borrow.put("returnDate", returnDate);
        borrow.put("borrowDate", borrowDate);
        borrow.put("LibrarianReturnId", "None");
        borrow.put("LibrarianReturnName", "None");
        borrow.put("Returned", "None");

        for (Book b : Database.bookList) {
            if (b.bookid == Integer.parseInt(bookID)) {
                if (b.quantity <= 0) {
                    System.out.println("The book is out of stock.");
                    return;
                }
                ;
                String Update = "Update Book set Qty=Qty-1 where ID='" + b.bookid + "'";
                MySQLConnection.executeUpdate(Update);
                break;
            }
        }

        for (HashMap<String, Object> b : Database.borrowList) {
            if (String.valueOf(b.get("ReturnedDate")).equals("None")) {
                if (String.valueOf(b.get("bookId")).equals(bookID) && String.valueOf(b.get("studentId")).equals(ID)) {
                    System.out.println("You have already borrowed this book and it is not returned yet.");
                    return;
                }
            }
        }

        // Add to database
        Database.TmpBorrow.add(borrow);
        String insertQuery = String.format(
                "INSERT INTO BorrowList VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')",
                bookID, bookName, ID, Name, librarianID, librarianName, borrowDate, returnDate, "None", "None", "None");
        MySQLConnection.executeUpdate(insertQuery);
    }
}
