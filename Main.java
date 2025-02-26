import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        User student = new Student();
        User librarian = new Librarian();
        Scanner scanner = new Scanner(System.in);
            System.out.println("#---------------------------------------------------#");
            System.out.println("|                                                   |");
            System.out.println("#        Welcome to Library Management System       #");
            System.out.println("|                                                   |");
            System.out.println("#---------------------------------------------------#");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Admin");
            System.out.println("2. Librarian");
            System.out.println("3. Student");
            System.out.println("4. Exit");
            System.out.println("----------------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Please Login as Admin");
                    System.out.print("Enter Username : ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Password : ");
                    String password = scanner.nextLine();
                    admin.login(username, password);
                    System.out.println("Welcome to Admin function");
                    System.out.println("1. Display Librarian");
                    System.out.println("2. Display Student");
                    System.out.println("3. Display Book");
                    System.out.println("4. Add Book");
                    System.out.println("5. Add Book Quantity By ISBN");
                    System.out.println("6. Remove Book By ISBN");
                    System.out.println("7. Search Book By ISBN");
                    System.out.println("8. Search Book By Name");
                    System.out.println("9. Search Book By Author");
                    System.out.println("10. Search Book By Category");
                    System.out.print("Please Select Option (1-10) : ");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            admin.displayLibrarain();
                        break;
                        case 2:
                            admin.displayStudent();
                        break;
                        case 3:
                            admin.displayBook();
                        break;
                        case 4:
                            admin.addBook();
                        break;
                        case 5:
                            admin.addBookQuantityByISBN();
                        break;
                        case 6:
                            admin.deleteBook();
                        break;
                        case 7:
                            System.out.print("Enter book ISBN: ");
                            int ISBN = scanner.nextInt();
                            admin.searchBookByISBN(ISBN);
                        break;
                        case 8:
                            System.out.print("Enter book name: ");
                            String bookName = scanner.nextLine();
                            admin.searchBookByName(bookName);
                        break;
                        case 9:
                            System.out.print("Enter book author: ");
                            String authorName = scanner.nextLine();
                            admin.searchBookByAuthor(authorName);
                        break;
                        case 10:
                            System.out.print("Enter book category: ");
                            String category = scanner.nextLine();
                            admin.searchBookByCategory(category);
                        break;
                        default:
                        break;
                    }
                break;

                case 2:
                    System.out.println("Librarian");
                    System.out.println("1. Register Librarian");
                    System.out.println("2. Log in Librarian");
                    System.out.print("Please Select Option (1-2) : ");
                    int option1 = scanner.nextInt();
                    scanner.nextLine();
                    switch (option1) {
                        case 1:
                            librarian.register();
                        break;
                        case 2:
                            System.out.println("Please Login as Librarian");
                            System.out.print("Enter Email : ");
                            String Email = scanner.nextLine();
                            System.out.print("Enter Password : ");
                            String password1 = scanner.nextLine();
                            librarian.login(Email, password1);
                            System.out.println("Welcome to Librarian function");
                            System.out.println("1. Display Student");
                            System.out.println("2. Display Book");
                            System.out.println("3. Add Book");
                            System.out.println("4. Add Book Quantity By ISBN");
                            System.out.println("5. Remove Book By ISBN");
                            System.out.println("6. Search Book By ISBN");
                            System.out.println("7. Search Book By Name");
                            System.out.println("8. Search Book By Author");
                            System.out.println("9. Search Book By Category");
                            System.out.print("Please Select Option (1-9) : ");
                            int option3 = scanner.nextInt();
                            scanner.nextLine();
                            switch (option3) {
                                case 1:
                                    librarian.displayStudent();
                                break;
                                case 2:
                                    librarian.displayBook();
                                break;
                                case 3:
                                    librarian.addBook();
                                break;
                                case 4:
                                    librarian.addBookQuantityByISBN();
                                break;
                                case 5:
                                    librarian.deleteBook();
                                break;
                                case 6:
                                    System.out.print("Enter book ISBN: ");
                                    int ISBN1 = scanner.nextInt();
                                    librarian.searchBookByISBN(ISBN1);
                                break;
                                case 7:
                                    System.out.print("Enter book name: ");
                                    String bookName1 = scanner.nextLine();
                                    librarian.searchBookByName(bookName1);
                                break;
                                case 8:
                                    System.out.print("Enter book author: ");
                                    String authorName1 = scanner.nextLine();
                                    librarian.searchBookByAuthor(authorName1);
                                break;
                                case 9:
                                    System.out.print("Enter book category: ");
                                    String category1 = scanner.nextLine();
                                    librarian.searchBookByCategory(category1);
                                break;
                                default:
                                break;
                            }
                    }
                break;

                case 3:
                    System.out.println("Student");
                    System.out.println("1. Register Student");
                    System.out.println("2. Log in Student");
                    System.out.print("Please Select Option (1-2) : ");
                    int option2 = scanner.nextInt();
                    scanner.nextLine();
                    switch (option2) {
                        case 1:
                            student.register();
                        break;
                        case 2:
                        System.out.println("Please Login as Student");
                        System.out.print("Enter Email : ");
                        String Email1 = scanner.nextLine();
                        System.out.print("Enter Password : ");
                        String password2 = scanner.nextLine();
                        student.login(Email1, password2);
                        System.out.println("Welcome to Student function");
                        System.out.println("1. Display Book");
                        System.out.println("2. Search Book By ISBN");
                        System.out.println("3. Search Book By Name");
                        System.out.println("4. Search Book By Author");
                        System.out.println("5. Search Book By Category");
                        System.out.println("6. Borrow Book");
                        System.out.println("7. Return Book");
                        System.out.print("Please Select Option (1-7) : ");
                        int option3 = scanner.nextInt();
                        scanner.nextLine();
                        switch (option3) {
                            case 1:
                                student.displayBook();
                            break;
                            case 2:
                                System.out.print("Enter book ISBN: ");
                                int ISBN2 = scanner.nextInt();
                                student.searchBookByISBN(ISBN2);
                            break;
                            case 3:
                                System.out.print("Enter book name: ");
                                String bookName2 = scanner.nextLine();
                                student.searchBookByName(bookName2);
                            break;
                            case 4:
                                System.out.print("Enter book author: ");
                                String authorName2 = scanner.nextLine();
                                student.searchBookByAuthor(authorName2);
                            break;
                            case 5:
                                System.out.print("Enter book category: ");
                                String category2 = scanner.nextLine();
                                student.searchBookByCategory(category2);
                            break;
                            case 6:
                                System.out.print("Enter book ID : ");
                                int bookId = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter student ID : ");
                                String studentID = scanner.nextLine();
                                System.out.print("Enter librarian ID : ");
                                String librarianID = scanner.nextLine();
                                System.out.print("Enter borrow Date : ");
                                String BDate = scanner.nextLine();
                                System.out.print("Enter return Date : ");
                                String RDate = scanner.nextLine();
                                student.Borrow(bookId, studentID, librarianID, BDate, RDate);
                            break;
                            case 7:
                                System.out.print("Enter book ID : ");
                                int bookId1 = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter student ID : ");
                                String studentID1 = scanner.nextLine();
                                System.out.print("Enter librarian ID : ");
                                String librarianID1 = scanner.nextLine();
                                System.out.print("Enter return Date : ");
                                String RDate1 = scanner.nextLine();
                                student.Returned(bookId1, studentID1, librarianID1, RDate1);
                            break;
                        }
                    }
                    break;
                case 4 : 

                break;
                default:
            }
        scanner.close();
    } 
}
