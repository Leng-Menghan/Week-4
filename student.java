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
        borrowList.put("bookId", bookID);
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                borrowList.put("bookName", b.bookname);
                break;
            }
        }
        borrowList.put("studentId", studentID);
        for(Student s : Database.studentList){
            if(studentID == s.ID){
                borrowList.put("studentName", s.Name);
                break;
            }
        }
        borrowList.put("librarianId", librarianID);
        for(Librarian l : Database.librarianList){
            if(librarianID == l.ID){
                borrowList.put("librarianName", l.Name);
                break;
            }
        }
        borrowList.put(returnDate, borrowList);
        borrowList.put("borrowDate", borrowDate);
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                borrowList.put("payForBorrow", b.price * 0.1 );
                break;
            }
        }
        for(Book b : Database.bookList) {
            if(b.bookid == bookID) {
                b.quantity--;
                break;
            }
        }
    Database.TmpBorrow.add(borrowList);
    }
    
    //For fill information borrow or return
    public void Returned(int bookID, int studentID, int librarianID, String returnedDate) {
        HashMap<String, Object> returnedList = new HashMap<>();
        returnedList.put("bookId", bookID);
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                returnedList.put("bookName", b.bookname);
                break;
            }
        }
        returnedList.put("studentId", studentID);
        for(Student s : Database.studentList){
            if(studentID == s.ID){
                returnedList.put("studentName", s.Name);
                break;
            }
        }
        returnedList.put("librarianId", librarianID);
        for(Librarian l : Database.librarianList){
            if(librarianID == l.ID){
                returnedList.put("librarianName", l.Name);
                break;
            }
        }
        for(Book b : Database.bookList) {
            if(b.bookid == bookID) {
                b.quantity++;
                break;
            }
        }
        returnedList.put("returnedDate", returnedDate);
        Database.returnedList.add(returnedList);
    }
}
