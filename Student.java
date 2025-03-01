import java.util.HashMap;
import java.util.Scanner;

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
        System.out.print("Enter name : ");
        String name = scanner.nextLine();
        System.out.print("Enter address : ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number : ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email : ");
        String email = scanner.nextLine();
        System.out.print("Enter password : ");
        String password = scanner.nextLine();
        Database.UserList.add(new Student(name, address, phoneNumber, email, password));
    };
    
    public void Borrow(){
        HashMap<String, Object> borrowList = new HashMap<>();
        System.out.print("Enter book ID : ");
        int bookID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student ID : ");
        String studentID = scanner.nextLine();
        System.out.print("Enter librarian ID : ");
        String librarianID = scanner.nextLine();
        System.out.print("Enter borrow Date : ");
        String borrowDate = scanner.nextLine();
        System.out.print("Enter return Date : ");
        String returnDate = scanner.nextLine();
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
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
            if(bookID == b.bookid){
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
            if(b.bookid == bookID) {
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
        System.out.print("Enter book ID : ");
        int bookID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student ID : ");
        String studentID = scanner.nextLine();
        System.out.print("Enter librarian ID : ");
        String librarianID = scanner.nextLine();
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
            if(b.bookid == bookID) {
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
