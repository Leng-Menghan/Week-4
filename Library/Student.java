package Library;

import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Exception.NumberOnlyException;
import java.awt.*;

public class Student extends User {
    
    public void StudentFeatures() {
        JFrame frame = GUI.createFrame("Student Feature", 500, 450);

        GUI.createTitle(frame, 0, 10, 500, "Welcome to Student Features");
        
        JPanel panelButton = GUI.createInputPanel(frame, 0, 60, 500, 650);

        JButton button1 = GUI.createButton("Display Book", 150, 0, 200, 40, panelButton);
        JButton button2 = GUI.createButton("Borrow Book", 150, 50, 200, 40, panelButton);
        JButton button3 = GUI.createButton("Return Book", 150, 100, 200, 40, panelButton);
        JButton button4 = GUI.createButton("Log out", 150, 150, 200, 40, panelButton);
        JButton button5 = GUI.createButton("Change Password", 150, 200, 200, 40, panelButton);
        JButton button6 = GUI.createButton("Change Name", 150, 250, 200, 40, panelButton);
        JButton button7 = GUI.createButton("Check Information", 150, 300, 200, 40, panelButton);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInformation();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }});

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeName();
            }});

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
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
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
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(bookid.getText().trim(), "^[0-9]+$","Number positive interger only");
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
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

                //Check for exist
                int foundBook = 0;
                for (Book b : Database.bookList) {
                    if (String.valueOf(b.bookid).equals(bookid.getText())) {
                        bookName = b.bookname;
                        payment = String.valueOf(b.price * 0.1);
                        foundBook = 1;
                        break;
                    }
                }
                if (foundBook == 0) {
                    JOptionPane.showMessageDialog(null, "Book not found!");
                    return;
                }

                int foundLibrarian = 0;
                for (User u : Database.UserList) {
                    if (String.valueOf(u.ID).equals(ID)) {
                        studentName = u.Name;
                    }
                    if(String.valueOf(u.ID).equals(librarianid.getText())) {
                        librarianName = u.Name;
                        foundLibrarian = 1;
                    }
                }
                if(foundLibrarian == 0 || foundLibrarian ==0) {
                    JOptionPane.showMessageDialog(null, "Librarian not found!");
                    return;
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

        JButton ReturnButton = GUI.createButton("Return", 200, 123, 100, 30, panelInput); 

        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(bookid.getText().trim(), "^[1-9]+$","Number positive interger only");
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                Database.GetDataFromUser();
                String librarianReturnName = "";
                int foundLibrarian = 0;
                for (User user : Database.UserList) {
                    if(user.ID.equals(librarianid.getText())) {
                        librarianReturnName = user.Name;
                        foundLibrarian = 1;
                        break;
                    }
                }
                if(foundLibrarian == 0) {
                    JOptionPane.showMessageDialog(null, "Librarian not found!");
                    return;
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
                            JOptionPane.showMessageDialog(null, "Returned Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }
                if (IsReturned == 0) {
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
                try {
                    NumberOnlyException exception1 = new NumberOnlyException(Search.getText().trim(), "^[1-9]+$","Number positive interger only");
                    searchBook(Search.getText());
                }catch (NumberOnlyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
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
