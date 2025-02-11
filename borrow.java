public class Borrow {
    int bookID;
    int studentID;
    String studentName;
    int librarainID;
    String librarainName;
    String bookName;
    String borrowDate;
    String returnDate;
    double payForBorrow;
    public Borrow(int bookID, int studentID, int librarainID, String borrowDate, String returnDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.librarainID = librarainID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                this.bookName = b.bookname;
            }
        }
        for(Student s : Database.studentList){
            if(studentID == s.stuID){
                this.studentName = s.stuName;
            }
        }
        for(Librarain l : Database.librarainList){
            if(librarainID == l.id){
                this.librarainName = l.name;
            }
        }
        for(Book b : Database.bookList) {
            if(b.bookid == bookID) {
                b.quantity--;
            }
        }

        for(Book b : Database.bookList){
            if(bookID == b.bookid){
                this.payForBorrow = b.price * 0.1 ;
            }
        }
    }
}
