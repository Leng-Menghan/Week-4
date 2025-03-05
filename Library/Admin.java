package Library;
import java.util.HashMap;
import java.util.Scanner;

import Exception.InputException;

public class Admin extends Librarian{
    private String adminUsername = "admin";
    private String adminPassword = "123";
    Scanner scanner = new Scanner(System.in);
    public boolean adminlogin() {
        System.out.print("Enter Username : ");
        String username = scanner.nextLine();
        System.out.print("Enter Password : ");
        String password = scanner.nextLine();
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            return true;
        }
        return false;
    }

    // Search Librarian by id
    public void searchLibrarianByID() {
        String librarianID;
        while(true){
            try{
                System.out.print("Enter Librarian ID     : ");
                librarianID = scanner.nextLine();
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Result of librarian with ID : " + librarianID);
        System.out.println("___________________________________________________\n");
        for(User l : Database.UserList) {
            if(librarianID.equals(l.ID)) {
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
                System.out.printf(format, "Student ID", "Name", "Book ID" , "Book Name", "Borrow Date", "Returned Status");
                System.out.println("+------------+----------------------+---------+--------------------------------+---------------------------+----------------+");
                for (HashMap<String, Object> b : Database.borrowList){
                    if(b.get("librarianId").equals(l.ID)) {
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

    //Display Librarian
    public void displayLibrarain(){
        System.out.println("#-----------------------------------------------------------------------------------------------------------------#");
        System.out.println("|                                                                                                                 |");
        System.out.println("#                                      Librarain list in Library Management System                                #");
        System.out.println("|                                                                                                                 |");
        System.out.println("#-----------------------------------------------------------------------------------------------------------------#");
        int count = 0;
        String format = "| %-5s | %-20s | %-20s | %-15s | %-20s | %-15s |\n";
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.printf(format, " ID", "Name", "Address" , "Phone Number", "Email", "Password");
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        for(User l : Database.UserList){
            if(l.ID.charAt(0)=='L'){
                System.out.println(l);
                count++;
            }
        }
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        System.out.println("--------------- Total librarain : " + count +" --------------- ");
    }

}
