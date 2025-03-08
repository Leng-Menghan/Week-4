package Library;
import java.util.HashMap;
import java.util.Scanner;

import Exception.InputException;
import Exception.NumberOnlyException;

public class Student extends User {
    Scanner scanner = new Scanner(System.in);
    // Constructor
    public Student(String ID, String Name, String Address, String PhoneNumber, String Email, String password) {
        super(ID, Name, Address, PhoneNumber, Email, password);
    }

    public Student() {};

    //Borrow
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

                int qty = Integer.parseInt(bookID);
                InputException test2 = new InputException(qty);
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String bookName = "";
        for (Book b : Database.bookList) {
            if (Integer.parseInt(bookID) == b.bookid) {
                borrow.put("bookName", b.bookname);
                bookName = b.bookname;
                break;
            }
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
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        String librarianName = "";
        for (User l : Database.UserList) {
            if (librarianID.equals(l.ID)) {
                borrow.put("librarianName", l.Name);
                librarianName = l.Name;
                break;
            }
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
                if(b.quantity <= 0){
                    System.out.println("The book is out of stock.");
                    return;
                };
                String Update = "Update Book set Qty=Qty-1 where ISBN='" + b.isbn + "'";
                MySQLConnection.executeUpdate(Update);
                break;
            }
        }

        for (HashMap<String, Object> b : Database.borrowList) {
            if(String.valueOf(b.get("ReturnedDate")).equals("None")){
                if (String.valueOf(b.get("bookId")).equals(bookID) && String.valueOf(b.get("studentId")).equals(ID)) {
                    System.out.println("You have already borrowed this book and it is not returned yet.");
                    return;
                }
            }
        }
        
        //Add to database
        Database.TmpBorrow.add(borrow);
        String insertQuery = String.format(
        "INSERT INTO BorrowList VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')",
        bookID, bookName, ID, Name, librarianID, librarianName, borrowDate, returnDate, "None", "None", "None");
        MySQLConnection.executeUpdate(insertQuery);
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

                int qty = Integer.parseInt(bookID);
                InputException test2 = new InputException(qty);
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        String librarianID;
        while (true) {
            try {
                System.out.print("Enter librarian ID : ");
                librarianID = scanner.nextLine();
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Enter return Date : ");
        String returnedDate = scanner.nextLine();
        int IsReturned = 0;
        for (HashMap<String, Object> b : Database.borrowList) {
            if(String.valueOf(b.get("ReturnedDate")).equals("None")){
                if (String.valueOf(b.get("bookId")).equals(bookID) && String.valueOf(b.get("studentId")).equals(ID)) {
                    b.put("ReturnedDate", returnedDate);
                    b.put("LibrarianReturnId", librarianID);
                    String LirbrarianReturnName = "";
                    for (User l : Database.UserList) {
                        if (librarianID.equals(l.ID)) {
                            b.put("LibrarianReturnName", l.Name);
                            LirbrarianReturnName = l.Name;
                            break;
                        }
                    }
    
                    String Update = ("Update BorrowList set LibrarianReturnId='"+ librarianID +"', LibrarianReturnName='"+ LirbrarianReturnName +"', ReturnedDate='"+ returnedDate +"' where BookId='"+ bookID +"' and StudentId='"+ ID +"'");
                    MySQLConnection.executeUpdate(Update);
    
                    for (Book B : Database.bookList) {
                        if (B.bookid == Integer.parseInt(bookID)) {
                            String UpdateB = "Update Book set Qty=Qty+1 where ISBN='" + B.isbn + "'";
                            MySQLConnection.executeUpdate(UpdateB);
                            break;
                        }
                    }

                    IsReturned = 1;
                    break;
                } else IsReturned = 2;
            }
        }
        if(IsReturned == 0){
            System.out.println("You have already returned this book !!");
        }else if(IsReturned == 1){
            System.out.println("Returned Success");
        }else if(IsReturned == 2){
            System.out.println("You haven't returned this Book!!");
        }
    }

    // Invoice
    public void DisplayInvoice() {
        if(Database.TmpBorrow.size() == 0){
            return;
        }
        double payment = 0;
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#              Invoice of borrowing               #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("\nStudent ID   : " + Database.TmpBorrow.get(0).get("studentId"));
        System.out.println("Name         : " + Database.TmpBorrow.get(0).get("studentName"));
        System.out.println("Librarian ID : " + Database.TmpBorrow.get(0).get("librarianId"));
        System.out.println("Name         : " + Database.TmpBorrow.get(0).get("librarianName"));
        System.out.println("Borrow Date  : " + Database.TmpBorrow.get(0).get("borrowDate") + " -> "
                + Database.TmpBorrow.get(0).get("returnDate"));
        System.out.println("---------------------------------------------------");
        System.out.println("Borrowed Books : ");
        int count = 0;
        for (HashMap<String, Object> b : Database.TmpBorrow) {
            count++;
            System.out.println("(ID: " + b.get("bookId") + ") - " + b.get("bookName"));
            payment += Double.parseDouble(b.get("payForBorrow").toString());
        }
        System.out.println("---------------------------------------------------");
        System.out.println("             Total payment : " + payment + " $");
        System.out.println("                   books : " + count);
        System.out.println("---------------------------------------------------\n");
        Database.TmpBorrow.clear();
    }

    //Empty Method Because of Librarian's method
    public void searchStudentByID() {};

    public void displayStudent() {};

    public void displayReturn() {};

    public void displayBorrow() {};

    public void addBookQuantityByISBN() {};

    public void deleteBook() {};

    public void addBook() {};

}
