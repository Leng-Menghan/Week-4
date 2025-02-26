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

                break;

                case 3:

                break;

                case 4: 

                break;

                default:
            }
        scanner.close();
    } 
}
