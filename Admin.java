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
                for (HashMap<String, Object> b : Database.borrowList){
                    if((int)b.get("librarianId") == l.ID) {
                        System.out.println("Student ID  : " + b.get("studentId") + " Name : " + b.get("studentName"));
                        System.out.println("Book ID     : " + b.get("bookId") + " Name : " + b.get("bookName"));
                        System.out.println("Borrow Date : " + b.get("borrowDate") +" -> "+ b.get("returnDate"));
                        checked = 1;
                        if(b.get("Returned").equals("None")) {
                            System.out.println("Not returned\n");
                        }else{
                            System.out.println("Returned Date: " + b.get("Returned") +"\n");
                        }
                    }
                }
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
        System.out.println("#--------------------------------------------------------------------#");
        System.out.println("|                                                                    |");
        System.out.println("#   Borrowed list (Not returned yet) in Library Management System    #");
        System.out.println("|                                                                    |");
        System.out.println("#--------------------------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int check = 0;
        for (HashMap<String, Object> b : Database.borrowList){
            if(b.get("Returned").equals("None")) {
                System.out.println("Book ID        : " + b.get("bookId"));
                System.out.println("Book name      : " + b.get("bookName"));
                System.out.println("Student ID     : " + b.get("studentId"));
                System.out.println("Student Name   : " + b.get("studentName"));
                System.out.println("Librarian ID   : " + b.get("librarianId"));
                System.out.println("Librarian Name : " + b.get("librarianName"));
                System.out.println("Borrow Date    : " + b.get("borrowDate") + " -> " + b.get("returnDate"));
                System.out.println("___________________________________________________\n");
                count++;
                check = 1;
            }
        }
        if(check == 0){
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
        System.out.println("--------------- Total borrowed : "+ count +" --------------- ");
        System.out.println("___________________________________________________\n");
    }

    public void displayReturn(){
        int count = 0;
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#   Returned list in Library Management System    #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int check = 0;
        for (HashMap<String, Object> b : Database.borrowList){
            if(!b.get("Returned").equals("None")) {
            System.out.println("Book ID        : " + b.get("bookId"));
            System.out.println("Book name      : " + b.get("bookName"));
            System.out.println("Student ID     : " + b.get("studentId"));
            System.out.println("Student Name   : " + b.get("studentName"));
            System.out.println("Librarian ID   : " + b.get("LibrarianReturnId"));
            System.out.println("Librarian Name : " + b.get("LibrarianReturnName"));
            System.out.println("Returned Date  : " + b.get("Returned"));
            System.out.println("___________________________________________________\n");
            count++;
            check = 1;
            }
        }
        if(check == 0){
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
        System.out.println("--------------- Total returned : "+count+" --------------- ");
        System.out.println("___________________________________________________\n");
    }

    public void displayLibrarain(){
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#   Librarain list in Library Management System   #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int count = 0;
        for(Librarian l : Database.librarianList){
            System.out.println("ID       : " + l.ID);
            System.out.println("Name     : " + l.Name);
            System.out.println("Address  : " + l.Address);
            System.out.println("Phone    : " + l.PhoneNumber);
            System.out.println("Email    : " + l.Email);
            System.out.println("Password : " + l.getPassword());
            System.out.println("___________________________________________________\n");
            count++;
        }
        System.out.println("--------------- Total librarain : " + count +" --------------- ");
        System.out.println("___________________________________________________\n");
    }

    public void displayStudent(){
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#    Student list in Library Management System    #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int count = 0;
        for(Student s : Database.studentList){
            System.out.println("ID       : " + s.ID);
            System.out.println("Name     : " + s.Name);
            System.out.println("Address  : " + s.Address);
            System.out.println("Phone    : " + s.PhoneNumber);
            System.out.println("Email    : " + s.Email);
            System.out.println("Password : " + s.getPassword());
            System.out.println("___________________________________________________\n");
            count++;
        }
        System.out.println("--------------- Total students : " + count +" --------------- ");
        System.out.println("___________________________________________________\n");
    }
}
