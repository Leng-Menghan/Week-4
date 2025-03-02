import java.util.Scanner;

public abstract class User implements UserAction{
    protected String ID;
    protected String Name;
    protected String Address;
    protected String PhoneNumber;
    protected String Email;
    private String Password;
    Scanner scanner = new Scanner(System.in);
    //Constructor
    public User(String Name, String Address, String PhoneNumber, String Email, String password) {
            this.Name = Name;
            this.Address = Address;
            this.PhoneNumber = PhoneNumber;        
            this.Email = Email;
            this.Password = password;
    }

    //default constructor
    public User() {};
    
    // login
    public boolean login() {
        String email;
        while(true){
            try{
                System.out.print("Enter email : ");
                email = scanner.nextLine();
                InputException test = new InputException(email, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        String password;
        while(true){
            try{
                System.out.print("Enter password : ");
                password = scanner.nextLine();
                InputException test = new InputException(password, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        for(User u : Database.UserList) {
            if (u.Email.equals(Email) && u.Password.equals(Password)) {
                System.out.println("User logged in");
                return true;
            }
        }
        return false;
    }

    // get password
    public String getPassword() {
        return Password;
    }

    // change password
    public void changePassword(String newPassword, String oldPassword) {
        if (Password.equals(oldPassword)) {
            Password = newPassword;
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Invalid Password");
        }
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

    //Search Book by Author
    public void searchBookByAuthor() {
        String authorName;
        while(true){
            try{
                System.out.println("Enter author name: ");
                authorName = scanner.nextLine();
                CharacterOnlyException test = new CharacterOnlyException(authorName, "^[a-zA-Z ]+$");
                break;
            } catch (CharacterOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("----------------------------------------------------");
        int checked = 0;
        for(Book b : Database.bookList) {
            if(b.author.toLowerCase().equals(authorName.toLowerCase())) {
                System.out.println("(ID : " + b.bookid +") : " + b.bookname + " quantity: " + b.quantity);
                checked = 1;
            }
        }
        if(checked == 0) {
            System.out.println("Book not found");
        }
        System.out.println("----------------------------------------------------\n");
    }

    // Search book by category
    public void searchBookByCategory() {
        String category;
        while(true){
            try{
                System.out.print("Enter book category: ");
                category = scanner.nextLine();
                InputException test = new InputException(category, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        int checked = 0;
        for(Book b : Database.bookList) {
            if(b.category.toLowerCase().equals(category.toLowerCase())) {
                System.out.println("(ID : " + b.bookid +") : " + b.bookname + " quantity: " + b.quantity);
                checked = 1;
            }
        }
        if(checked == 0) {
            System.out.println("Book not found");
        }
    }

    // Search book by ISBN
    public void searchBookByISBN() {
        String ISBN;
        while(true){
            try{
                System.out.print("Enter book ISBN: ");
                ISBN = scanner.nextLine();
                InputException test = new InputException(ISBN, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        for(Book b : Database.bookList) {
            if(b.isbn.equals(ISBN)) {
                System.out.println("ISBN      : " + b.isbn);
                System.out.println("Book name : " + b.bookname);
                System.out.println("Author    : " + b.author);
                System.out.println("Category  : " + b.category);
                System.out.println("Price     : " + b.price);
                System.out.println("Quantity  : " + b.quantity);
                System.out.println("Publisher : " + b.publisher);
                return;
            }
        }
        System.out.println("Book not found");
    }

    //Display Book
    public void displayBook(){
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
            System.out.println(b.toString());
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
