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
        for(Student s : Database.studentList) {
            if(s.ID == studentID) {
                System.out.println("ID        : " + s.ID);
                System.out.println("Name      : " + s.Name);
                System.out.println("Address   : " + s.Address);
                System.out.println("Phone     : " + s.PhoneNumber);
                System.out.println("Email     : " + s.Email);
                System.out.println("Password  : " + s.getPassword());
                System.out.println("\nRecord student activity : ");
                int checked = 0;
                for (HashMap<String, Object> b : Database.borrowList){
                    if((int)b.get("studentId") == s.ID) {
                        System.out.println("Book ID : " + b.get("bookId") + " Name : " + b.get("bookName"));
                        System.out.println("Borrow Date: " + b.get("borrowDate") +" -> "+ b.get("returnDate") + " Approved by Librarian ID : " + b.get("librarianId") + " Name : " + b.get("librarianName"));   
                        checked = 1;
                        int check = 0;
                        for (HashMap<String, Object> r : Database.returnedList) {
                            if(b.get("bookId").equals(r.get("bookId")) && b.get("studentId").equals(r.get("studentId"))) {
                                System.out.println("Returned Date: " + r.get("returnedDate") + " Recorded by Librarian ID : " + r.get("librarianId") + " Name : " + r.get("librarianName") + "\n");
                                check = 1;
                            }
                        }
                        if(check == 0) {
                            System.out.println("Not returned\n");
                        }
                    }
                }
                if(checked == 0) {
                    System.out.println("No activity");
                }
                return;
            }
        }
        System.out.println("Student not found");
    }

    public void searchLibrarianByID(int librarianID) {
        for(Librarian l : Database.librarianList) {
            if(l.ID == librarianID) {
                System.out.println("ID        : " + l.ID);
                System.out.println("Name      : " + l.Name);
                System.out.println("Address   : " + l.Address);
                System.out.println("Phone     : " + l.PhoneNumber);
                System.out.println("Email     : " + l.Email +"\n");
                System.out.println("Record librarian activity : ");
                int checked = 0;
                for (HashMap<String, Object> b : Database.borrowList){
                    if((int)b.get("librarianId") == l.ID) {
                        System.out.println("Student ID : " + b.get("studentid") + " Name : " + b.get("studentName"));
                        System.out.println("Book ID : " + b.get("bookId") + " Name : " + b.get("bookName"));
                        System.out.println("Borrow Date: " + b.get("borrowDate") +" -> "+ b.get("returnDate"));
                        checked = 1;
                        int check = 0;
                        for (HashMap<String, Object> r : Database.returnedList) {
                            if((int)b.get("bookId") == (int)r.get("bookId") && (int)b.get("studentId") == (int)r.get("studentId")) {
                                System.out.println("Returned Date: " + r.get("returnedDate") + "\n");
                                check = 1;
                            }
                        }
                        if(check == 0) {
                            System.out.println("Not returned\n");
                        }
                    }
                }

                if(checked == 0) {
                    System.out.println("No activity");
                }
                return;
            }
        }
        System.out.println("Librarian not found");
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
            // for(Map.Entry<String, Object> borrow : b.entrySet()){
                
            // }
            int returned = 0;
            for (HashMap<String, Object> r : Database.returnedList) {
                if (b.get("bookId") == r.get("bookId") && b.get("studentId") == r.get("studentId")) {
                    returned = 1;
                }
            }
            if (returned == 0) {
                System.out.println("Book ID        : " + b.get("bookId"));
                System.out.println("Book name      : " + b.get("bookName"));
                System.out.println("Student ID     : " + b.get("studentId"));
                System.out.println("Student Name   : " + b.get("studentName"));
                System.out.println("Librarian ID   : " + b.get("librarianId"));
                System.out.println("Librarian Name : " + b.get("librarianName"));
                System.out.println("Borrow Date    : " + b.get("borrowDate") + " -> " + b.get("returnDate"));
                System.out.println("___________________________________________________\n");
                check = 1;
                count++;
            }
        }
        if(check == 0){
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
        System.out.println("--------------- Total borrowed : "+count+" --------------- ");
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
        for (HashMap<String, Object> r : Database.returnedList) {
            System.out.println("Book ID        : " + r.get("bookId"));
            System.out.println("Book name      : " + r.get("bookName"));
            System.out.println("Student ID     : " + r.get("studentId"));
            System.out.println("Student Name   : " + r.get("studentName"));
            System.out.println("Librarian ID   : " + r.get("librarianId"));
            System.out.println("Librarian Name : " + r.get("librarianName"));
            for (HashMap<String, Object> b : Database.borrowList){
                if (b.get("bookId") == r.get("bookId") && b.get("studentId") == r.get("studentId")) {
                    System.out.println("Borrow Date    : " + b.get("borrowDate") + " -> " + b.get("returnDate"));
                }
            }
            System.out.println("Returned Date  : " + r.get("returnedDate"));
            System.out.println("___________________________________________________\n");
            count++;
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
