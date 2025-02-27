import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        User student = new Student();
        User librarian = new Librarian();
        librarian.addBook();
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
                    admin.adminlogin();
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
                            admin.searchBookByISBN();
                        break;
                        case 8:
                            admin.searchBookByName();
                        break;
                        case 9:
                            admin.searchBookByAuthor();
                        break;
                        case 10:
                            admin.searchBookByCategory();
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
                            int option2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (option2) {
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
                                    librarian.searchBookByISBN();
                                break;
                                case 7:
                                    librarian.searchBookByName();
                                break;
                                case 8:
                                    librarian.searchBookByAuthor();
                                break;
                                case 9:
                                    librarian.searchBookByCategory();
                                break;
                            }
                        break;
                        case 2:
                            System.out.println("Please Login as Librarian");
                            librarian.login();
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
                                    librarian.searchBookByISBN();
                                break;
                                case 7:
                                    librarian.searchBookByName();
                                break;
                                case 8:
                                    librarian.searchBookByAuthor();
                                break;
                                case 9:
                                    librarian.searchBookByCategory();
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
                            System.out.println("Welcome to Student function");
                            System.out.println("1. Display Book");
                            System.out.println("2. Search Book By ISBN");
                            System.out.println("3. Search Book By Name");
                            System.out.println("4. Search Book By Author");
                            System.out.println("5. Search Book By Category");
                            System.out.println("6. Borrow Book");
                            System.out.println("7. Return Book");
                            System.out.print("Please Select Option (1-7) : ");
                            int option4 = scanner.nextInt();
                            scanner.nextLine();
                            switch (option4) {
                                case 1:
                                    student.displayBook();
                                break;
                                case 2:
                                    student.searchBookByISBN();
                                break;
                                case 3:
                                    student.searchBookByName();
                                break;
                                case 4:
                                    student.searchBookByAuthor();
                                break;
                                case 5:
                                    student.searchBookByCategory();
                                break;
                                case 6:
                                    student.Borrow();
                                    student.DisplayInvoice();
                                break;
                                case 7:
                                    student.Returned();
                                break;
                            }
                        break;

                        case 2:
                            System.out.println("Please Login as Student");
                            student.login();
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
                                    student.searchBookByISBN();
                                break;
                                case 3:
                                    student.searchBookByName();
                                break;
                                case 4:
                                    student.searchBookByAuthor();
                                break;
                                case 5:
                                    student.searchBookByCategory();
                                break;
                                case 6:
                                    student.Borrow();
                                break;
                                case 7:
                                    student.Returned();
                                break;
                            }
                        break;
                    }
                break;
                case 4 : 
                    System.exit(0);
                break;
            }
        scanner.close();
    } 
}
