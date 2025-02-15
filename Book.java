public class Book {
    static int totalBook = 0;
    int bookid;
    int isbn;
    String category;
    String bookname;
    String author;
    double price;
    int quantity;
    String publisher;
//Add book
    public Book(int isbn, String category, String bookname, String author, double price, int quantity, String publisher) {
        this.bookid = ++totalBook;
        this.isbn = isbn;
        this.category = category;
        this.bookname = bookname;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
    }
}
