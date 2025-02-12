public class User {
    int ID = 0;
    String Name;
    String Address;
    String PhoneNumber;
    String Email;
    private String password;

    // Register
    public User(String Name, String Address, String PhoneNumber, String Email, String password) {
            this.ID++;
            this.Name = Name;
            this.Address = Address;
            this.PhoneNumber = PhoneNumber;        
            this.Email = Email;
            this.password = password;
        }

    // login
    public User(String Email, String password) {
            this.Email = Email;
            this.password = password;
        }

    // check password
    public String checkPassword(String password) {
        if (this.password.equals(password)) {
            return password;
        } else {
            return "Invalid Password";
        }
    }

    // get password
    public String getPassword() {
        return password;
    }

    // change password
    public void setNewPassword(String newPassword, String oldPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Invalid Password");
        }
    }
}
