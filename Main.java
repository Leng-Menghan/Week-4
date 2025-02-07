import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        // library name1 = new library("ABC Library", "123 Main St.");
        // System.out.println(name1);
        // System.out.println("***********************************************");
        // System.out.println("*  Welcome to " + name1.name + " Management System   *");
        // System.out.println("***********************************************");

    ArrayList<book> bookList = new ArrayList<book>();
    ArrayList<student> studentList = new ArrayList<student>();
    ArrayList<borrow> borrowList = new ArrayList<borrow>();
    ArrayList<Librarain> librarainList = new ArrayList<Librarain>();
    book book1 = new book(123456, "Fiction", "The Great Gatsby", "F. Scott Fitzgerald", 10, 5, "Scribner");
    book book2 = new book(789012, "Non-Fiction", "To Kill a Mockingbird", "F. Scott Fitzgerald", 15, 3, "Penguin");
    

    bookList.add(book1);
    bookList.add(book2);
    search search1 = new search();
    //search1.searchBookByName(bookList,"The Great Gatsb");
    //search1.searchBookByAuthor(bookList,"F. Scott Fitzgerald");
    //search1.searchBookByISBN(bookList,789012);
    student student1 = new student("John Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    student student2 = new student("Jane Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Librarain librarain1 = new Librarain("smith", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    borrow borrow1 = new borrow(123456, 1, 1,"The Great Gatsby", "2023-01-01", "2023-01-15");
    borrow borrow2 = new borrow(789012, 2, 1,"To Kill a Mockingbird", "2023-01-01", "2023-01-15");
    studentList.add(student1);
    studentList.add(student2);
    borrowList.add(borrow1);
    borrowList.add(borrow2);
    librarainList.add(librarain1);
    search1.searchStudentByID(studentList,3, borrowList);
    //search1.searchLibrarainByID(librarainList, 1, borrowList);


    }

    
}
