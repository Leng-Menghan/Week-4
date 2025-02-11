public class Returned {
    int bookID;
    String bookName; 
    int studentID;
    String studentName;
    int librarainID;
    String librarainName;
    String returnedDate;
    public Returned(int bookID, int studentID, int librarainID, String returnedDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.librarainID = librarainID;
        this.returnedDate = returnedDate;
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
                b.quantity++;
            }
        }
    }
}
