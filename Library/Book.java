package Library;
public class Book {
    protected int bookid;
    protected String category;
    protected String bookname;
    protected String author;
    protected double price;
    protected int quantity;
    protected String publisher;

    public Book(int bookid, String category, String bookname, String author, double price, int quantity, String publisher) {
        this.bookid = bookid;
        this.category = category;
        this.bookname = bookname;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
    }
}
