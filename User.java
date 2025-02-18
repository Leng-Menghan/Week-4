public class User implements UserAction{
    String Name;
    String Address;
    String PhoneNumber;
    String Email;
    private String password;

    // Register
    public User(String Name, String Address, String PhoneNumber, String Email, String password) {
            this.Name = Name;
            this.Address = Address;
            this.PhoneNumber = PhoneNumber;        
            this.Email = Email;
            this.password = password;
        }

    // login
    public User(String Email, String password) {
            this.Email = Email;
            this.password = password;
        }

    //default constructor
    public User() {};
    
    // check password
    public String checkPassword(String password) {
        if (this.password.equals(password)) {
            return password;
        } else {
            return "Invalid Password";
        }
    }

    // get password
    public String getPassword() {
        return password;
    }

    // change password
    public void setNewPassword(String newPassword, String oldPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Invalid Password");
        }
    }

    public void searchBookByName(String bookName) {
        for(Book b : Database.bookList) {
            if(b.bookname.equals(bookName)) {
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

    public void searchBookByAuthor(String authorName) {
        System.out.println("Books of author : " + authorName);
        System.out.println("----------------------------------------------------");
        int checked = 0;
        for(Book b : Database.bookList) {
            if(b.author.equals(authorName)) {
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
        System.out.println("Books of category : " + category);
        int checked = 0;
        for(Book b : Database.bookList) {
            if(b.category.equals(category)) {
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
            System.out.printf(format, b.bookid, b.isbn, b.bookname, b.author, b.category, b.price, b.quantity, b.publisher);
            System.out.println("+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
            count++;
        }
        System.out.println("| Total Books : " + count +"   |\n");
    }
}
