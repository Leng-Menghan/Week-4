import java.util.HashMap;

public class Admin extends User{
    String adminEmail = "admin";
    String adminPassword = "123";

    public boolean adminlogin(String email, String password) {
        if (email.equals(adminEmail) && password.equals(adminPassword)) {
            System.out.println( "Admin logged in");
            return true;
        }
        else {
            System.out.println("Invalid email or password");
            return false;
            
        }
    }

    // Search student by ID
    public void searchStudentByID(int studentID) {
        System.out.println("Result of student with ID : " + studentID);
        System.out.println("___________________________________________________\n");
        for(Student s : Database.studentList) {
            if(s.ID == studentID) {
                System.out.println("Name      : " + s.Name);
                System.out.println("Address   : " + s.Address);
                System.out.println("Phone     : " + s.PhoneNumber);
                System.out.println("Email     : " + s.Email);
                System.out.println("Password  : " + s.getPassword());
                System.out.println("___________________________________________________");
                System.out.println("\nRecord student activity : ");
                int checked = 0;
                for (HashMap<String, Object> b : Database.borrowList){
                    if((int)b.get("studentId") == s.ID) {
                        System.out.println("Book ID : " + b.get("bookId") + " Name : " + b.get("bookName"));
                        System.out.println("Borrow Date: " + b.get("borrowDate") +" -> "+ b.get("returnDate") + " Approved by Librarian ID : " + b.get("librarianId") + " Name : " + b.get("librarianName"));   
                        checked = 1;
                        if(b.get("Returned").equals("None")) {
                            System.out.println("Not returned\n");
                        } else {
                            System.out.println("Returned Date: " + b.get("Returned") + "\n");
                        }
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
    public void searchLibrarianByID(int librarianID) {
        System.out.println("Result of librarian with ID : " + librarianID);
        System.out.println("___________________________________________________\n");
        for(Librarian l : Database.librarianList) {
            if(l.ID == librarianID) {
                System.out.println("ID        : " + l.ID);
                System.out.println("Name      : " + l.Name);
                System.out.println("Address   : " + l.Address);
                System.out.println("Phone     : " + l.PhoneNumber);
                System.out.println("Email     : " + l.Email);
                System.out.println("___________________________________________________");
                System.out.println("\nRecord librarian activity : ");
                System.out.println("1. Borrow activity : ");
                int checked = 0;
                String format = "| %-10s | %-20s | %-7s | %-30s | %-25s | %-14s |\n";
                System.out.println("+------------+----------------------+---------+--------------------------------+---------------------------+----------------+");
                System.out.printf(format, "Student ID", "Name", "Book ID" , "Book Name", "Borrow Date", "Return Status");
                System.out.println("+------------+----------------------+---------+--------------------------------+---------------------------+----------------+");
                for (HashMap<String, Object> b : Database.borrowList){
                    if((int)b.get("librarianId") == l.ID) {
                        checked = 1;
                        if(b.get("Returned").equals("None")) {
                            System.out.printf(format, b.get("studentId"), b.get("studentName"), b.get("bookId"), b.get("bookName"),b.get("borrowDate") + " -> " + b.get("returnDate"),"Not Returned");
                        }else{
                            System.out.printf(format, b.get("studentId"), b.get("studentName"), b.get("bookId"), b.get("bookName"),b.get("borrowDate") + " -> " + b.get("returnDate"), b.get("Returned"));
                        }
                        
                    }
                    
                }
                System.out.println("+------------+----------------------+---------+--------------------------------+---------------------------+----------------+");
                System.out.println("2. Return activity : ");
                for (HashMap<String, Object> b : Database.borrowList){
                    if(b.get("LibrarianReturnId").equals(String.valueOf(librarianID))) {
                        System.out.println("Student ID  : " + b.get("studentId") + " Name : " + b.get("studentName"));
                        System.out.println("Book ID     : " + b.get("bookId") + " Name : " + b.get("bookName"));
                        System.out.println("Returned Date: " + b.get("Returned"));
                        checked = 1;
                    }
                }

                if(checked == 0) {
                    System.out.println("No activity");
                }
                return;
            }
        }
        System.out.println("                Librarian not found");
        System.out.println("___________________________________________________");
    }

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

    public void displayLibrarain(){
        System.out.println("#-----------------------------------------------------------------------------------------------------------------#");
        System.out.println("|                                                                                                                 |");
        System.out.println("#                                      Librarain list in Library Management System                                #");
        System.out.println("|                                                                                                                 |");
        System.out.println("#-----------------------------------------------------------------------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int count = 0;
        String format = "| %-5s | %-20s | %-20s | %-15s | %-20s | %-15s |\n";
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.printf(format, " ID", "Name", "Address" , "Phone Number", "Email", "Password");
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        for(Librarian l : Database.librarianList){
            System.out.printf(format, l.ID, l.Name, l.Address, l.PhoneNumber, l.Email, l.getPassword());
            count++;
        }
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.println("--------------- Total librarain : " + count +" --------------- ");
    }

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
        for(Student s : Database.studentList){
            System.out.printf(format, s.ID, s.Name, s.Address, s.PhoneNumber, s.Email, s.getPassword());
            count++;
        }
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.println("--------------- Total students : " + count +" --------------- ");
    }
}
