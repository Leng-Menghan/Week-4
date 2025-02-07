public class Librarain {
    static int totalLibrarain = 0;
    int id;
    String name;
    String address;
    String phoneNumber;
    String email;
    private String password;
    public Librarain(String name, String address, String phoneNumber, String email, String password) {
        this.id = ++totalLibrarain;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    public Librarain(String email, String password) {
        this.email = email;
        this.password = password;
    }
//Check password
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
