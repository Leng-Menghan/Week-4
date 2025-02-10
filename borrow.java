public class borrow {
    int bookID;
    int studentID;
    String studentName;
    int librarainID;
    String librarainName;
    String bookName;
    String borrowDate;
    String returnDate;
    public borrow(int bookID, int studentID, String studentName,int librarainID,String librarainName, String bookName, String borrowDate, String returnDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.studentName = studentName;
        this.librarainID = librarainID;
        this.librarainName = librarainName;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        for(book b : Database.bookList) {
            if(b.bookid == bookID) {
                b.quantity--;
            }
        }

    }
}
