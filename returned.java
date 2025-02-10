public class returned {
    int bookID;
    String bookName; 
    int studentID;
    String studentName;
    int librarainID;
    String librarainName;
    String returnedDate;
    public returned(int bookID, String bookName, int studentID, String studentName, int librarainID, String librarainName, String returnedDate) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.studentID = studentID;
        this.studentName = studentName;
        this.librarainID = librarainID;
        this.librarainName = librarainName;
        this.returnedDate = returnedDate;
        for(book b : Database.bookList) {
            if(b.bookid == bookID) {
                b.quantity++;
            }
        }
    }
}
