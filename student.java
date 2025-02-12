public class Student extends User {
//Register
    public Student(String Name, String Address, String PhoneNumber, String Email, String password) {
        super(Name, Address, PhoneNumber, Email, password); 
    }
    
//login
    public Student(String Email, String password) {
        super(Email, password); 
    }
}
