public class Display {
    public static void displayStudent(){
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#    Student list in Library Management System    #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int count = 0;
        for(Student s : Database.studentList){
            System.out.println("ID       : " + s.ID);
            System.out.println("Name     : " + s.Name);
            System.out.println("Address  : " + s.Address);
            System.out.println("Phone    : " + s.PhoneNumber);
            System.out.println("Email    : " + s.Email);
            System.out.println("Password : " + s.getPassword());
            System.out.println("___________________________________________________\n");
            count++;
        }
        System.out.println("--------------- Total students : " + count +" --------------- ");
        System.out.println("___________________________________________________\n");
    }

    public static void displayBook(){
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#     Book list in Library Management System      #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int count = 0;
        for(Book b : Database.bookList){
            System.out.println("ID        : " + b.bookid);
            System.out.println("ISBN      : " + b.isbn);
            System.out.println("Name      : " + b.bookname);
            System.out.println("Author    : " + b.author);
            System.out.println("Category  : " + b.category);
            System.out.println("Price     : " + b.price);
            System.out.println("Quantity  : " + b.quantity);
            System.out.println("Publisher : " + b.publisher);
            System.out.println("___________________________________________________\n");
            count++;
        }
        System.out.println("----------------- Total Books : " + count +" ----------------- ");
        System.out.println("___________________________________________________\n");
    }

    public static void displayLibrarain(){
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#   Librarain list in Library Management System   #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int count = 0;
        for(Librarian l : Database.librarianList){
            System.out.println("ID       : " + l.ID);
            System.out.println("Name     : " + l.Name);
            System.out.println("Address  : " + l.Address);
            System.out.println("Phone    : " + l.PhoneNumber);
            System.out.println("Email    : " + l.Email);
            System.out.println("Password : " + l.getPassword());
            System.out.println("___________________________________________________\n");
            count++;
        }
        System.out.println("--------------- Total librarain : " + count +" --------------- ");
        System.out.println("___________________________________________________\n");
    }

    public static void displayBorrow(){
        int count = 0;
        System.out.println("#--------------------------------------------------------------------#");
        System.out.println("|                                                                    |");
        System.out.println("#   Borrowed list (Not returned yet) in Library Management System    #");
        System.out.println("|                                                                    |");
        System.out.println("#--------------------------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        int check = 0;
        for (Borrow b : Database.borrowList){
            int returned = 0;
            for (Returned r : Database.returnedList){
                if (b.bookID == r.bookID && b.studentID == r.studentID) {
                    returned = 1;
                }
            }
            if (returned == 0) {
                System.out.println("Book ID        : " + b.bookID);
                System.out.println("Book name      : " + b.bookName);
                System.out.println("Student ID     : " + b.studentID);
                System.out.println("Student Name   : " + b.studentName);
                System.out.println("Librarian ID   : " + b.librarianID);
                System.out.println("Librarian Name : " + b.librarianName);
                System.out.println("Borrow Date    : " + b.borrowDate + " -> " + b.returnDate);
                System.out.println("___________________________________________________\n");
                check = 1;
                count++;
            }
        }
        if(check == 0){
            System.out.println("                     None !");
            System.out.println("___________________________________________________\n");
        }
        System.out.println("--------------- Total borrowed : "+count+" --------------- ");
        System.out.println("___________________________________________________\n");
    }

    public static void displayReturn(){
        int count = 0;
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#   Returned list in Library Management System    #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("___________________________________________________\n");
        for (Returned r : Database.returnedList){
            System.out.println("Book ID        : " + r.bookID);
            System.out.println("Book name      : " + r.bookName);
            System.out.println("Student ID     : " + r.studentID);
            System.out.println("Student Name   : " + r.studentName);
            System.out.println("Librarian ID   : " + r.librarianID);
            System.out.println("Librarian Name : " + r.librarianName);
            for(Borrow b : Database.borrowList){
                if(r.bookID == b.bookID && r.studentID == b.studentID){
                    System.out.println("Borrow Date    : " + b.borrowDate + " -> " + b.returnDate);
                }
            }
            System.out.println("Returned Date  : " + r.returnedDate);
            System.out.println("___________________________________________________\n");
            count++;
        }
        System.out.println("--------------- Total returned : "+count+" --------------- ");
        System.out.println("___________________________________________________\n");
    }

    
    public static void DisplayInvoice(){
        double payment = 0;
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#              Invoice of borrowing               #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("---------------------------------------------------");
        for(Borrow b : Database.TmpBorrow){
            System.out.println("Book ID      : " + b.bookID + " Book name : " + b.bookName);
            System.out.println("Student ID   : " + b.studentID + " Student Name : " + b.studentName);
            System.out.println("Librarian ID : " + b.librarianID + " Librarian Name : " + b.librarianName);
            System.out.println("Borrow Date  : " + b.borrowDate + " -> " + b.returnDate);
            System.out.println("---------------------------------------------------");
            payment+=b.payForBorrow;
        }
        System.out.println("             Total payment : " + payment +" $" );
        System.out.println("---------------------------------------------------\n");
        Database.borrowList.addAll(Database.TmpBorrow);
        Database.TmpBorrow.clear();
    }
    

}
