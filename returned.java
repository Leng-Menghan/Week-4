public class Returned extends StudentAction{
    // int bookID;
    // String bookName;
    // int studentID;
    // String studentName;
    // int librarianID;
    // String librarianName;
    String returnedDate;

    public Returned(int bookID, int studentID, int librarianID, String returnedDate) {
        super(bookID, studentID, librarianID); //call the parent constructor
        this.returnedDate = returnedDate;
        for(Book b : Database.bookList) {
            if(b.bookid == bookID) {
                b.quantity++;
                break;
            }
        }
    }
}
