public class Librarian extends User {

    public Librarian(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
    }

    public Librarian(String Email, String password) {
        super(Email, password); 
    }
}
