import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        User user = Login();
        if (user == null) {
            System.out.println("Login failed. Exiting application.");
            return;
        }
        else if (user instanceof Customer) {
            customerMenu(user);
        }else if (user instanceof Employee) {
            System.out.println("Welcome, " + user.getName() + "!");
        } else if (user instanceof Admin) {
            System.out.println("Unknown user type.");
        }
    }

    public static void customerMenu(User user) {
        System.out.println("Welcome, " + user.getName() + "!");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("1. View books");
            System.out.println("2. Add books to cart");
            System.out.println("3. Check out");
            System.out.println("4. View order history");
            System.out.println("0. Logout");
            System.out.println("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Displaying products...");
                    ArrayList<Product> products = ProductManager.getProducts();
                    user.Print(products);

                    subMenu(user, products);
                    
                    break;
                
                case 2:
                    break;

                case 3:
                    break;
                
                case 4:
                    break;

                case 5:
                    break;

                default:
                    break;
            }
        } while (choice != 0);
    }

    private static void subMenu(User user, ArrayList<Product> products) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. Search book by author");
            System.out.println("2. Search book by title");
            System.out.println("3. Sort book by price");
            System.out.println("4. Sort book by title");
            System.out.println("0. Back to main menu");
            System.out.println("Select an option: ");
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

                case 5:
                    break;
                
                default:
                    break;
            }
        } while (choice != 0);
    }

    public static void employeeMenu(User user) {
        System.out.println("Welcome, " + user.getName() + "!");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("1. View all books");
            System.out.println("2. Process order");
            System.out.println("3. Delivery");
            System.out.println("0. Logout");
            System.out.println("Select an option: ");
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

                default:
                    break;
            }
        } while (choice != 0);
    }

    private static User Login() {
        return null;
    }
}
