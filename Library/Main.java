package Library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Exception.LimitOptionAdminException;
import Exception.LimitOptionAuthentication;
import Exception.LimitOptionLibrarianException;
import Exception.LimitOptionStudentException;
import Exception.LimitOptionUserException;
import Exception.NumberOnlyException;
import Exception.ExitException;
public class Main {
    public static void main(String[] args) {
    String selectQuery = "SELECT * FROM User";
    ResultSet rs = MySQLConnection.executeQuery(selectQuery);
    try {
        while (rs != null && rs.next()) {
            String id = rs.getString("ID");
            String name = rs.getString("Name");
            String address = rs.getString("Address");
            String phoneNumber = rs.getString("PhoneNumber");
            String email = rs.getString("Email");
            String password = rs.getString("Password");
            if(id.equals("S")){
                int inc = 0;
                Database.UserList.add(new Student("S"+ ++inc,name, address, phoneNumber, email, password));
            }else{
                int inc = 0;
                Database.UserList.add(new Librarian("L"+ ++inc,name, address, phoneNumber, email, password));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    String selectQuery2 = "SELECT * FROM Book";
    ResultSet rs2 = MySQLConnection.executeQuery(selectQuery2);
    try {
        while (rs2 != null && rs2.next()) {
            String ISBN = rs2.getString("ISBN");
            String category = rs2.getString("Category");
            String bookname = rs2.getString("Name");
            String author = rs2.getString("Author");
            double price = rs2.getDouble("Price");
            int quantity = rs2.getInt("Qty");
            String publisher = rs2.getString("Publisher");
            int inc = 0;
            Database.bookList.add(new Book(++inc,ISBN, category, bookname, author, price, quantity, publisher));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    MySQLConnection.closeConnection();

        Admin admin = new Admin();
        User student = new Student();
        User librarian = new Librarian();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("#---------------------------------------------------#");
            System.out.println("|                                                   |");
            System.out.println("#        Welcome to Library Management System       #");
            System.out.println("|                                                   |");
            System.out.println("#---------------------------------------------------#");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Admin");
            System.out.println("2. Librarian");
            System.out.println("3. Student");
            System.out.println("----------------------------------------------------");
            String choice;
            while (true) {
                try {
                    System.out.print("Please Select Option (1-3) : ");
                    choice = scanner.nextLine();
                    NumberOnlyException test1 = new NumberOnlyException(choice, "^[0-9]+$");
                    LimitOptionUserException test = new LimitOptionUserException(Integer.parseInt(choice));
                    break;
                } catch (LimitOptionUserException e) {
                    System.out.println(e.getMessage());
                } catch (NumberOnlyException e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (Integer.parseInt(choice)) {
                case 1:
                    int loginAttempts = 0;
                    System.out.println("Please Login as Admin");
                    while (loginAttempts < 3) {
                        if (admin.adminlogin()) {
                            while (true) {
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
                                System.out.println("11. Logout");
                                String option;
                                while (true) {
                                    try {
                                        System.out.print("Please Select Option (1-11) : ");
                                        option = scanner.nextLine();
                                        NumberOnlyException test = new NumberOnlyException(option, "^[0-9]+$");
                                        int optionInt = Integer.parseInt(option);
                                        LimitOptionAdminException test1 = new LimitOptionAdminException(optionInt);
                                        break;
                                    } catch (LimitOptionAdminException e) {
                                        System.out.println(e.getMessage());
                                    } catch (NumberOnlyException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                switch (Integer.parseInt(option)) {
                                    case 1:
                                        admin.displayLibrarain();
                                        String exit;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit = scanner.nextLine();
                                                ExitException test = new ExitException(exit, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit.equals("Y") || exit.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 2:
                                        admin.displayStudent();
                                        String exit1;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit1 = scanner.nextLine();
                                                ExitException test = new ExitException(exit1, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit1.equals("Y") || exit1.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 3:
                                        admin.displayBook();
                                        String exit2;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit2 = scanner.nextLine();
                                                ExitException test = new ExitException(exit2, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit2.equals("Y") || exit2.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 4:
                                        System.out.println("How many books do you want to add?");
                                        System.out.print("Enter quantity: ");
                                        int newQuantity = scanner.nextInt();
                                        scanner.nextLine();
                                        for (int i = 0; i < newQuantity; i++) {
                                            admin.addBook();
                                        }
                                        String exit3;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit3 = scanner.nextLine();
                                                ExitException test = new ExitException(exit3, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit3.equals("Y") || exit3.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 5:
                                        admin.addBookQuantityByISBN();
                                        String exit4;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit4 = scanner.nextLine();
                                                ExitException test = new ExitException(exit4, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit4.equals("Y") || exit4.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 6:
                                        admin.deleteBook();
                                        String exit5;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit5 = scanner.nextLine();
                                                ExitException test = new ExitException(exit5, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit5.equals("Y") || exit5.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 7:
                                        admin.searchBookByISBN();
                                        String exit6;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit6 = scanner.nextLine();
                                                ExitException test = new ExitException(exit6, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit6.equals("Y") || exit6.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 8:
                                        admin.searchBookByName();
                                        String exit7;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit7 = scanner.nextLine();
                                                ExitException test = new ExitException(exit7, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit7.equals("Y") || exit7.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 9:
                                        admin.searchBookByAuthor();
                                        String exit8;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit8 = scanner.nextLine();
                                                ExitException test = new ExitException(exit8, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit8.equals("Y") || exit8.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 10:
                                        admin.searchBookByCategory();
                                        String exit9;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit9 = scanner.nextLine();
                                                ExitException test = new ExitException(exit9, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit9.equals("Y") || exit9.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 11:
                                        loginAttempts = 4;
                                        break;
                                }
                                break;
                            }
                        } else {
                            System.out.println("Invalid Username or Password");
                            loginAttempts++;
                        }
                        if (loginAttempts == 3) {
                            System.out.println(
                                    "You have reached the maximum number of login attempts. Please try again later.");
                            break;
                        }
                        if (loginAttempts == 4) {
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Librarian");
                    System.out.println("1. Register Librarian");
                    System.out.println("2. Log in Librarian");
                    System.out.println("3. Exit");
                    String option1;
                    while (true) {
                        try {
                            System.out.print("Please Select Option (1-3) : ");
                            option1 = scanner.nextLine();
                            NumberOnlyException test1 = new NumberOnlyException(option1, "^[0-9]+$");
                            LimitOptionAuthentication test = new LimitOptionAuthentication(Integer.parseInt(option1));
                            break;
                        } catch (LimitOptionAuthentication e) {
                            System.out.println(e.getMessage());
                        } catch (NumberOnlyException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    switch (Integer.parseInt(option1)) {
                        case 1:
                            librarian.register();
                            while (true) {
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
                                System.out.println("10. Log out");
                                String option2;
                                while (true) {
                                    try {
                                        System.out.print("Please Select Option (1-10) : ");
                                        option2 = scanner.nextLine();
                                        NumberOnlyException test = new NumberOnlyException(option2, "^[0-9]+$");
                                        int optionInt = Integer.parseInt(option2);
                                        LimitOptionLibrarianException test1 = new LimitOptionLibrarianException(optionInt);
                                        break;
                                    } catch (LimitOptionLibrarianException e) {
                                        System.out.println(e.getMessage());
                                    } catch (NumberOnlyException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                switch (Integer.parseInt(option2)) {
                                    case 1:
                                        librarian.displayStudent();
                                        String exit;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit = scanner.nextLine();
                                                ExitException test = new ExitException(exit, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit.equals("Y") || exit.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 2:
                                        librarian.displayBook();
                                        String exit1;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit1 = scanner.nextLine();
                                                ExitException test = new ExitException(exit1, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit1.equals("Y") || exit1.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 3:
                                        System.out.println("How many books do you want to add?");
                                        System.out.print("Enter quantity: ");
                                        int quantity = scanner.nextInt();
                                        scanner.nextLine();
                                        for (int i = 0; i < quantity; i++) {
                                            librarian.addBook();
                                        }
                                        String exit2;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit2 = scanner.nextLine();
                                                ExitException test = new ExitException(exit2, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit2.equals("Y") || exit2.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 4:
                                        librarian.addBookQuantityByISBN();
                                        String exit3;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit3 = scanner.nextLine();
                                                ExitException test = new ExitException(exit3, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit3.equals("Y") || exit3.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 5:
                                        librarian.deleteBook();
                                        String exit4;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit4 = scanner.nextLine();
                                                ExitException test = new ExitException(exit4, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit4.equals("Y") || exit4.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 6:
                                        librarian.searchBookByISBN();
                                        String exit5;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit5 = scanner.nextLine();
                                                ExitException test = new ExitException(exit5, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit5.equals("Y") || exit5.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 7:
                                        librarian.searchBookByName();
                                        String exit6;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit6 = scanner.nextLine();
                                                ExitException test = new ExitException(exit6, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit6.equals("Y") || exit6.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 8:
                                        librarian.searchBookByAuthor();
                                        String exit7;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit7 = scanner.nextLine();
                                                ExitException test = new ExitException(exit7, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit7.equals("Y") || exit7.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 9:
                                        librarian.searchBookByCategory();
                                        String exit8;
                                        while (true) {
                                            try {
                                                System.out.print("Exit (Y/y) : ");
                                                exit8 = scanner.nextLine();
                                                ExitException test = new ExitException(exit8, "^[Yy]");
                                                break;
                                            } catch (ExitException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        if (exit8.equals("Y") || exit8.equals("y")) {
                                            continue;
                                        }
                                        break;
                                    case 10:
                                        break;
                                }
                                break;
                            }

                            break;
                        case 2:
                            System.out.println("Please Login as Librarian");
                            int loginAttemptsLibrarian = 0;
                            while (loginAttemptsLibrarian < 3) {
                                if (librarian.login()) {
                                    while (true) {
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
                                        System.out.println("10. Log out");
                                        String option2;
                                        while (true) {
                                            try {
                                                System.out.print("Please Select Option (1-10) : ");
                                                option2 = scanner.nextLine();
                                                NumberOnlyException test = new NumberOnlyException(option2, "^[0-9]+$");
                                                int optionInt = Integer.parseInt(option2);
                                                LimitOptionLibrarianException test1 = new LimitOptionLibrarianException(optionInt);
                                                break;
                                            } catch (LimitOptionLibrarianException e) {
                                                System.out.println(e.getMessage());
                                            } catch (NumberOnlyException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        switch (Integer.parseInt(option2)) {
                                            case 1:
                                                librarian.displayStudent();
                                                String exit;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit = scanner.nextLine();
                                                        ExitException test = new ExitException(exit, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit.equals("Y") || exit.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 2:
                                                librarian.displayBook();
                                                String exit1;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit1 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit1, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit1.equals("Y") || exit1.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 3:
                                                System.out.println("How many books do you want to add?");
                                                System.out.print("Enter quantity: ");
                                                int quantity = scanner.nextInt();
                                                scanner.nextLine();
                                                for (int i = 0; i < quantity; i++) {
                                                    librarian.addBook();
                                                }
                                                String exit2;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit2 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit2, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit2.equals("Y") || exit2.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 4:
                                                librarian.addBookQuantityByISBN();
                                                String exit3;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit3 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit3, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit3.equals("Y") || exit3.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 5:
                                                librarian.deleteBook();
                                                String exit4;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit4 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit4, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit4.equals("Y") || exit4.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 6:
                                                librarian.searchBookByISBN();
                                                String exit5;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit5 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit5, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit5.equals("Y") || exit5.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 7:
                                                librarian.searchBookByName();
                                                String exit6;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit6 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit6, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit6.equals("Y") || exit6.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 8:
                                                librarian.searchBookByAuthor();
                                                String exit7;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit7 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit7, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit7.equals("Y") || exit7.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 9:
                                                librarian.searchBookByCategory();
                                                String exit8;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit8 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit8, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit8.equals("Y") || exit8.equals("y")) {
                                                    continue;
                                                }
                                                break;
                                            case 10:
                                                loginAttemptsLibrarian = 4;
                                                break;
                                        }
                                        break;
                                    }
                                } else {
                                    System.out.println("Invalid Password");
                                    loginAttemptsLibrarian++;
                                }
                                if (loginAttemptsLibrarian == 3) {
                                    System.out.println(
                                            "You have reached the maximum number of login attempts. Please try again later.");
                                    break;
                                }
                            }

                        case 3:
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Student");
                    System.out.println("1. Register Student");
                    System.out.println("2. Log in Student");
                    System.out.println("3. Exit");
                    String option2;
                    while (true) {
                        try {
                            System.out.print("Please Select Option (1-3) : ");
                            option2 = scanner.nextLine();
                            NumberOnlyException test = new NumberOnlyException(option2, "^[0-9]+$");
                            LimitOptionAuthentication test2 = new LimitOptionAuthentication(Integer.parseInt(option2));
                            break;
                        } catch (NumberOnlyException e) {
                            System.out.println(e.getMessage());
                        } catch (LimitOptionAuthentication e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    switch (Integer.parseInt(option2)) {
                        case 1:
                            student.register();
                            while (true) {
                                System.out.println("Welcome to Student function");
                                System.out.println("1. Display Book");
                                System.out.println("2. Search Book By ISBN");
                                System.out.println("3. Search Book By Name");
                                System.out.println("4. Search Book By Author");
                                System.out.println("5. Search Book By Category");
                                System.out.println("6. Borrow Book");
                                System.out.println("7. Return Book");
                                System.out.println("8. Log out");
                                String option4;
                                while (true) {
                                    try {
                                        System.out.print("Please Select Option (1-8) : ");
                                        option4 = scanner.nextLine();
                                        NumberOnlyException test = new NumberOnlyException(option4, "^[0-9]+$");
                                        LimitOptionStudentException test2 = new LimitOptionStudentException(
                                                Integer.parseInt(option4));
                                        break;
                                    } catch (NumberOnlyException e) {
                                        System.out.println(e.getMessage());
                                    } catch (LimitOptionStudentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                switch (Integer.parseInt(option4)) {
                                    case 1:
                                        student.displayBook();
                                        String exit;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit = scanner.nextLine();
                                                        ExitException test = new ExitException(exit, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit.equals("Y") || exit.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 2:
                                        student.searchBookByISBN();
                                        String exit1;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit1 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit1, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit1.equals("Y") || exit1.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 3:
                                        student.searchBookByName();
                                        String exit2;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit2 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit2, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit2.equals("Y") || exit2.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 4:
                                        student.searchBookByAuthor();
                                        String exit3;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit3 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit3, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit3.equals("Y") || exit3.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 5:
                                        student.searchBookByCategory();
                                        String exit4;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit4 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit4, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit4.equals("Y") || exit4.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 6:
                                        student.Borrow();
                                        student.DisplayInvoice();
                                        String exit5;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit5 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit5, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit5.equals("Y") || exit5.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 7:
                                        student.Returned();
                                        String exit6;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit6 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit6, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit6.equals("Y") || exit6.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 8:

                                        break;
                                }
                                break;
                            }
                            break;

                        case 2:
                            System.out.println("Please Login as Student");
                            int loginAttemptsStudent = 0;
                            while (loginAttemptsStudent < 3) {
                                if (student.login()) {
                                    while (true) {
                                        System.out.println("Welcome to Student function");
                                        System.out.println("1. Display Book");
                                        System.out.println("2. Search Book By ISBN");
                                        System.out.println("3. Search Book By Name");
                                        System.out.println("4. Search Book By Author");
                                        System.out.println("5. Search Book By Category");
                                        System.out.println("6. Borrow Book");
                                        System.out.println("7. Return Book");
                                        System.out.println("8. Log out");
                                        String option4;
                                        while (true) {
                                            try {
                                                System.out.print("Please Select Option (1-8) : ");
                                                option4 = scanner.nextLine();
                                                NumberOnlyException test = new NumberOnlyException(option4, "^[0-9]+$");
                                                LimitOptionStudentException test2 = new LimitOptionStudentException(
                                                        Integer.parseInt(option4));
                                                break;
                                            } catch (NumberOnlyException e) {
                                                System.out.println(e.getMessage());
                                            } catch (LimitOptionStudentException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        switch (Integer.parseInt(option4)) {
                                            case 1:
                                        student.displayBook();
                                        String exit;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit = scanner.nextLine();
                                                        ExitException test = new ExitException(exit, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit.equals("Y") || exit.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 2:
                                        student.searchBookByISBN();
                                        String exit1;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit1 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit1, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit1.equals("Y") || exit1.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 3:
                                        student.searchBookByName();
                                        String exit2;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit2 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit2, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit2.equals("Y") || exit2.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 4:
                                        student.searchBookByAuthor();
                                        String exit3;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit3 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit3, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit3.equals("Y") || exit3.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 5:
                                        student.searchBookByCategory();
                                        String exit4;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit4 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit4, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit4.equals("Y") || exit4.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 6:
                                        student.Borrow();
                                        student.DisplayInvoice();
                                        String exit5;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit5 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit5, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit5.equals("Y") || exit5.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                    case 7:
                                        student.Returned();
                                        String exit6;
                                                while (true) {
                                                    try {
                                                        System.out.print("Exit (Y/y) : ");
                                                        exit6 = scanner.nextLine();
                                                        ExitException test = new ExitException(exit6, "^[Yy]");
                                                        break;
                                                    } catch (ExitException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                if (exit6.equals("Y") || exit6.equals("y")) {
                                                    continue;
                                                }
                                        break;
                                            case 8:
                                                loginAttemptsStudent = 4;
                                                break;
                                        }
                                        break;
                                    }
                                } else {
                                    System.out.println("Invalid Password");
                                    loginAttemptsStudent++;
                                }
                                if (loginAttemptsStudent == 3) {
                                    System.out.println(
                                            "You have reached the maximum number of login attempts. Please try again later.");
                                    break;
                                }
                                if (loginAttemptsStudent == 4) {
                                    break;
                                }
                            }
                            break;

                        case 3:
                            break;
                    }

            }
        }
    }
}
