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

    public Student() {};

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

        // Create frame
        JFrame frame = GUI.createFrame("Add Borrow", 500, 300);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        // Create Title
        GUI.createTitle(frame, 0, 10, 500, "Borrow Action");
        
        // Create Input Panel
        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 350);

        GUI.createLabel("Book ID : ", 20, 0, panelInput);
        JTextField bookid = GUI.createTextField(160, 3, panelInput);

        GUI.createLabel("Librarian ID : ", 20, 40, panelInput);
        JTextField librarianid = GUI.createTextField(160, 43, panelInput);

        GUI.createLabel("Borrow Date : ", 20, 80, panelInput);
        JTextField borrowdate = GUI.createTextField(160, 83, panelInput);

        GUI.createLabel("Return Date : ", 20, 120, panelInput);
        JTextField returndate = GUI.createTextField(160, 123, panelInput);

        JButton BorrowButton = GUI.createButton("Borrow", 200, 163, 100, 30, panelInput); 

        BorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.GetDataFromBorrow();
                for (HashMap<String, Object> b : Database.borrowList) {
                    if (String.valueOf(b.get("ReturnedDate")).equals("None")) {
                        if (String.valueOf(b.get("bookId")).equals(bookid.getText()) && String.valueOf(b.get("studentId")).equals(ID)) {
                            JOptionPane.showMessageDialog(null, "You haven't Return This book yet.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

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
        JFrame frame = GUI.createFrame("Return Action", 500, 260);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        // Create Title
        GUI.createTitle(frame, 0, 10, 500, "Return Action");
        
        // Create Input Panel
        JPanel panelInput = GUI.createInputPanel(frame, 0, 60, 500, 250);

        GUI.createLabel("Book ID : ", 20, 0, panelInput);
        JTextField bookid = GUI.createTextField(160, 3, panelInput);

        GUI.createLabel("Librarian ID : ", 20, 40, panelInput);
        JTextField librarianid = GUI.createTextField(160, 43, panelInput);

        GUI.createLabel("Return Date : ", 20, 80, panelInput);
        JTextField returneddate = GUI.createTextField(160, 83, panelInput);

        JButton ReturnButton = GUI.createButton("Borrow", 200, 123, 100, 30, panelInput); 

        ReturnButton.addActionListener(new ActionListener() {
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
                int IsReturned = 0;
                for (HashMap<String, Object> b : Database.borrowList) {
                    if (String.valueOf(b.get("ReturnedDate")).equals("None")) {
                        if (String.valueOf(b.get("bookId")).equals(bookid.getText()) && String.valueOf(b.get("studentId")).equals(ID)) {
                            String Update = ("Update BorrowList set LibrarianReturnId='" +  librarianid.getText()
                                    + "', LibrarianReturnName='" + librarianReturnName + "', ReturnedDate='" + returneddate.getText()
                                    + "' where BookId='" + bookid.getText() + "' and StudentId='" + ID + "'");
                            MySQLConnection.executeUpdate(Update);
                            String UpdateB = "Update Book set Qty=Qty+1 where ID='" + bookid.getText() + "'";
                            MySQLConnection.executeUpdate(UpdateB);
                            IsReturned = 1;
                            break;
                        } else IsReturned = 2;
                    }
                }
                if (IsReturned == 1) {
                    JOptionPane.showMessageDialog(null, "Returned Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (IsReturned == 2) {
                    JOptionPane.showMessageDialog(null, "You haven't borrow this Book!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    };

    public void DisplayAction() {
        Database.GetDataFromBook();
        // Create frame
        JFrame frame = GUI.createFrame("Display Action", 730, 400);

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

        // Create Button Refresh
        JButton Refresh = new JButton("Refresh");
        Refresh.setFont(new Font("Arial", Font.BOLD, 15));
        Refresh.setForeground(Color.WHITE);
        Refresh.setBounds(610, 10, 100, 30);
        Refresh.setBackground(Color.RED);
        frame.add(Refresh);

        // Add action listener to Refresh button
        Refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DisplayAction();
            }
        });

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

        // Create DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Add rows directly from bookList
        for (Book book : Database.bookList) {
                Object[] row = { book.bookid, book.bookname, book.category, book.author, book.price, book.quantity, book.publisher };
                model.addRow(row);
        }

        JTable table = GUI.createTable(frame, model, 10, 145, 700, 200);

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
                frame.dispose();
                searchBook(Search.getText());
            }
        });

    }

    public void DisplayInvoice(String bookId, String bookName, String studentId, String studentName, String librarianId,
            String Librarianname, String borrowDate, String returnDate, String payment) {
        // Create frame
        JFrame frame = GUI.createFrame("Display Invoice", 500, 500);

        GUI.createTitle(frame, 0, 10, 500, "Invoice Details Borrowing");

        // Create Button Back
        JButton Back = GUI.createButtonBack(frame);
        Back.addActionListener(e -> frame.dispose());

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
    }

}
