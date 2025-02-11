public class Main {

    public static void main(String[] args) {
        //library name1 = new library("ABC Library", "123 Main St.");
        //System.out.println(name1);

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
    Librarain librarain1 = new Librarain("smith", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Database.librarainList.add(librarain1);

//Add Borrow
    Borrow borrow1 = new Borrow(3, 2 ,1, "2023-01-01", "2023-01-15");
    Database.TmpBorrow.add(borrow1);
    Borrow borrow2 = new Borrow(1, 1,1, "2023-01-01", "2023-01-15");
    Database.TmpBorrow.add(borrow2);
    Display.DisplayInvoice();
    Display.displayBorrow();
    Display.displayReturn();
    //Display.displayBorrow();
    //Invoice.DisplayInvoice();
//Add Return
    // returned returned1 = new returned(3, 2, 1,  "2023-01-15");
    // Database.returnedList.add(returned1);
    // returned returned2 = new returned(1, 1, 1, "2023-01-18");
    // Database.returnedList.add(returned2);
    
    //Search.searchBookByName("The Great Gatsby");
    //Search.searchBookByAuthor("F. Scott Fitzgerald");
    //search.searchBookByISBN(789012);
    //search.searchBookByCategory("Non-Fiction");
    //search.searchStudentByID(2);
    //search.searchLibrarainByID(1);


// Display Book : ID and Qty after borrow and return
    // for(book b : Database.bookList) {
    //     System.out.println(b.bookid + " : " + b.quantity);
    // }
    //Display.displayStudent();
    //Display.displayBook();
    //Display.displayLibrarain();
    //Display.displayBorrow();
    // Display.displayReturn();
    }
}
