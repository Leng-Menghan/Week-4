public class Librarain {
    int id;
    String name;
    String address;
    String phoneNumber;
    String email;
    private String password;
    public Librarain(int id, String name, String address, String phoneNumber, String email, String password) {
        this.id = id;
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
