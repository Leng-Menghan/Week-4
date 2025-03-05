package Library;
import java.util.Scanner;

import Exception.CharacterOnlyException;
import Exception.InputException;
public abstract class User implements UserAction{
    protected String ID;
    protected String Name;
    protected String Address;
    protected String PhoneNumber;
    protected String Email;
    private String Password;
    Scanner scanner = new Scanner(System.in);
    //Constructor
    public User(String ID, String Name, String Address, String PhoneNumber, String Email, String password) {
            this.Name = Name;
            this.Address = Address;
            this.PhoneNumber = PhoneNumber;        
            this.Email = Email;
            this.Password = password;
            this.ID = ID;
    }

    //default constructor
    public User() {};
    
    // get password
    public String getPassword() {
        return Password;
    }

    //Search Book by name
    public void searchBookByName() {
        String bookName;
        while(true){
            try{
                System.out.print("Enter book name: ");
                bookName = scanner.nextLine();
                InputException test = new InputException(bookName, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        for(Book b : Database.bookList) {
            if(b.bookname.toLowerCase().equals(bookName.toLowerCase())) {
                System.out.println("Book found");
                System.out.println("----------------------------------------------------");
                System.out.println("Book ID   : " + b.bookid);
                System.out.println("ISBN      : " + b.isbn);
                System.out.println("Book name : " + b.bookname);
                System.out.println("Author    : " + b.author);
                System.out.println("Category  : " + b.category);
                System.out.println("Price     : " + b.price + " $");
                System.out.println("Quantity  : " + b.quantity);
                System.out.println("Publisher : " + b.publisher);
                System.out.println("----------------------------------------------------\n");
                return;
            }
        }
        System.out.println("Book not found");
    }

    public void searchBook() {
        Database.GetDataFromBook();
        System.out.print("Enter book Name or ISBN or Author or Category: ");
        String searchBook = scanner.nextLine();
        int found = 0;
        System.out.println("#---------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println("|                                                                                                                                 |");
        System.out.println("#                                           Book list in Library Management System                                                #");
        System.out.println("|                                                                                                                                 |");
        System.out.println("#---------------------------------------------------------------------------------------------------------------------------------#");
        String format = "| %-3s | %-6s | %-27s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
        System.out.println("+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
        System.out.printf(format, "ID", "ISBN", "Name", "Author", "Category", "Price", "Quantity", "Publisher");
        System.out.println("+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
        for(Book b : Database.bookList) {
            if(b.bookname.toLowerCase().equals(searchBook.toLowerCase()) || b.isbn.toLowerCase().equals(searchBook.toLowerCase())|| b.author.toLowerCase().equals(searchBook.toLowerCase()) || b.category.toLowerCase().equals(searchBook.toLowerCase())) {
                System.out.println(b);
                found = 1;
            }
        }
        if(found == 0) {
            System.out.println("Book not found");
        }
    }
    
    //Display Book
    public void displayBook(){
        Database.GetDataFromBook();
        System.out.println("#---------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println("|                                                                                                                                 |");
        System.out.println("#                                           Book list in Library Management System                                                #");
        System.out.println("|                                                                                                                                 |");
        System.out.println("#---------------------------------------------------------------------------------------------------------------------------------#");
        String format = "| %-3s | %-6s | %-27s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
        System.out.println("+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
        System.out.printf(format, "ID", "ISBN", "Name", "Author", "Category", "Price", "Quantity", "Publisher");
        System.out.println("+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
        int count = 0;
        for(Book b : Database.bookList){
            System.out.println(b);
            count++;
        }
        System.out.println("| Total Books : " + count +"   |\n");
    }

    @Override
    public String toString() {
        String format = "| %-5s | %-20s | %-20s | %-15s | %-20s | %-15s |";
        String result = String.format(format, ID, Name, Address, PhoneNumber, Email, Password);
        return result;
    }

    //abstract method
    public abstract void register();
    public abstract boolean login();
        //For student
    public abstract void Borrow();
    public abstract void Returned();
    public abstract void DisplayInvoice();
        //For librarian
    public abstract void searchStudentByID();
    public abstract void displayStudent();
    public abstract void displayBorrow();
    public abstract void displayReturn();
    public abstract void addBook();
    public abstract void deleteBook();
    public abstract void addBookQuantityByISBN();
}
