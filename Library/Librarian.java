package Library;
import java.util.HashMap;
import java.util.Scanner;

import Exception.CharacterOnlyException;
import Exception.EmailException;
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

    //Register
    public void register() {
        System.out.println("Please Register as Librarian");
        String name;
        while(true){
            try{
                System.out.print("Enter name : ");
                name = scanner.nextLine();
                CharacterOnlyException test = new CharacterOnlyException(name, "^[a-zA-Z ]+$");
                break;
            } catch (CharacterOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        String address;
        while(true){
            try{
                System.out.print("Enter address : ");
                address = scanner.nextLine();
                InputException test = new InputException(address, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String phoneNumber;
        while(true){
            try{
                System.out.print("Enter phone number : ");
                phoneNumber = scanner.nextLine();
                NumberOnlyException test = new NumberOnlyException(phoneNumber, "^[0-9 ]+$");
                break;
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        
        String email;
        while(true){
            try{
                System.out.print("Enter email : ");
                email = scanner.nextLine();
                InputException test = new InputException(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
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
        String insertQuery = String.format("INSERT INTO User (ID, Name, Address, PhoneNumber, Email, Password) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                "L", name, address, phoneNumber, email,password);
        MySQLConnection.executeUpdate(insertQuery);
    }
    
    // Login
    public boolean login() {
        String email;
        while(true){
            try{
                System.out.print("Enter email : ");
                email = scanner.nextLine();
                EmailException test = new EmailException(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                break;
            } catch (EmailException e) {
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
            if(u.ID.startsWith("L")){
                if (u.Email.equals(email) && u.getPassword().equals(password)) {
                    System.out.println("User logged in");
                    return true;
                }
            }
        }
        return false;
    }
    
    // Search student by ID
    public void searchStudentByID() {
        String studentID;
        while(true){
            try{
                System.out.print("Enter student ID     : ");
                studentID = scanner.nextLine();
                InputException test = new InputException(studentID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("___________________________________________________\n");
        for(User s : Database.UserList) {
            if(studentID.equals(s.ID)) {
                System.out.println("Name      : " + s.Name);
                System.out.println("Address   : " + s.Address);
                System.out.println("Phone     : " + s.PhoneNumber);
                System.out.println("Email     : " + s.Email);
                System.out.println("Password  : " + s.getPassword());
                System.out.println("___________________________________________________");
                System.out.println("\nRecord student activity : ");
                int checked = 0;
                String format = "| %-7s | %-30s | %-7s | %-30s | %-25s | %-14s |\n";
                System.out.println("+---------+--------------------------------+---------+--------------------------------+---------------------------+----------------+");
                System.out.printf(format, "Book ID", "Book Name", "Lib ID" , "Librarian Name", "Borrow Date", "Return Status");
                System.out.println("+---------+--------------------------------+---------+--------------------------------+---------------------------+----------------+");
                for (HashMap<String, Object> b : Database.borrowList){
                    if(String.valueOf(b.get("studentId")).equals(s.ID)) {
                        checked = 1;
                        if(String.valueOf(b.get("Returned")).equals("None")) {
                            System.out.printf(format, b.get("bookId"), b.get("bookName"), b.get("librarianId"), b.get("librarianName"),b.get("borrowDate") + " -> " + b.get("returnDate"), "Not returned");
                        } else {
                            System.out.printf(format, b.get("bookId"), b.get("bookName"), b.get("librarianId"), b.get("librarianName"),b.get("borrowDate") + " -> " + b.get("returnDate"), b.get("ReturnedDate"));
                        }
                        System.out.println("+---------+--------------------------------+---------+--------------------------------+---------------------------+----------------+");
                    }
                    
                }
                if(checked == 0) {
                    System.out.println("No activity");
                }
                return;
            }
        }
        System.out.println("                Student not found");
        System.out.println("___________________________________________________\n");
    }

    // Display borrowed list
    public void displayBorrow(){
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
                count++; 
            } 
        }
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.println("--------------- Total students : " + count +" --------------- ");
    }
    
    //Add book
    public void addBook(){
        System.out.println("#---------------------------------------#");
        System.out.println("|          Enter Book Details           |");
        System.out.println("#---------------------------------------#");
        
        String ISBN;
        while(true){
            try{
                System.out.print("ISBN          : ");
                ISBN = scanner.nextLine();
                InputException test = new InputException(ISBN, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
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
                InputException test = new InputException(category, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }

        String bookName;
        while(true){
            try{
                System.out.print("Book Name     : ");
                bookName = scanner.nextLine();
                InputException test = new InputException(bookName, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }

        String author;
        while(true){
            try{
                System.out.print("Author name : ");
                author = scanner.nextLine();
                CharacterOnlyException test = new CharacterOnlyException(author, "^[a-zA-Z ]+$");
                break;
            } catch (CharacterOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        String price;
        while(true){
            try{
                System.out.print("Price of Book : $ ");
                price = scanner.nextLine();
                NumberOnlyException test = new NumberOnlyException(price, "^-?[0-9]+(\\.[0-9]+)?$");

                double priceDouble = Double.parseDouble(price);
                InputException test2 = new InputException(priceDouble);

                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
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

                Double qty = Double.parseDouble(quantity);
                InputException test2 = new InputException(qty);
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            } catch (NumberOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        
        String publisher;
        while(true){
            try{
                System.out.print("Publisher     : ");
                publisher = scanner.nextLine();
                InputException test = new InputException(publisher, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }

        String insertQuery = String.format("INSERT INTO Book VALUES ( '%s', '%s', '%s', '%s', '%s','%s', '%s')",
        ISBN, category, bookName, author, price, quantity, publisher);
        MySQLConnection.executeUpdate(insertQuery);
    };

    //Delete book
    public void deleteBook(){
        String ISBN;
        while(true){
            try{
                System.out.print("ISBN : ");
                ISBN = scanner.nextLine();
                InputException test = new InputException(ISBN, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        for(Book b : Database.bookList){
            if(b.isbn.equals(ISBN)){ {
                Database.bookList.remove(b);
                String Delete = "DELETE FROM Book WHERE ISBN = '" + ISBN + "'";
                MySQLConnection.executeUpdate(Delete);
                break;
            }
        }
    }
    };

    //Add book qty
    public void addBookQuantityByISBN(){
        String ISBN;
        while(true){
            try{
                System.out.print("ISBN : ");
                ISBN = scanner.nextLine();
                InputException test = new InputException(ISBN, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        for(Book b : Database.bookList){
            if(b.isbn.equals(ISBN)){ {
                System.out.println("How many books do you want to add?");
                System.out.print("Enter quantity: ");
                int newQuantity = scanner.nextInt();
                b.quantity += newQuantity;
                String Update = "UPDATE Book SET Qty = '" + b.quantity + "' WHERE ISBN = '" + ISBN + "'";
                MySQLConnection.executeUpdate(Update);
                break;
            }
        }
    }
    };
    
    //Empty method because Student's Method
    public void Borrow(){};
    public void Returned(){};
    public void DisplayInvoice(){};
}
