package Library;
import java.util.HashMap;
import java.util.Scanner;
import Exception.InputException;
import Exception.NumberOnlyException;

public class Librarian extends User{
    Scanner scanner = new Scanner(System.in);

    // constructor for register
    public Librarian(String ID,String Name, String Address, String PhoneNumber, String Email, String password) {
        super(ID,Name, Address, PhoneNumber, Email, password); 
    }

    //default constructor
    public Librarian() {};

    //Update Book By ID
    public void updateBookByID(){
        Database.GetDataFromBook();
        if(Database.bookList.isEmpty()){
            System.out.println("Book list is empty.");
            return;
        }
        String bookID;
        while(true){
            try{
                System.out.print("Enter book ID     : ");
                bookID = scanner.nextLine();
                NumberOnlyException test = new NumberOnlyException(bookID, "^-?[0-9]+$");
                NumberOnlyException test2 = new NumberOnlyException(Integer.parseInt(bookID));
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        for(Book b : Database.bookList) {
            if(b.bookid == Integer.parseInt(bookID)) {
                String format = "| %-3s |  %-27s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
                System.out.println(
                    "+-----+------------------------------+----------------------+----------------------+---------+----------+-----------------+");
            System.out.printf(format, "ID",  "Name", "Author", "Category", "Price", "Quantity", "Publisher");
            System.out.println(
                    "+-----+------------------------------+----------------------+----------------------+---------+----------+-----------------+");
                System.out.println(b);
                    String bookName;
                    while(true){
                        try{
                            System.out.print("Book Name     : ");
                            bookName = scanner.nextLine();
                            InputException test = new InputException(bookName, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                            break;
                        } catch (InputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    String category;
                    while(true){
                        try{
                            System.out.print("Category      : ");
                            category = scanner.nextLine();
                            InputException test = new InputException(category, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                            break;
                        } catch (InputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    String author;
                    while(true){
                        try{
                            System.out.print("Author name   : ");
                            author = scanner.nextLine();
                            InputException test = new InputException(author, "^[a-zA-Z ]+$", "Characters only");
                            break;
                        } catch (InputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    String price;
                    while(true){
                        try{
                            System.out.print("Price of Book : $ ");
                            price = scanner.nextLine();
                            NumberOnlyException test = new NumberOnlyException(price, "^-?[0-9]+(\\.[0-9]+)?$");
                            NumberOnlyException test2 = new NumberOnlyException(Double.parseDouble(price));
                            break;
                        } catch (NumberOnlyException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    String quantity;
                    while(true){
                        try{
                            System.out.print("Quantity      : ");
                            quantity = scanner.nextLine();
                            NumberOnlyException test1 = new NumberOnlyException(quantity, "^-?[0-9]+(\\.[0-9]+)?$");
                            NumberOnlyException test2 = new NumberOnlyException(Double.parseDouble(quantity));
                            break;
                        } catch (NumberOnlyException e) {
                            System.out.println(e.getMessage());
                        } 
                    }

                    
                    String publisher;
                    while(true){
                        try{
                            System.out.print("Publisher     : ");
                            publisher = scanner.nextLine();
                            InputException test = new InputException(publisher, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                            break;
                        } catch (InputException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    String UpdateBook = "update Book set Name = '"+bookName+"', Category = '"+category+"', Publisher = '"+publisher+"' , Author = '"+author+"', price = "+price+", Qty = "+quantity+" where ID = "+bookID+"";
                    MySQLConnection.executeUpdate(UpdateBook);
                return;
            }
        }
        System.out.println("Book doesn't exist.");	
    }
    // Search student by ID
    public void searchStudentByID() {
        Database.GetDataFromUser();
        if(Database.UserList.isEmpty()){
            System.out.println("User list is empty.");
            return;
        }
        String studentID;
        while(true){
            try{
                System.out.print("Enter student ID     : ");
                studentID = scanner.nextLine();
                InputException test = new InputException(studentID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("___________________________________________________n");
        for(User s : Database.UserList) {
            if(studentID.equals(s.ID)) {
                System.out.println("Name      : " + s.Name);
                System.out.println("Address   : " + s.Address);
                System.out.println("Phone     : " + s.PhoneNumber);
                System.out.println("Email     : " + s.Email);
                System.out.println("Password  : " + s.getPassword());
                System.out.println("___________________________________________________");
                return;
            }
        }
        System.out.println("                Student not found");
        System.out.println("___________________________________________________\n");
    }

    // Display borrowed list
    public void displayBorrow(){
        Database.GetDataFromBorrow();
        if(Database.borrowList.isEmpty()){
            System.out.println("Borrow list is empty.");
        }
        int count = 0;
        System.out.println("#--------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println("|                                                                                                                                |");
        System.out.println("#                                      Borrowed list (Not returned yet) in Library Management System                             #");
        System.out.println("|                                                                                                                                |");
        System.out.println("#--------------------------------------------------------------------------------------------------------------------------------#");
        int check = 0;
        String format = "| %-6s | %-18s | %-5s | %-30s | %-6s | %-18s | %-25s |\n";
        System.out.println("+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        System.out.printf(format, "Stu ID", "Name", "B ID" , "Book Name","Lib ID", "Librarian Name", "Borrow Date");
        System.out.println("+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        for (HashMap<String, Object> b : Database.borrowList){
            if(b.get("ReturnedDate").equals("None")) {
                System.out.printf(format, b.get("studentId"), b.get("studentName"), b.get("bookId"), b.get("bookName"), b.get("librarianId"), b.get("librarianName"), b.get("borrowDate") + " -> " + b.get("returnDate"));
                count++;
                check = 1;
            }
        }
        System.out.println("+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        if(check == 0){
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
        System.out.println("--------------- Total borrowed : "+ count +" --------------- ");
    }

    // Display returned list
    public void displayReturn(){
        Database.GetDataFromBorrow();
        if(Database.borrowList.isEmpty()) {
            System.out.println("No data in borrow list");
            return;
        }
        int count = 0;
        System.out.println("#--------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println("|                                                                                                                                |");
        System.out.println("#                                          Returned list in Library Management System                                            #");
        System.out.println("|                                                                                                                                |");
        System.out.println("#--------------------------------------------------------------------------------------------------------------------------------#");
        int check = 0;
        String format = "| %-6s | %-18s | %-5s | %-30s | %-6s | %-18s | %-25s |\n";
        System.out.println("+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        System.out.printf(format, "Stu ID", "Name", "B ID" , "Book Name","Lib ID", "Librarian Name", "Returned Date");
        System.out.println("+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
        for (HashMap<String, Object> b : Database.borrowList){
            if(!b.get("ReturnedDate").equals("None")) {
            System.out.printf(format, b.get("studentId"), b.get("studentName"), b.get("bookId"), b.get("bookName"), b.get("librarianReturnId"), b.get("librarianReturnName"), b.get("ReturnedDate"));
            count++;
            check = 1;
            System.out.println("+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
            }
        }
        if(check == 0){
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
       
        System.out.println("--------------- Total returned : "+count+" --------------- ");
    }
    
    // Display student
    public void displayStudent(){
        Database.GetDataFromUser();
        if(Database.UserList.isEmpty()){
            System.out.println("No data in student list");
            return;
        }
        System.out.println("#----------------------------------------------------------------------------------------------------------------#");
        System.out.println("|                                                                                                                |");
        System.out.println("#                                         Student list in Library Management System                              #");
        System.out.println("|                                                                                                                |");
        System.out.println("#----------------------------------------------------------------------------------------------------------------#");
        int count = 0;
        String format = "| %-5s | %-20s | %-20s | %-15s | %-20s | %-15s |\n";
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.printf(format, " ID", "Name", "Address" , "Phone Number", "Email", "Password");
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        for(User s : Database.UserList){
            if(s.ID.charAt(0)=='S'){
                System.out.println(s);
                System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
                count++; 
            } 
        }
        
        System.out.println("--------------- Total students : " + count +" --------------- ");
    }
    
    //Add book
    public void addBook(){
        Database.GetDataFromBook();
        System.out.println("#---------------------------------------#");
        System.out.println("|          Enter Book Details           |");
        System.out.println("#---------------------------------------#");

        String bookName;
        while(true){
            try{
                System.out.print("Book Name     : ");
                bookName = scanner.nextLine();
                InputException test = new InputException(bookName, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        
        //Check if book already exists
        for(Book b : Database.bookList){
            if(b.bookname.equals(bookName)){
                System.out.println("Book already exists");
                return;
            }
        }

        String category;
        while(true){
            try{
                System.out.print("Category      : ");
                category = scanner.nextLine();
                InputException test = new InputException(category, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }

        String author;
        while(true){
            try{
                System.out.print("Author name   : ");
                author = scanner.nextLine();
                InputException test = new InputException(author, "^[a-zA-Z ]+$", "Characters only");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }

        String price;
        while(true){
            try{
                System.out.print("Price of Book : $ ");
                price = scanner.nextLine();
                NumberOnlyException test = new NumberOnlyException(price, "^-?[0-9]+(\\.[0-9]+)?$");
                NumberOnlyException test2 = new NumberOnlyException(Double.parseDouble(price));
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        String quantity;
        while(true){
            try{
                System.out.print("Quantity      : ");
                quantity = scanner.nextLine();
                NumberOnlyException test1 = new NumberOnlyException(quantity, "^-?[0-9]+(\\.[0-9]+)?$");
                NumberOnlyException test2 = new NumberOnlyException(Double.parseDouble(quantity));
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            } 
        }

        
        String publisher;
        while(true){
            try{
                System.out.print("Publisher     : ");
                publisher = scanner.nextLine();
                InputException test = new InputException(publisher, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }


        String insertQuery = String.format("INSERT INTO Book (Category, Name, Author, Price, Qty, Publisher) VALUES ( '%s', '%s', '%s', '%s','%s', '%s')",
        category, bookName, author, price, quantity, publisher);
        MySQLConnection.executeUpdate(insertQuery);
    };

    //Delete book
    public void deleteBook(){
        Database.GetDataFromBook();
        if(Database.bookList.isEmpty()){
            System.out.println("Book list is empty.");
            return;
        }
        int ID;
        while(true){
            try{
                System.out.print("ID : ");
                ID = scanner.nextInt();
                NumberOnlyException test = new NumberOnlyException(String.valueOf(ID), "^?[0-9]+$");
                NumberOnlyException test2 = new NumberOnlyException(ID);
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        int foundBook = 0;
        for(Book b : Database.bookList){
            if(b.bookid == ID){ {
                String Delete = "DELETE FROM Book WHERE ID = '" + ID + "'";
                MySQLConnection.executeUpdate(Delete);
                foundBook = 1;
                break;
            }
        }
        if(foundBook == 0){
            System.out.println("Book not found.");
        }
    }
    };

    //Add book qty
    public void addBookQuantityByID(){
        Database.GetDataFromBook();
        if(Database.bookList.isEmpty()){
            System.out.println("Book list is empty.");
            return;
        }
        int ID;
        while(true){
            try{
                System.out.print("ID : ");
                ID = scanner.nextInt();
                NumberOnlyException test = new NumberOnlyException(String.valueOf(ID), "^?[0-9]+$");
                NumberOnlyException test2 = new NumberOnlyException(ID);
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        int foundBook = 0;
        for(Book b : Database.bookList){
            if(b.bookid == ID){ {
                System.out.println("How many books do you want to add?");
                System.out.print("Enter quantity: ");
                int newQuantity = scanner.nextInt();
                int updateQty = b.quantity + newQuantity;
                String Update = "UPDATE Book SET Qty = '" + updateQty + "' WHERE ID = '" + ID + "'";
                MySQLConnection.executeUpdate(Update);
                foundBook = 1;
                break;
            }
        }
        if(foundBook == 0){
            System.out.println("Book not found.");
        }
    }
    };
    
    //Empty method because Student's Method
    public void Borrow(){};
    public void Returned(){};
    public void DisplayInvoice(){};
}
