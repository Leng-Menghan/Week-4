import java.util.ArrayList;

public class search {
//search Book
    // search book by name
    public void searchBookByName(ArrayList<book> bookList,String bookName) {
        for(book b : bookList) {
            if(b.bookname.equals(bookName)) {
                System.out.println("Book found");
                System.out.println("Book ID   : " + b.bookid);
                System.out.println("ISBN      : " + b.isbn);
                System.out.println("Book name : " + b.bookname);
                System.out.println("Author    : " + b.author);
                System.out.println("Category  : " + b.category);
                System.out.println("Price     : " + b.price);
                System.out.println("Quantity  : " + b.quantity);
                System.out.println("Publisher : " + b.publisher);
                return;
            }
        }
        System.out.println("Book not found");
    }
    // search book by author
    public void searchBookByAuthor(ArrayList<book> bookList,String authorName) {
        System.out.println("Books of author : " + authorName);
        int checked = 0;
        for(book b : bookList) {
            if(b.author.equals(authorName)) {
                System.out.println("(ID : " + b.bookid +") : " + b.bookname + " quantity: " + b.quantity);
                checked = 1;
            }
        }
        if(checked == 0) {
            System.out.println("Book not found");
        }
    }
    // search book by category
    public void searchBookByCategory(ArrayList<book> bookList,String category) {
        System.out.println("Books of category : " + category);
        int checked = 0;
        for(book b : bookList) {
            if(b.category.equals(category)) {
                System.out.println("(ID : " + b.bookid +") : " + b.bookname + " quantity: " + b.quantity);
                checked = 1;
            }
        }
        if(checked == 0) {
            System.out.println("Book not found");
        }
    }
    // search book by ISBN
    public void searchBookByISBN(ArrayList<book> bookList,int ISBN) {
        for(book b : bookList) {
            if(b.isbn == ISBN) {
                System.out.println("ISBN      : " + b.isbn);
                System.out.println("Book name : " + b.bookname);
                System.out.println("Author    : " + b.author);
                System.out.println("Category  : " + b.category);
                System.out.println("Price     : " + b.price);
                System.out.println("Quantity  : " + b.quantity);
                System.out.println("Publisher : " + b.publisher);
                return;
            }
        }
        System.out.println("Book not found");
    }

//search student 
    public void searchStudentByID(ArrayList<student> studentList,int studentID, ArrayList<borrow> borrowList, ArrayList<returned> returnedList) {
        for(student s : studentList) {
            if(s.stuID == studentID) {
                System.out.println("ID        : " + s.stuID);
                System.out.println("Name      : " + s.stuName);
                System.out.println("Address   : " + s.stuAddress);
                System.out.println("Phone     : " + s.stuPhoneNumber);
                System.out.println("Email     : " + s.stuEmail);
                System.out.println("Password  : " + s.getPassword());
                System.out.println("\nRecord student activity : ");
                int checked = 0;
                for(borrow b : borrowList) {
                    if(s.stuID == b.studentID) {
                        System.out.println("Book ID : " + b.bookID + " Name : " + b.bookName);
                        System.out.println("Borrow Date: " + b.borrowDate +" -> "+ b.returnDate + " Approved by Librarain ID : " + b.librarainID + " Name : " + b.librarainName);
                        checked = 1;
                        int check = 0;
                            for(returned r : returnedList) {
                                if(b.bookID == r.bookID) {
                                    System.out.println("Returned Date: " + r.returnedDate + " Recorded byLibrarain ID : " + r.librarainID + " Name : " + r.librarainName + "\n");
                                    check = 1;
                                }
                            }
                        if(check == 0) {
                            System.out.println("Not returned\n");
                        }
                    }
                }
                if(checked == 0) {
                    System.out.println("No activity");
                }
                return;
            }
        }
        System.out.println("Student not found");
        
    }

//search librarain
    public void searchLibrarainByID(ArrayList<Librarain> librarainList, int librarainID, ArrayList<borrow> borrowList, ArrayList<returned> returnedList) {
        for(Librarain l : librarainList) {
            if(l.id == librarainID) {
                System.out.println("ID        : " + l.id);
                System.out.println("Name      : " + l.name);
                System.out.println("Address   : " + l.address);
                System.out.println("Phone     : " + l.phoneNumber);
                System.out.println("Email     : " + l.email +"\n");
                System.out.println("Record librarain activity : ");
                int checked = 0;
                for(borrow b : borrowList) {
                    if(l.id == b.librarainID) {
                        System.out.println("Student ID : " + b.studentID + " Name : " + b.studentName);
                        System.out.println("Book ID : " + b.bookID + " Name : " + b.bookName);
                        System.out.println("Borrow Date: " + b.borrowDate +" -> "+ b.returnDate);
                        checked = 1;
                        int check = 0;
                            for(returned r : returnedList) {
                                if(b.bookID == r.bookID) {
                                    System.out.println("Returned Date: " + r.returnedDate + "\n");
                                    check = 1;
                                }
                            }
                        if(check == 0) {
                            System.out.println("Not returned\n");
                        }
                    }
                }

                if(checked == 0) {
                    System.out.println("No activity");
                }
                return;
            }
        }
        System.out.println("Librarain not found");
    }
}