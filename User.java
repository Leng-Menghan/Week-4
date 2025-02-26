public abstract class User implements UserAction{
    protected String ID;
    protected String Name;
    protected String Address;
    protected String PhoneNumber;
    protected String Email;
    private String Password;

    // Register
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
    public boolean login(String email, String password) {
        for(User u : Database.UserList) {
            if (u.Email.equals(email) && u.Password.equals(password)) {
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
    public void searchBookByName(String bookName) {
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
    public void searchBookByAuthor(String authorName) {
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
    public void searchBookByCategory(String category) {
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
    public void searchBookByISBN(int ISBN) {
        for(Book b : Database.bookList) {
            if(b.isbn == ISBN) {
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
            System.out.printf(format, b.bookid, b.isbn, b.bookname, b.author, b.category, b.price + " $", b.quantity, b.publisher);
            System.out.println("+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
            count++;
        }
        System.out.println("| Total Books : " + count +"   |\n");
    }

    //abstract method
        //For student
    public abstract void Borrow(int bookID, String studentID, String librarianID, String borrowDate, String returnDate);
    public abstract void Returned(int bookID, String studentID, String librarianID, String returnedDate);
    public abstract void DisplayInvoice();
        //For librarian
    public abstract void searchStudentByID(String studentID);
    public abstract void displayStudent();
    public abstract void displayBorrow();
    public abstract void displayReturn();
    public abstract void addBook();
    public abstract void deleteBook();
    public abstract void addBookQuantityByISBN();
}
