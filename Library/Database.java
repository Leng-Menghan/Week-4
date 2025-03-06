package Library;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Book> bookList = new ArrayList<Book>();
    public static ArrayList<User> UserList = new ArrayList<User>();
    public static ArrayList<HashMap<String, Object>> borrowList = new ArrayList<>();
    public static ArrayList<HashMap<String, Object>> TmpBorrow = new ArrayList<>();

    public static void GetDataFromUser() {
        String selectQuery = "SELECT * FROM User";
        ResultSet rs = MySQLConnection.executeQuery(selectQuery);
        Database.UserList.clear();
        int inc = 1;
        int inc1 = 1;
        try {
            while (rs != null && rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String phoneNumber = rs.getString("PhoneNumber");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                if (id.equals("S")) {
                    Database.UserList.add(new Student("S" + inc++, name, address, phoneNumber, email, password));
                } else {
                    Database.UserList.add(new Librarian("L" + inc1++, name, address, phoneNumber, email, password));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySQLConnection.closeConnection();
    }

    public static void GetDataFromBook(){
        String selectQuery2 = "SELECT * FROM Book";
        ResultSet rs2 = MySQLConnection.executeQuery(selectQuery2);
        Database.bookList.clear();
        try {
            while (rs2 != null && rs2.next()) {
                int ID = rs2.getInt("ID");
                String ISBN = rs2.getString("ISBN");
                String category = rs2.getString("Category");
                String bookname = rs2.getString("Name");
                String author = rs2.getString("Author");
                double price = rs2.getDouble("Price");
                int quantity = rs2.getInt("Qty");
                String publisher = rs2.getString("Publisher");
                Database.bookList.add(new Book(ID,ISBN, category, bookname, author, price, quantity, publisher));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySQLConnection.closeConnection();
    }

    public static void GetDataFromBorrow(){ 
        String selectQuery3 = "SELECT * FROM BorrowList";
        Database.borrowList.clear();
        ResultSet rs3 = MySQLConnection.executeQuery(selectQuery3);
        try {
            while (rs3 != null && rs3.next()) {
                int BookId = rs3.getInt("BookId");
                String BookName = rs3.getString("BookName");
                String StudentId = rs3.getString("StudentId");
                String StudentName = rs3.getString("StudentName");
                String LibrarianId = rs3.getString("LibrarianId");
                String LibrarianName = rs3.getString("LibrarianName");  
                String BorrowDate = rs3.getString("BorrowDate");
                String ReturnDate = rs3.getString("ReturnDate");
                String LibrarianReturnId = rs3.getString("LibrarianReturnId");
                String LibrarianReturnName = rs3.getString("LibrarianReturnName");
                String Returned = rs3.getString("ReturnedDate");
                HashMap<String, Object> borrowRecord = new HashMap<>();
                borrowRecord.put("bookId", BookId);
                borrowRecord.put("bookName", BookName);
                borrowRecord.put("studentId", StudentId);
                borrowRecord.put("studentName", StudentName);
                borrowRecord.put("librarianId", LibrarianId);
                borrowRecord.put("librarianName", LibrarianName);
                borrowRecord.put("borrowDate", BorrowDate);
                borrowRecord.put("returnDate", ReturnDate);
                borrowRecord.put("librarianReturnId", LibrarianReturnId);
                borrowRecord.put("librarianReturnName", LibrarianReturnName);
                borrowRecord.put("ReturnedDate", Returned);
    
                // Add the record to the borrowList
                Database.borrowList.add(borrowRecord);
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MySQLConnection.closeConnection();
    }

}
