public class student{
    static int toalStudent=0;
    int stuID;
    String stuName;
    String stuAddress;
    String stuPhoneNumber;
    String stuEmail;
    private String password;

//Register
    public student(String stuName, String stuAddress, String stuPhoneNumber, String stuEmail, String password) {
        this.stuID = ++toalStudent;
        this.stuName = stuName;
        this.stuAddress = stuAddress;
        this.stuPhoneNumber = stuPhoneNumber;        
        this.stuEmail = stuEmail;
        this.password = password;
    }

//login
    public student(String stuEmail, String password) {
        this.stuEmail = stuEmail;
        this.password = password;
    }

//check password
    public String getPassword(String password) {
        if(this.password.equals(password)){
            return password;
        }else{
            return "Invalid Password";
        }
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