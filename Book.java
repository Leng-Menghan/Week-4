public class Book {
    static int totalBook = 0;
    int bookid;
    String isbn;
    String category;
    String bookname;
    String author;
    double price;
    int quantity;
    String publisher;

//Add book
    public Book(String isbn, String category, String bookname, String author, double price, int quantity, String publisher) {
        this.bookid = ++totalBook;
        this.isbn = isbn;
        this.category = category;
        this.bookname = bookname;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        String format = "| %-3s | %-6s | %-27s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
        String result = String.format(format, bookid, isbn, bookname, author, category, price + " $", quantity, publisher);
        String line = "+-----+--------+-----------------------------+----------------------+----------------------+---------+----------+-----------------+";
        return result + line;
    }
    
}
