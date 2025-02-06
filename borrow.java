public class borrow {
    int bookID;
    int studentID;
    String bookName;
    String borrowDate;
    String returnDate;

    public borrow(int bookID, int studentID, String bookName, String borrowDate, String returnDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
