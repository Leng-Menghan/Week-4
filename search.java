public interface Search {
    void searchBookByName(String bookName);
    void searchBookByAuthor(String authorName);
    void searchBookByCategory(String category);
    void searchBookByISBN(int ISBN);
}
