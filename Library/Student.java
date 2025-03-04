package Library;
import java.util.HashMap;
import java.util.Scanner;

import Exception.CharacterOnlyException;
import Exception.InputException;
import Exception.NumberOnlyException;
import Exception.EmailException;
public class Student extends User {
    Scanner scanner = new Scanner(System.in);
    static int total = 0;
    //Constructor
    public Student(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
        this.ID = "S" + ++total;
    }
    public Student() {};
    //Borrow

    //Register
    public void register() {
        System.out.println("Please Register as Librarian");
        String name;
        while(true){
            try{
                System.out.print("Enter name : ");
                name = scanner.nextLine();
                CharacterOnlyException test = new CharacterOnlyException(name, "^[a-zA-Z ]+$");
                break;
            } catch (CharacterOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        String address;
        while(true){
            try{
                System.out.print("Enter address : ");
                address = scanner.nextLine();
                InputException test = new InputException(address, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String phoneNumber;
        while(true){
            try{
                System.out.print("Enter phone number : ");
                phoneNumber = scanner.nextLine();
                NumberOnlyException test = new NumberOnlyException(phoneNumber, "^[0-9 ]+$");
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String email;
        while(true){
            try{
                System.out.print("Enter email : ");
                email = scanner.nextLine();
                EmailException test = new EmailException(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                break;
            } catch (EmailException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String password;
        while(true){
            try{
                System.out.print("Enter password : ");
                password = scanner.nextLine();
                InputException test = new InputException(password, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        Database.UserList.add(new Student(name, address, phoneNumber, email, password));
    }
    
    public void Borrow(){
        HashMap<String, Object> borrowList = new HashMap<>();
        String bookID;
        while(true){
            try{
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

        String studentID;
        while(true){
            try{
                System.out.print("Enter student ID : ");
                studentID = scanner.nextLine();
                InputException test = new InputException(studentID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String librarianID;
        while(true){
            try{
                System.out.print("Enter librarian ID : ");
                librarianID = scanner.nextLine();
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("Enter borrow Date : ");
        String borrowDate = scanner.nextLine();
        System.out.print("Enter return Date : ");
        String returnDate = scanner.nextLine();
        for(Book b : Database.bookList){
            if(Integer.parseInt(bookID) == b.bookid){
                borrowList.put("bookName", b.bookname);
                break;
            }
        }
        for(User s : Database.UserList){
            if(studentID.equals(s.ID)){
                borrowList.put("studentName", s.Name);
                break;
            }
        }
        for(User l : Database.UserList){
            if(librarianID.equals(l.ID)){
                borrowList.put("librarianName", l.Name);
                break;
            }
        }
        for(Book b : Database.bookList){
            if(Integer.parseInt(bookID) == b.bookid){
                borrowList.put("payForBorrow", b.price * 0.1 );
                break;
            }
        }
        borrowList.put("bookId", bookID);
        borrowList.put("studentId", studentID);
        borrowList.put("librarianId", librarianID);
        borrowList.put("returnDate", returnDate);
        borrowList.put("borrowDate", borrowDate);
        for(Book b : Database.bookList) {
            if(b.bookid == Integer.parseInt(bookID)){ 
                b.quantity--;
                break;
            }
        }
        borrowList.put("LibrarianReturnId","None");
        borrowList.put("LibrarianReturnName","None");
        borrowList.put("Returned","None");
        Database.TmpBorrow.add(borrowList);
}
    
    //Return
    public void Returned() {
        String bookID;
        while(true){
            try{
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

        String studentID;
        while(true){
            try{
                System.out.print("Enter student ID : ");
                studentID = scanner.nextLine();
                InputException test = new InputException(studentID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String librarianID;
        while(true){
            try{
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
        for (HashMap<String, Object> b : Database.borrowList) {
            if (b.get("bookId").equals(bookID) && b.get("studentId").equals(studentID)) {
                b.put("Returned", returnedDate);
                b.put("LibrarianReturnId", librarianID);
                for(User l : Database.UserList){
                    if(librarianID.equals(l.ID)){
                        b.put("LibrarianReturnName", l.Name);
                        break;
                    }
                }
                break;
            }
        }
        for(Book b : Database.bookList) {
            if(b.bookid == Integer.parseInt(bookID)){ 
                b.quantity++;
                break;
            }
        }
    }

    //Invoice
    public void DisplayInvoice(){
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
        System.out.println("Borrow Date  : " + Database.TmpBorrow.get(0).get("borrowDate") + " -> " + Database.TmpBorrow.get(0).get("returnDate"));
        System.out.println("---------------------------------------------------");
        System.out.println("Borrowed Books : ");
        int count = 0;
        for (HashMap<String, Object> b : Database.TmpBorrow) {
            count++;
            System.out.println("(ID: " + b.get("bookId") + ") - " + b.get("bookName"));
            payment+= Double.parseDouble(b.get("payForBorrow").toString());
        }
        System.out.println("---------------------------------------------------");
        System.out.println("             Total payment : " + payment +" $" );
        System.out.println("                   books : " + count);
        System.out.println("---------------------------------------------------\n");
        Database.borrowList.addAll(Database.TmpBorrow);
        Database.TmpBorrow.clear();
    }

    public void searchStudentByID() {};
    public void displayStudent() {};
    public void displayReturn() {};
    public void displayBorrow() {};
    public void addBookQuantityByISBN() {};
    public void deleteBook() {};
    public void addBook() {};
    
}
