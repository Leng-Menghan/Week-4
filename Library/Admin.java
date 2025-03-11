package Library;
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
        Database.GetDataFromUser();
        if(Database.UserList.isEmpty()){
            System.out.println("User list is empty.");
            return;
        }
        String librarianID;
        while(true){
            try{
                System.out.print("Enter Librarian ID     : ");
                librarianID = scanner.nextLine();
                InputException test = new InputException(librarianID, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$","Must has at least one character");
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
                return;
            }
        }
        System.out.println("                Librarian not found");
        System.out.println("___________________________________________________");
    }

    //Display Librarian
    public void displayLibrarain(){
        Database.GetDataFromUser();
        if(Database.UserList.isEmpty()){
            System.out.println("No data in Librarain list");   
            return;
        }
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
                System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
                count++;
            }
        }
       
        System.out.println("--------------- Total librarain : " + count +" --------------- ");
    }

}
