package Library;
public interface UserAction {
    boolean userLogin(String role);
    void searchBook(String keyword);
    void changePassword();
    void changeName();
    void showInformation();
}
