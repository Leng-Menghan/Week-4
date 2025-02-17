import java.util.HashMap;

public class Librarian extends User {
    static int total = 0;
    int ID = 0;
    public Librarian(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
        this.ID = ++total;
    }

    public Librarian(String Email, String password) {
        super(Email, password); 
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
