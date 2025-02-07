public class borrow {
    int bookID;
    int studentID;
    int librarainID;
    String bookName;
    String borrowDate;
    String returnDate;

    public borrow(int bookID, int studentID, int librarainID, String bookName, String borrowDate, String returnDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.librarainID = librarainID;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
