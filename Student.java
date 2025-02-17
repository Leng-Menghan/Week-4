import java.util.HashMap;

public class Student extends User {
//Register
    public Student(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
    }
    
//login
    public Student(String Email, String password) {
        super(Email, password); 
    }

    //For fill information borrow or return
    public void Borrow(int bookID, int studentID, int librarianID, String borrowDate, String returnDate) {
        HashMap<String, Object> borrowList = new HashMap<>();
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                borrowList.put("bookName", b.bookname);
                break;
            }
        }
        for(Student s : Database.studentList){
            if(studentID == s.ID){
                borrowList.put("studentName", s.Name);
                break;
            }
        }
        for(Librarian l : Database.librarianList){
            if(librarianID == l.ID){
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
    
    //For fill information borrow or return
    public void Returned(int bookID, int studentID, int librarianID, String returnedDate) {
        for (HashMap<String, Object> b : Database.borrowList) {
            if (b.get("bookId").equals(bookID) && b.get("studentId").equals(studentID)) {
                b.put("Returned", returnedDate);
                b.put("LibrarianReturnId", librarianID);
                for(Librarian l : Database.librarianList){
                    if(librarianID == l.ID){
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
}
