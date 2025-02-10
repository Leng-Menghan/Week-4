import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        // library name1 = new library("ABC Library", "123 Main St.");
        // System.out.println(name1);
        // System.out.println("***********************************************");
        // System.out.println("*  Welcome to " + name1.name + " Management System   *");
        // System.out.println("***********************************************");

//Add Book  
    book book1 = new book(123456, "Fiction", "The Great Gatsby", "F. Scott Fitzgerald", 10, 5, "Scribner");
    book book2 = new book(789012, "Non-Fiction", "To Kill a Mockingbird", "F. Scott Fitzgerald", 15, 3, "Penguin");
    book book3 = new book(789012, "Non-Fiction", "To Kill", "F. Scott Fitzgerald", 15, 3, "Penguin");
    Database.bookList.add(book1);
    Database.bookList.add(book2);
    Database.bookList.add(book3);

//Add student
    student student1 = new student("John Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    student student2 = new student("Jane Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Database.studentList.add(student1);
    Database.studentList.add(student2);

//Add Librarain
    Librarain librarain1 = new Librarain("smith", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Database.librarainList.add(librarain1);

//Add Borrow
    borrow borrow1 = new borrow(1, 1,  "john Doe",1,"smith","The Great Gatsby", "2023-01-01", "2023-01-15");
    borrow borrow2 = new borrow(2, 1, "john Doe",1,"smith","To Kill a Mockingbird", "2023-01-01", "2023-01-15");
    Database.borrowList.add(borrow1);
    Database.borrowList.add(borrow2);
    
//Add Return
    //returned returned1 = new returned(1, "The Great Gatsby", 1, "john Doe", 1, "smith", "2023-01-18");
    //Database.returnedList.add(returned1);
   
    
    search search1 = new search();
    search1.searchBookByName("The Great Gatsby");
    // search1.searchBookByAuthor("F. Scott Fitzgerald");
    // search1.searchBookByISBN(789012);
    // search1.searchBookByCategory("Non-Fiction");
    //search1.searchStudentByID(studentList,1, borrowList,returnedList);
    //search1.searchLibrarainByID(1);


// Display Book : ID and Qty after borrow and return
    for(book b : Database.bookList) {
        System.out.println(b.bookid + " : " + b.quantity);
    }
    }
}
