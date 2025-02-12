public interface Searchable {
    void searchBookByName(String bookName);
    void searchBookByAuthor(String authorName);
    void searchBookByCategory(String category);
    void searchBookByISBN(int ISBN);

    void searchStudentByID(int studentID);

    void searchLibrarianByID(int librarianID);
}
