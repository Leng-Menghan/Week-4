package Library;

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import Exception.CharacterOnlyException;
import Exception.EmailException;
import Exception.AtLeastOneCharacter;
import Exception.NumberOnlyException;

public abstract class User implements UserAction {
    protected String ID;
    protected String Name;
    protected String Address;
    protected String PhoneNumber;
    protected String Email;
    private String Password;
    Scanner scanner = new Scanner(System.in);

    // Constructor
    public User(String ID, String Name, String Address, String PhoneNumber, String Email, String password) {
        this.Name = Name;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Password = password;
        this.ID = ID;
    }

    // default constructor
    public User() {};

    // get password
    public String getPassword() {
        return this.Password;
    }

    //Register
    public void register(String role) {
        Database.GetDataFromUser();
        String name;
        String address;
        String phoneNumber;
        String email;
        String password;
            System.out.println("Please Register as Librarian");
            while (true) {
                try {
                    System.out.print("Enter name : ");
                    name = scanner.nextLine();
                    CharacterOnlyException test = new CharacterOnlyException(name, "^[a-zA-Z ]+$");
                    break;
                } catch (CharacterOnlyException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter address : ");
                    address = scanner.nextLine();
                    AtLeastOneCharacter test = new AtLeastOneCharacter(address, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                    break;
                } catch (AtLeastOneCharacter e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter phone number : ");
                    phoneNumber = scanner.nextLine();
                    NumberOnlyException test = new NumberOnlyException(phoneNumber, "^[0-9 ]+$");
                    break;
                } catch (NumberOnlyException e) {
                    System.out.println(e.getMessage());
                }
            }
    
        while(true){
            while (true) {
                try {
                    System.out.print("Enter email : ");
                    email = scanner.nextLine();
                    EmailException test = new EmailException(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                    break;
                } catch (EmailException e) {
                    System.out.println(e.getMessage());
                }
            }
    
            while (true) {
                try {
                    System.out.print("Enter password : ");
                    password = scanner.nextLine();
                    AtLeastOneCharacter test = new AtLeastOneCharacter(password, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                    break;
                } catch (AtLeastOneCharacter e) {
                    System.out.println(e.getMessage());
                }
            }
            
            int found = 0;
            for (User u : Database.UserList) {
                if(u.ID.startsWith(role)) {
                    if (u.Email.equals(email) && u.getPassword().equals(password)) {
                        System.out.println("User already exists. Please try again.");
                        found = 1;
                        break;
                    }
                };
            }
            if(found == 0) break;
        }

        String insertQuery = String.format(
                "INSERT INTO User (role, Name, Address, PhoneNumber, Email, Password) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                role, name, address, phoneNumber, email, password);
        MySQLConnection.executeUpdate(insertQuery);
        Database.GetDataFromUser();
        
        String GetID = "select concat(user.role, user.ID) as userID, user.Name from user where user.Email = '"+ email +"' and user.Password = '"+ password +"'";
        ResultSet rs = MySQLConnection.executeQuery(GetID);
        try {
            while (rs != null && rs.next()) {
                ID = rs.getString("userID");
                Name = rs.getString("Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySQLConnection.closeConnection();
    }
    
    //Login
    public boolean login(String role) {
        Database.GetDataFromUser();
        String email;
        while(true){
            try{
                System.out.print("Enter email : ");
                email = scanner.nextLine();
                EmailException test = new EmailException(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                break;
            } catch (EmailException e) {
                System.out.println(e.getMessage());
            }
        }
        String password;
        while(true){
            try{
                System.out.print("Enter password : ");
                password = scanner.nextLine();
                AtLeastOneCharacter test = new AtLeastOneCharacter(password, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (AtLeastOneCharacter e) {
                System.out.println(e.getMessage());
            }
        }
        for(User u : Database.UserList) {
            if(u.ID.startsWith(role)){ {
                if (u.Email.equals(email) && u.getPassword().equals(password)) {
                    System.out.println("User logged in.");
                    ID = u.ID;
                    Name = u.Name;
                    return true;
                }
            }
        }
    }
        return false;
    }

    //Change Password
    public void changePassword() {
        Database.GetDataFromUser();
        String currentPassword;
         while(true){
            try{
                System.out.print("Enter current password: ");
                currentPassword = scanner.nextLine();
                AtLeastOneCharacter test = new AtLeastOneCharacter(currentPassword, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
                break;
            } catch (AtLeastOneCharacter e) {
                System.out.println(e.getMessage());
            }
        }
        
        String newPassword;
        while(true){
           try{
                System.out.print("Enter new password: ");
               newPassword = scanner.nextLine();
               AtLeastOneCharacter test = new AtLeastOneCharacter(newPassword, "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$");
               break;
           } catch (AtLeastOneCharacter e) {
               System.out.println(e.getMessage());
           }
        }
        
        for(User u : Database.UserList) {
            if(ID.equals(u.ID)) {
                if (u.getPassword().equals(currentPassword)) {
                    String updateQuery = String.format("UPDATE User SET Password = '%s' WHERE ID = '%s'", newPassword, ID);
                    MySQLConnection.executeUpdate(updateQuery);
                    System.out.println("Password changed successfully.");
                    return;
                }
                break;
            }
        }

        System.out.println("Invalid current password.");        
    }
    
    //Change Name
    public void changeName() {
        Database.GetDataFromUser();
        String newName;
        while(true){
            try{
                System.out.print("Enter new name: ");
                newName = scanner.nextLine();
                CharacterOnlyException test = new CharacterOnlyException(newName, "^[a-zA-Z ]+$");
                break;
            } catch (CharacterOnlyException e) {
                System.out.println(e.getMessage());
            }
        }
        for(User u : Database.UserList) {
            if(ID.equals(u.ID)) {
                String updateQuery = String.format("UPDATE User SET Name = '%s' WHERE ID = '%s'", newName, ID);
                MySQLConnection.executeUpdate(updateQuery);
                System.out.println("Name changed successfully.");
                return;
            }
        }
    }
    
    // Searh Book
    public void searchBook() {
        Database.GetDataFromBook();
        if(Database.bookList.isEmpty()) {
            System.out.println("Book list is empty.");
            return;
        }
        System.out.print("Enter book Name or ID or Author or Category: ");
        String searchBook = scanner.nextLine();
        int found = 0;
        System.out.println(
                "#---------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println(
                "|                                                                                                                                 |");
        System.out.println(
                "#                                           Book list in Library Management System                                                #");
        System.out.println(
                "|                                                                                                                                 |");
        System.out.println(
                "#---------------------------------------------------------------------------------------------------------------------------------#");
        String format = "| %-3s | %-6s | %-27s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
        System.out.println(
                "+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
        System.out.printf(format, "ID", "ISBN", "Name", "Author", "Category", "Price", "Quantity", "Publisher");
        System.out.println(
                "+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+");
        for (Book b : Database.bookList) {
            if (b.bookname.toLowerCase().equals(searchBook.toLowerCase())
                    || b.bookid == Integer.parseInt(searchBook)
                    || b.author.toLowerCase().equals(searchBook.toLowerCase())
                    || b.category.toLowerCase().equals(searchBook.toLowerCase())) {
                System.out.println(b);
                found = 1;
            }
        }
        if (found == 0) {
            System.out.println("Book not found");
        }
    }

    // Display Book
    public void displayBook() {
        Database.GetDataFromBook();
        if(Database.bookList.isEmpty()){
            System.out.println("Book list is empty.");
            return;
        }
        System.out.println(
                "#---------------------------------------------------------------------------------------------------------------------------------#");
        System.out.println(
                "|                                                                                                                                 |");
        System.out.println(
                "#                                           Book list in Library Management System                                                #");
        System.out.println(
                "|                                                                                                                                 |");
        System.out.println(
                "#---------------------------------------------------------------------------------------------------------------------------------#");
        String format = "| %-3s |  %-27s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
        System.out.println(
                "+-----+------------------------------+----------------------+----------------------+---------+----------+-----------------+");
        System.out.printf(format, "ID",  "Name", "Author", "Category", "Price", "Quantity", "Publisher");
        System.out.println(
                "+-----+------------------------------+----------------------+----------------------+---------+----------+-----------------+");
        int count = 0;
        for (Book b : Database.bookList) {
            System.out.println(b);
            count++;
        }
        System.out.println("| Total Books : " + count + "   |\n");
    }

    @Override
    public String toString() {
        String format = "| %-5s | %-20s | %-20s | %-15s | %-20s | %-15s |";
        String result = String.format(format, ID, Name, Address, PhoneNumber, Email, Password);
        System.out.println("+-------+----------------------+----------------------+-----------------+----------------------+-----------------+");
        return result;
    }

    // abstract method
    // For student
    public abstract void Borrow();

    public abstract void Returned();

    public abstract void DisplayInvoice();

    // For librarian
    public abstract void searchStudentByID();

    public abstract void displayStudent();

    public abstract void displayBorrow();

    public abstract void displayReturn();

    public abstract void addBook();

    public abstract void deleteBook();

    public abstract void addBookQuantityByID();
}
