public interface UserAction {
    void searchBookByName(String bookName);
    void searchBookByAuthor(String authorName);
    void searchBookByCategory(String category);
    void searchBookByISBN(int ISBN);
    void displayBook();
    boolean login(String email, String password);
}
