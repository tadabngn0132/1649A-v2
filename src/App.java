import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        User user = Login();
        if (user == null) {
            System.out.println("Login failed. Exiting application.");
            return;
        } else if (user instanceof Customer) {
            customerMenu((Customer) user);
        } else if (user instanceof Admin) {
            adminMenu((Admin) user);
        }
    }

    private static void customerMenu(Customer customer) {
        System.out.println("Welcome, " + customer.getFullName() + "!");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("====Customer Menu====");
            System.out.println("1. View books");
            System.out.println("2. Add books to cart");
            System.out.println("3. Check out");
            System.out.println("4. View order history");
            System.out.println("0. Logout");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Displaying books...");
                    ArrayList<Book> books = BookManager.getBooks();

                    subMenu(customer, books);
                    
                    break;
                
                case 2:
                    break;

                case 3:
                    break;
                
                case 4:
                    break;

                case 0:
                    break;

                default:
                    break;
            }
        } while (choice != 0);
    }

    private static void subMenu(Customer customer, ArrayList<Book> books) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("====View Books====");
            System.out.println("1. Search book by author");
            System.out.println("2. Search book by title");
            System.out.println("3. Sort book by price");
            System.out.println("4. Sort book by title");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
            
                case 2:
                    break;
            
                case 3:
                    break;

                case 4:
                    break;

                case 0:
                    customerMenu(customer);
                    break;
                
                default:
                    break;
            }
        } while (choice != 0);
    }

    private static void userManagementMenu(Admin admin, ArrayList<User> users) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("====Manage Users====");
            System.out.println("1. View all users");
            System.out.println("2. Add a new user");
            System.out.println("3. Delete a user");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                
                case 2:
                    break;

                case 3:
                    break;
                
                case 0:
                    break;

                default:
                    break;
            }
        } while (choice != 0);
    }

    private static void bookManagementMenu(Admin admin, ArrayList<Book> books) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("====Manage Books====");
            System.out.println("1. View all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                
                case 2:
                    break;

                case 3:
                    break;
                
                case 4:
                    break;

                case 0:
                    break;

                default:
                    break;
            }
        } while (choice != 0);
    }

    private static void adminMenu(Admin admin) {
        System.out.println("Welcome, " + admin.getFullName() + "!");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("====Admin Menu====");
            System.out.println("1. Manage users");
            System.out.println("2. Manage books");
            System.out.println("3. Process order");
            System.out.println("4. Delivery");
            System.out.println("0. Logout");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ArrayList<User> users = UserManager.getUsers();
                    userManagementMenu(admin, users);
                    break;
                
                case 2:
                    ArrayList<Book> books = BookManager.getBooks();
                    bookManagementMenu(admin, books);
                    break;

                case 3:
                    break;
                
                case 4:
                    break;

                case 0:
                    break;
                
                default:
                    break;
            }
        } while (choice != 0);
    }

    private static User Login() {
        User user = new Admin(0, "Nguyen Ba Dat", "btad", "1234", "datngba2310@gmail.com", "Admin");
        return user;
    }
}
