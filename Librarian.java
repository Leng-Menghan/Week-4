import java.util.HashMap;

public class Librarian extends User {
    static int total = 0;
    public Librarian(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
        this.ID = "L" + ++total;
    }

    public Librarian(String Email, String password) {
        super(Email, password); 
    }

    //default constructor
    public Librarian() {};

    // Search student by ID
    public void searchStudentByID(String studentID) {
        System.out.println("Result of student with ID : " + studentID);
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
    
    //Empty method from Parent(User)'s abstract method
    public void Borrow(int bookID, String studentID, String librarianID, String borrowDate, String returnDate){};
    public void Returned(int bookID, String studentID, String librarianID, String returnedDate){};
    public void DisplayInvoice(){};
}
