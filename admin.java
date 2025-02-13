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
}
