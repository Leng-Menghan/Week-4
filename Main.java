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
