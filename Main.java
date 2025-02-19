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
    User student1 = new Student("John Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    User student2 = new Student("Jane Doe", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Database.UserList.add(student1);
    Database.UserList.add(student2);
//Add Librarain
    User librarian1 = new Librarian("smith", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    User librarian2 = new Librarian("smile", "123 Main St.", "123-456-7890", "yWV7V@example.com","1234");
    Database.UserList.add(librarian1);
    Database.UserList.add(librarian2);

//Add Admin
    Admin admin1 = new Admin();
    //admin1.displayLibrarain();
    //admin1.searchBookByName("The Great Gatsby");
// //Add Borrow
    student1.Borrow(1,"S1","L1","12-02-2024","26-02-2024" );
    student2.Borrow(2,"S2","L1","12-02-2024","26-02-2024");
    student1.DisplayInvoice();
    //admin1.searchLibrarianByID("L1");
    //admin1.searchStudentByID("S1");
    admin1.displayLibrarain();
    //admin1.displayStudent();
    }
}
