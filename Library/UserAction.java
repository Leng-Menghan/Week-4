package Library;
public interface UserAction {
    void displayBook();
    void register(String role);
    boolean login(String role);
    void changeName();
    void changePassword();
    String toString();
}
