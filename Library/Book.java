package Library;
public class Book {
    public int bookid;
    public String category;
    public String bookname;
    public String author;
    public double price;
    public int quantity;
    public String publisher;

    public Book(int bookid, String category, String bookname, String author, double price, int quantity, String publisher) {
        this.bookid = bookid;
        this.category = category;
        this.bookname = bookname;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        String format = "| %-3s | %-28s | %-20s | %-20s | %-7s | %-8s | %-15s |\n";
        String result = String.format(format, bookid, bookname, author, category, price + " $", quantity, publisher);
        String line = "+-----+------------------------------+----------------------+----------------------+---------+----------+-----------------+";
        return result + line;
    }
    
}
