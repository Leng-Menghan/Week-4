public class Student extends User {
//Register
    public Student(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
    }
    
//login
    public Student(String Email, String password) {
        super(Email, password); 
    }

    int bookID;
    String bookName;
    int studentID;
    String studentName;
    int librarianID;
    String librarianName;
    String borrowDate;
    String returnDate;
    double payForBorrow;
    //For fill information borrow or return
    public void Borrow(int bookID, int studentID, int librarianID, String borrowDate, String returnDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.librarianID = librarianID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                this.bookName = b.bookname;
                break;
            }
        }
        for(Student s : Database.studentList){
            if(studentID == s.ID){
                this.studentName = s.Name;
                break;
            }
        }
        for(Librarian l : Database.librarianList){
            if(librarianID == l.ID){
                this.librarianName = l.Name;
                break;
            }
        }
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                this.payForBorrow = b.price * 0.1 ;
                break;
            }
        }
        for(Book b : Database.bookList) {
            if(b.bookid == bookID) {
                b.quantity--;
                break;
            }
        }
    }
    
    String returnedDate;

    //For fill information borrow or return
    public void Returned(int bookID, int studentID, int librarianID, String returnedDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.librarianID = librarianID;
        this.returnedDate = returnedDate;
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                this.bookName = b.bookname;
                break;
            }
        }
        for(Student s : Database.studentList){
            if(studentID == s.ID){
                this.studentName = s.Name;
                break;
            }
        }
        for(Librarian l : Database.librarianList){
            if(librarianID == l.ID){
                this.librarianName = l.Name;
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
}
