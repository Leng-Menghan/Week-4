import java.util.HashMap;
import java.util.Scanner;

public class Librarian extends User{
    Scanner scanner = new Scanner(System.in);
    static int total = 0;

    // constructor for register
    public Librarian(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
        this.ID = "L" + ++total;
    }

    //default constructor
    public Librarian() {};

    //Register
    public void register() {
        System.out.println("Please Register as Librarian");
        System.out.print("Enter name : ");
        String name = scanner.nextLine();
        System.out.print("Enter address : ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number : ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email : ");
        String email = scanner.nextLine();
        System.out.print("Enter password : ");
        String password2 = scanner.nextLine();
        Database.UserList.add(new Librarian(name, address, phoneNumber, email, password2));
    }
    // Search student by ID
    public void searchStudentByID() {
        System.out.println("Enter student ID : ");
        String studentID = scanner.nextLine();
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
                    if(b.get("studentId").equals(s.ID)) {
                        checked = 1;
                        if(b.get("Returned").equals("None")) {
                            System.out.printf(format, b.get("bookId"), b.get("bookName"), b.get("librarianId"), b.get("librarianName"),b.get("borrowDate") + " -> " + b.get("returnDate"), "Not returned");
                        } else {
                            System.out.printf(format, b.get("bookId"), b.get("bookName"), b.get("librarianId"), b.get("librarianName"),b.get("borrowDate") + " -> " + b.get("returnDate"), b.get("Returned"));
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
            if(b.get("Returned").equals("None")) {
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
            if(!b.get("Returned").equals("None")) {
            System.out.printf(format, b.get("studentId"), b.get("studentName"), b.get("bookId"), b.get("bookName"), b.get("LibrarianReturnId"), b.get("LibrarianReturnName"), b.get("Returned"));
            count++;
            check = 1;
            }
        }
        if(check == 0){
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
        System.out.println("+--------+--------------------+-------+--------------------------------+--------+--------------------+---------------------------+");
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
                System.out.printf(format, s.ID, s.Name, s.Address, s.PhoneNumber, s.Email, s.getPassword());
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

        System.out.print("ISBN          : ");
        int ISBN = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Category      : ");
        String category = scanner.nextLine();

        System.out.print("Book Name     : ");
        String bookName = scanner.nextLine();

        System.out.print("Author        : ");
        String author = scanner.nextLine();

        System.out.print("Price of Book : $ ");
        double price = scanner.nextDouble();

        System.out.print("Quantity      : ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        try {
            ExceptionCase.isValidBookQty(quantity);
        } catch (ExceptionCase e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Publisher     : ");
        String publisher = scanner.nextLine();

        Database.bookList.add(new Book(ISBN, category, bookName, author, price, quantity, publisher));
    };

    //Delete book
    public void deleteBook(){
        System.out.println("Enter book ISBN: ");
        int ISBN = scanner.nextInt();
        for(Book b : Database.bookList){
            if(b.isbn == ISBN) {
                Database.bookList.remove(b);
                break;
            }
        }
    };

    public void addBookQuantityByISBN(){
        System.out.print("Enter book ISBN: ");
        int ISBN = scanner.nextInt();
        for(Book b : Database.bookList){
            if(b.isbn == ISBN) {
                System.out.println("How many books do you want to add?");
                System.out.print("Enter quantity: ");
                int newQuantity = scanner.nextInt();
                b.quantity += newQuantity;
                break;
            }
        }
    };
    
    //Empty method from Parent(User)'s abstract method
    public void Borrow(){};
    public void Returned(){};
    public void DisplayInvoice(){};
}
