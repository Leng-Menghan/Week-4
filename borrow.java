public class Borrow extends StudentAction {
    // int bookID;
    // String bookName;
    // int studentID;
    // String studentName;
    // int librarianID;
    // String librarianName;
    String borrowDate;
    String returnDate;
    double payForBorrow;

    public Borrow(int bookID, int studentID, int librarianID, String borrowDate, String returnDate) {
        super(bookID, studentID, librarianID); // Call the parent constructor
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
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
}
