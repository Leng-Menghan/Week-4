package Library;

import java.util.Scanner;
import Exception.LimitOptionException;
import Exception.NumberOnlyException;
import Exception.InputException;

public class Main {
    public static void main(String[] args) {
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
                    LimitOptionException test = new LimitOptionException(Integer.parseInt(choice), 3, 1);
                    break;
                } catch (LimitOptionException e) {
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
                                System.out.println("7. Search Book");
                                System.out.println("8. Display Borrow");
                                System.out.println("9. Display Return");
                                System.out.println("10. Search Student By ID");
                                System.out.println("11. Search Librarian By ID");
                                System.out.println("12. Logout");
                                String option;
                                while (true) {
                                    try {
                                        System.out.print("Please Select Option (1-12) : ");
                                        option = scanner.nextLine();
                                        NumberOnlyException test = new NumberOnlyException(option, "^[0-9]+$");
                                        int optionInt = Integer.parseInt(option);
                                        LimitOptionException test1 = new LimitOptionException(optionInt, 12, 1);
                                        break;
                                    } catch (LimitOptionException e) {
                                        System.out.println(e.getMessage());
                                    } catch (NumberOnlyException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                switch (Integer.parseInt(option)) {
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
                                        System.out.println("How many books do you want to add?");
                                        System.out.print("Enter quantity: ");
                                        int newQuantity = scanner.nextInt();
                                        scanner.nextLine();
                                        for (int i = 0; i < newQuantity; i++) {
                                            admin.addBook();
                                        }
                                        break;
                                    case 5:
                                        admin.addBookQuantityByID();
                                        break;
                                    case 6:
                                        admin.deleteBook();
                                        break;
                                    case 7:
                                        admin.searchBook();
                                        break;
                                    case 8:
                                        admin.displayBorrow();                
                                        break;
                                    case 9:
                                        admin.displayReturn();
                                        break;
                                    case 10:
                                        admin.searchStudentByID(); 
                                        break;
                                    case 11:
                                        admin.searchLibrarianByID();
                                        break;
                                }
                                if(Integer.parseInt(option) == 12){
                                    loginAttempts = 4;
                                    break;
                                }
                                String exit;
                                while (true) {
                                    try {
                                        System.out.print("Exit (Y/y) : ");
                                        exit = scanner.nextLine();
                                        InputException test = new InputException(exit, "^[Yy]", "Allowed input only Y/y");
                                        break;
                                    } catch (InputException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                if (exit.equals("Y") || exit.equals("y")) {
                                    continue;
                                }
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
                            LimitOptionException test = new LimitOptionException(Integer.parseInt(option1), 3, 1);
                            break;
                        } catch (LimitOptionException e) {
                            System.out.println(e.getMessage());
                        } catch (NumberOnlyException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    int loginAttemptsLibrarian = 0;
                    switch (Integer.parseInt(option1)) {
                        case 1:
                            librarian.register("L");
                            break;
                        case 2:
                            System.out.println("Please Login as Librarian");
                            while (loginAttemptsLibrarian < 3) {
                                if (librarian.login("L")) {
                                    break;
                                }else {
                                    System.out.println("Invalid Password");
                                    loginAttemptsLibrarian++;
                                }
                                if (loginAttemptsLibrarian == 3) {
                                    System.out.println("You have reached the maximum number of login attempts. Please try again later.");
                                    break;
                                }
                            }
                    }
                    if(loginAttemptsLibrarian==3 || Integer.parseInt(option1)==3){
                        break;
                    }
                    while (true) {
                        System.out.println("Welcome to Librarian function");
                        System.out.println("1. Display Student");
                        System.out.println("2. Display Book");
                        System.out.println("3. Add Book");
                        System.out.println("4. Add Book Quantity By ISBN");
                        System.out.println("5. Remove Book By ISBN");
                        System.out.println("6. Search Book");
                        System.out.println("7. Search Student By ID");
                        System.out.println("8. Change Password");
                        System.out.println("9. Change Name");
                        System.out.println("10. Log out");
                        String option2;
                        while (true) {
                            try {
                                System.out.print("Please Select Option (1-10) : ");
                                option2 = scanner.nextLine();
                                NumberOnlyException test = new NumberOnlyException(option2, "^[0-9]+$");
                                int optionInt = Integer.parseInt(option2);
                                LimitOptionException test1 = new LimitOptionException(optionInt, 10, 1);
                                break;
                            } catch (LimitOptionException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberOnlyException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        switch (Integer.parseInt(option2)) {
                            case 1:
                                librarian.displayStudent();
                                break;
                            case 2:
                                librarian.displayBook();
                                break;
                            case 3:
                                System.out.println("How many books do you want to add?");
                                System.out.print("Enter quantity: ");
                                int quantity = scanner.nextInt();
                                scanner.nextLine();
                                for (int i = 0; i < quantity; i++) {
                                    librarian.addBook();
                                }
                                break;
                            case 4:
                                librarian.addBookQuantityByID();
                                break;
                            case 5:
                                librarian.deleteBook();
                                break;
                            case 6:
                                librarian.searchBook();
                                break;
                            case 7:
                                librarian.searchStudentByID();
                                break;
                            case 8:
                                librarian.changePassword();
                                break;
                            case 9:
                                librarian.changeName();
                                break;
                        }
                        if (Integer.parseInt(option2) == 10) break;
                        String exit;
                        while (true) {
                            try {
                                System.out.print("Exit (Y/y) : ");
                                exit = scanner.nextLine();
                                InputException test = new InputException(exit, "^[Yy]", "Allowed input only Y/y");
                                break;
                            } catch (InputException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (exit.equals("Y") || exit.equals("y")) {
                            continue;
                        }
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
                            LimitOptionException test2 = new LimitOptionException(Integer.parseInt(option2), 3, 1);
                            break;
                        } catch (NumberOnlyException e) {
                            System.out.println(e.getMessage());
                        } catch (LimitOptionException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    int loginAttemptsStudent = 0;
                    switch (Integer.parseInt(option2)) {
                        case 1:
                            student.register("S");
                            break;
                        case 2:
                            System.out.println("Please Login as Student");
                            while (loginAttemptsStudent < 3) {
                                if (student.login("S")) {
                                    break;
                                }else {
                                    System.out.println("Invalid Password");
                                    loginAttemptsStudent++;
                                }
                                if (loginAttemptsStudent == 3) {
                                    System.out.println("You have reached the maximum number of login attempts. Please try again later.");
                                    break;
                                }
                            }
                    }
                    if(loginAttemptsStudent==3 || Integer.parseInt(option2)==3){
                        break;
                    }
                    while (true) {
                        System.out.println("Welcome to Student function");
                        System.out.println("Your ID is: " + student.ID);
                        System.out.println("1. Display Book");
                        System.out.println("2. Search Book");
                        System.out.println("3. Borrow Book");
                        System.out.println("4. Return Book");
                        System.out.println("5. Change Password");
                        System.out.println("6. Change Name");
                        System.out.println("7. Log out");
                        String option4;
                        while (true) {
                            try {
                                System.out.print("Please Select Option (1-5) : ");
                                option4 = scanner.nextLine();
                                NumberOnlyException test = new NumberOnlyException(option4, "^[0-9]+$");
                                LimitOptionException test2 = new LimitOptionException(Integer.parseInt(option4), 7, 1);
                                break;
                            } catch (NumberOnlyException e) {
                                System.out.println(e.getMessage());
                            } catch (LimitOptionException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        switch (Integer.parseInt(option4)) {
                            case 1:
                                student.displayBook();
                                break;
                            case 2:
                                student.searchBook();
                                break;
                            case 3:
                                student.Borrow();
                                student.DisplayInvoice();
                                break;
                            case 4:
                                student.Returned();
                                break;
                            case 5:
                                student.changePassword();
                                break;
                            case 6:
                                student.changeName();
                                break;
                        }
                        if (Integer.parseInt(option4) == 7) break;
                        String exit;
                        while (true) {
                            try {
                                System.out.print("Exit (Y/y) : ");
                                exit = scanner.nextLine();
                                InputException test = new InputException(exit, "^[Yy]", "Allowed input only Y/y");
                                break;
                            } catch (InputException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (exit.equals("Y") || exit.equals("y")) {
                            continue;
                        }
                    }
                break;
            }
            if(Integer.parseInt(choice)==4){
                break;
            }
        }
        scanner.close();
    }
    
    
}
