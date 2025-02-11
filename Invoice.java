public class Invoice {
    static double payment = 0;
    public static void DisplayInvoice(){
        System.out.println("#-------------------------------------------------#");
        System.out.println("|                                                 |");
        System.out.println("#              Invoice of borrowing               #");
        System.out.println("|                                                 |");
        System.out.println("#-------------------------------------------------#");
        System.out.println("---------------------------------------------------");
        for(Borrow b : Database.TmpBorrow){
            System.out.println("Book ID      : " + b.bookID + ", Book name : " + b.bookName);
            System.out.println("Student ID   : " + b.studentID + ", Student Name : " + b.studentName);
            System.out.println("Librarian ID : " + b.librarainID + ", Librarian Name : " + b.librarainName);
            System.out.println("Borrow Date  : " + b.borrowDate + " -> " + b.returnDate);
            System.out.println("---------------------------------------------------");
            payment+=b.payForBorrow;
        }
        System.out.println("             Total payment : " + payment +" $" );
        System.out.println("---------------------------------------------------\n");
        Database.borrowList.addAll(Database.TmpBorrow);
        Database.TmpBorrow.clear();
        payment = 0;
    }
}
