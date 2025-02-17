public class Main {
    public static void main(String[] args) {
//Add Book  
    Book book1 = new Book(123456, "Fiction", "The Great Gatsby", "F. Scott Fitzgerald", 5, 5, "Scribner");
    Book book2 = new Book(789012, "Non-Fiction", "To Kill a Mockingbird", "F. Scott Fitzgerald", 15, 3, "Penguin");
    Book book3 = new Book(789012, "Non-Fiction", "To Kill", "F. Scott Fitzgerald", 13, 3, "Penguin");
    Database.bookList.add(book1);
    Database.bookList.add(book2);
    Database.bookList.add(book3);
//Add student
    Student student1 = new Student("John Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Student student2 = new Student("Jane Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Database.studentList.add(student1);
    Database.studentList.add(student2);
//Add Librarain
    Librarian librarian1 = new Librarian("smith", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Database.librarianList.add(librarian1);

//Add Admin
    Admin admin1 = new Admin();
    //admin1.displayLibrarain();
    //admin1.searchBookByName("The Great Gatsby");
// //Add Borrow
    student1.Borrow(1,1,3,"12-02-2024","26-02-2024" );
    student2.Borrow(2,2,3,"12-02-2024","26-02-2024");
    student1.DisplayInvoice();
    // librarian1.displayBorrow();
    // librarian1.displayReturn();
    student1.Returned(1, 1, 3, "22-02-2024");
    // librarian1.displayBorrow();
    // librarian1.displayReturn();
    // student1.displayBook();
    //admin1.searchLibrarianByID(3);
    //admin1.searchStudentByID(2);
    //admin1.displayBook();
    admin1.displayStudent();
// //Add return
    //librarian1.displayStudent();
    //Database.returnedList.add(returned2);
    
    //Search
    //Display.displayBook();
    // Display.displayLibrarain();
    // Display.displayStudent();
    //Display.displayBorrow();
    // Display.displayReturn();

    //student1.searchBookByName("To Kill a Mockingbird");
    //student2.searchBookByName("To Kill a Mockingbird");
    //search.searchBookByAuthor("F. Scott Fitzgerald");
    //search.searchBookByCategory("Fiction");
    //search.searchBookByISBN(123456);
    //search.searchStudentByID(1);
    //search.searchLibrarianByID(1);
    }
}
