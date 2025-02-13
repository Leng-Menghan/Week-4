public class Admin implements Search{
    String adminEmail = "admin";
    String adminPassword = "123";

    public boolean adminlogin(String email, String password) {
        if (email.equals(adminEmail) && password.equals(adminPassword)) {
            System.out.println( "Admin logged in");
            return true;
        }
        else {
            System.out.println("Invalid email or password");
            return false;
            
        }
    }
    public void searchBookByName(String bookName) {
        for(Book b : Database.bookList) {
            if(b.bookname.equals(bookName)) {
                System.out.println("Book found");
                System.out.println("----------------------------------------------------");
                System.out.println("Book ID   : " + b.bookid);
                System.out.println("ISBN      : " + b.isbn);
                System.out.println("Book name : " + b.bookname);
                System.out.println("Author    : " + b.author);
                System.out.println("Category  : " + b.category);
                System.out.println("Price     : " + b.price + " $");
                System.out.println("Quantity  : " + b.quantity);
                System.out.println("Publisher : " + b.publisher);
                System.out.println("----------------------------------------------------\n");
                return;
            }
        }
        System.out.println("Book not found");
    }

    public void searchBookByAuthor(String authorName) {
        System.out.println("Books of author : " + authorName);
        System.out.println("----------------------------------------------------");
        int checked = 0;
        for(Book b : Database.bookList) {
            if(b.author.equals(authorName)) {
                System.out.println("(ID : " + b.bookid +") : " + b.bookname + " quantity: " + b.quantity);
                checked = 1;
            }
        }
        if(checked == 0) {
            System.out.println("Book not found");
        }
        System.out.println("----------------------------------------------------\n");
    }

    // Search book by category
    public void searchBookByCategory(String category) {
        System.out.println("Books of category : " + category);
        int checked = 0;
        for(Book b : Database.bookList) {
            if(b.category.equals(category)) {
                System.out.println("(ID : " + b.bookid +") : " + b.bookname + " quantity: " + b.quantity);
                checked = 1;
            }
        }
        if(checked == 0) {
            System.out.println("Book not found");
        }
    }

    // Search book by ISBN
    public void searchBookByISBN(int ISBN) {
        for(Book b : Database.bookList) {
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

    // Search student by ID
    public void searchStudentByID(int studentID) {
        for(Student s : Database.studentList) {
            if(s.ID == studentID) {
                System.out.println("ID        : " + s.ID);
                System.out.println("Name      : " + s.Name);
                System.out.println("Address   : " + s.Address);
                System.out.println("Phone     : " + s.PhoneNumber);
                System.out.println("Email     : " + s.Email);
                System.out.println("Password  : " + s.getPassword());
                System.out.println("\nRecord student activity : ");
                int checked = 0;
                for(Student b : Database.studentList) {
                    if(s.ID == b.studentID) {
                        System.out.println("Book ID : " + b.bookID + " Name : " + b.bookName);
                        System.out.println("Borrow Date: " + b.borrowDate +" -> "+ b.returnDate + " Approved by Librarian ID : " + b.librarianID + " Name : " + b.librarianName);
                        checked = 1;
                        int check = 0;
                        for(Student r : Database.studentList) {
                            if(b.bookID == r.bookID && b.studentID == r.studentID) {
                                System.out.println("Returned Date: " + r.returnedDate + " Recorded by Librarian ID : " + r.librarianID + " Name : " + r.librarianName + "\n");
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

    public void searchLibrarianByID(int librarianID) {
        for(Librarian l : Database.librarianList) {
            if(l.ID == librarianID) {
                System.out.println("ID        : " + l.ID);
                System.out.println("Name      : " + l.Name);
                System.out.println("Address   : " + l.Address);
                System.out.println("Phone     : " + l.PhoneNumber);
                System.out.println("Email     : " + l.Email +"\n");
                System.out.println("Record librarian activity : ");
                int checked = 0;
                for(Student b : Database.studentList) {
                    if(l.ID == b.librarianID) {
                        System.out.println("Student ID : " + b.studentID + " Name : " + b.studentName);
                        System.out.println("Book ID : " + b.bookID + " Name : " + b.bookName);
                        System.out.println("Borrow Date: " + b.borrowDate +" -> "+ b.returnDate);
                        checked = 1;
                        int check = 0;
                        for(Student r : Database.studentList) {
                            if(b.bookID == r.bookID && b.studentID == r.studentID) {
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
        System.out.println("Librarian not found");
    }
}
