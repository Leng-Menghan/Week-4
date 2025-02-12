public class StudentAction {
    int bookID;
    String bookName;
    int studentID;
    String studentName;
    int librarianID;
    String librarianName;

    //For fill information borrow or return
    public StudentAction(int bookID, int studentID, int librarianID) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.librarianID = librarianID;
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
    }
}
