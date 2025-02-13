import java.util.HashMap;

public class Librarian extends User {

    public Librarian(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
    }

    public Librarian(String Email, String password) {
        super(Email, password); 
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
}
