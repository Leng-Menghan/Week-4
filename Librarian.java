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
                for(Student b : Database.studentList) {
                    if(s.ID == b.studentID) {
                        System.out.println("Book ID : " + b.bookID + " Name : " + b.bookName);
                        System.out.println("Borrow Date: " + b.borrowDate +" -> "+ b.returnDate + " Approved by Librarian ID : " + b.librarianID + " Name : " + b.librarianName);
                        checked = 1;
                        int check = 0;
                        for(Student r : Database.studentList) {
                            if(b.bookID == r.bookID && b.studentID == r.studentID) {
                                System.out.println("Returned Date: " + r.returnedDate + " Recorded by Librarian ID : " + r.librarianID + " Name : " + r.librarianName + "\n");
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
