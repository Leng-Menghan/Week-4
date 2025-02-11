public class Student{
    static int toalStudent=0;
    int stuID;
    String stuName;
    String stuAddress;
    String stuPhoneNumber;
    String stuEmail;
    private String password;

//Register
    public Student(String stuName, String stuAddress, String stuPhoneNumber, String stuEmail, String password) {
        this.stuID = ++toalStudent;
        this.stuName = stuName;
        this.stuAddress = stuAddress;
        this.stuPhoneNumber = stuPhoneNumber;        
        this.stuEmail = stuEmail;
        this.password = password;
    }

//login
    public Student(String stuEmail, String password) {
        this.stuEmail = stuEmail;
        this.password = password;
    }

//check password
    public String checkPassword(String password) {
        if(this.password.equals(password)){
            return password;
        }else{
            return "Invalid Password";
        }
    }

//get password
    public String getPassword() {
        return password;
    }
    
//change password
    public void setNewPassword(String newPassword, String oldPassword) {
        if(this.password.equals(oldPassword)){
            this.password = newPassword;
            System.out.println("Password changed successfully");
        }else{
            System.out.println("Invalid Password");
        }
    }
}