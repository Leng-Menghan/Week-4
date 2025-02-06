public class book {
    static int totalBook = 0;
    int bookid;
    int isbn;
    String category;
    String bookname;
    String author;
    int price;
    int quantity;
    String publisher;
//Add book
    public book(int isbn, String category, String bookname, String author, int price, int quantity, String publisher) {
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
