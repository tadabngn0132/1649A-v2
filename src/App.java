import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        User user = Login();
        if (user == null) {
            System.out.println("Login failed. Exiting application.");
            return;
        } else {
            customerMenu(user);
        }
    }

    private static void customerMenu(User user) {
        System.out.println();
        System.out.println("Welcome, " + user.getFullName() + "!");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("====Customer Menu====");
            System.out.println("1. View books");
            System.out.println("2. Add books to cart");
            System.out.println("3. View cart");
            System.out.println("4. Check out");
            System.out.println("5. View order history");
            System.out.println("0. Logout");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            boolean addingBooks = true;
            ArrayList<Book> sortedBooks;
            InsertionSort sorter = new InsertionSort();
            switch (choice) {
                case 1:
                    System.out.println("Displaying books...");
                    ArrayList<Book> books = BookManager.getBooks();

                    sortedBooks = sorter.sortById(books, true);
                    
                    if (books != null && !books.isEmpty()) {
                        user.Print(sortedBooks);
                    } else {
                        System.out.println("There are no books to display.");
                    }

                    subMenu(user, books);
                    break;
                
                case 2:
                    System.out.println("Add Books to Cart...");

                    if (user.getCart() == null) {
                        user.setCart(new Cart());
                    }

                    while (addingBooks) {
                        System.out.println();
                        System.out.println("Available books:");
                        @SuppressWarnings("resource")
                        Scanner scanner1 = new Scanner(System.in);
                        ArrayList<Book> availableBooks = BookManager.getBooks();
                        
                        if (availableBooks != null && !availableBooks.isEmpty()) {
                            user.Print(availableBooks);
                        } else {
                            System.out.println("There are no books to display.");
                        }

                        System.out.println();
                        System.out.print("Enter book title to add to cart: ");
                        int bookId = scanner.nextInt();

                        if (bookId <= 0) {
                            addingBooks = false;
                        } else {
                            Book selectedBook = null;
                            for (Book book : availableBooks) {
                                if (book.getId() == bookId) {
                                    selectedBook = book;
                                    break;
                                }
                            }

                            if (selectedBook != null) {
                                System.out.print("Enter quantity: ");
                                int quantity = scanner1.nextInt();

                                if (quantity <= selectedBook.getQuantity()) {
                                    Book cartItem = new Book(
                                        selectedBook.getId(), 
                                        selectedBook.getTitle(), 
                                        selectedBook.getAuthor(), 
                                        selectedBook.getPrice(), 
                                        quantity
                                    );

                                    user.getCart().addItem(cartItem);
                                    System.out.println("Add to cart successfully");
                                } else {
                                    System.out.println("There are only " + quantity + " books in stock.");
                                }
                            } else {
                                System.out.println("Not found!");
                            }

                            System.out.print("Do you want to add more (yes/no): ");
                            @SuppressWarnings("resource")
                            Scanner scanner2 = new Scanner(System.in);

                            String answer = scanner2.nextLine();

                            String lowerCaseAnswer = answer.toLowerCase();

                            if (lowerCaseAnswer.compareTo("yes") == 0 || lowerCaseAnswer.compareTo("y") == 0) {
                                addingBooks = true;
                            } else {
                                addingBooks = false;
                            }
                        }
                    }
                    break;

                case 3:
                    if (user.getCart().isEmpty()) {
                        System.out.println("Cart is empty");
                    } else {
                        user.PrintCart(user);
                    }
                    break;
                
                case 4:
                    Scanner scanner1 = new Scanner(System.in);
                    if (user.getCart() == null || user.getCart().isEmpty()) {
                        System.out.println("Cart is empty! Please add books first.");
                        break;
                    }
                    
                    System.out.println();
                    System.out.println("===Checkout===");
                    System.out.println("Items in your cart:");
                    
                    user.PrintCart(user);
                    
                    System.out.print("Enter shipping address: ");
                    String shippingAddress = scanner1.nextLine();
                    
                    int nextOrderId = 0;
                    // Tạo đơn hàng mới
                    Order newOrder = new Order(nextOrderId++, user.getFullName(), shippingAddress);
                    newOrder.setBooks(new ArrayList<>(user.getCart().getItems()));
                    
                    // Thêm đơn hàng vào hệ thống
                    OrderManager.getPendingOrders().enqueue(newOrder);
                    OrderManager.getAllOrders().add(newOrder);
                    
                    System.out.println("Order created successfully! Order ID: " + newOrder.getId());
                    
                    // Cập nhật số lượng sách trong kho
                    for (Book cartItem : user.getCart().getItems()) {
                        for (Book inventoryBook : BookManager.getBooks()) {
                            if (inventoryBook.getId() == cartItem.getId()) {
                                inventoryBook.setQuantity(inventoryBook.getQuantity() - cartItem.getQuantity());
                                break;
                            }
                        }
                    }
                    
                    // Xóa giỏ hàng sau khi đặt hàng
                    user.getCart().clear();
                    break;

                case 5:
                    ArrayList<Order> orderHistory = OrderManager.getOrdersByCustomer(user.getFullName());
                    user.PrintOrder(orderHistory);
                    
                    if (!orderHistory.isEmpty()) {
                        System.out.print("\nEnter order ID to view details (0 to cancel): ");
                        int orderId = scanner.nextInt();
                        
                        if (orderId > 0) {
                            // Tìm đơn hàng theo ID
                            Order selectedOrder = null;
                            for (Order order : orderHistory) {
                                if (order.getId() == orderId) {
                                    selectedOrder = order;
                                    break;
                                }
                            }
                            
                            if (selectedOrder != null) {
                                // In chi tiết đơn hàng bằng phương thức PrintOrderDetail
                                user.PrintOrderDetail(selectedOrder);
                            } else {
                                System.out.println("Order not found or you don't have permission to view it.");
                            }
                        }
                    }

                    break;

                case 0:
                    break;

                default:
                    break;
            }
        } while (choice != 0);
    }

    private static void subMenu(User user, ArrayList<Book> books) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println();
            System.out.println("1. Search book by author");
            System.out.println("2. Search book by title");
            System.out.println("3. Sort book by price");
            System.out.println("4. Sort book by title");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            ArrayList<Book> sortedBooks;
            ArrayList<Book> searchedBooks;
            int idx = 0;
            BinarySearch searcher = new BinarySearch();
            InsertionSort sorter = new InsertionSort();
            switch (choice) {
                case 1:
                    Scanner scanner1 = new Scanner(System.in);

                    System.out.print("Enter author name to search: ");
                    String author = scanner1.nextLine();

                    sortedBooks = sorter.sortByAuthor(books, true);

                    // idx = searcher.searchByAuthor(sortedBooks, author);

                    // if (idx != -1) {
                    //     System.out.println("Found: " + sortedBooks.get(idx).getTitle() + ", author: " + sortedBooks.get(idx).getAuthor()
                    //                     + ", price: " + sortedBooks.get(idx).getPrice());
                    // } else {
                    //     System.out.println("Not found any book of " + author + "!");
                    // }

                    searchedBooks = searcher.searchBooksByAuthorName(sortedBooks, author);

                    if (searchedBooks.size() != 0) {
                        user.Print(searchedBooks);
                    } else {
                        System.out.println("Not found any book of " + author + "!");
                    }

                    break;
            
                case 2:
                    Scanner scanner2 = new Scanner(System.in);

                    System.out.print("Enter book title to search: ");
                    String title = scanner2.nextLine();

                    sortedBooks = sorter.sortByTitle(books, true);

                    idx = searcher.searchByTitle(sortedBooks, title);

                    if (idx != -1) {
                        System.out.println("Found: " + sortedBooks.get(idx).getTitle() + ", author: " + sortedBooks.get(idx).getAuthor()
                                        + ", price: $" + sortedBooks.get(idx).getPrice());
                    } else {
                        System.out.println("Not found any book with the title " + title + "!");
                    }
                    break;
            
                case 3:
                    sortedBooks = sorter.sortByPrice(books, true);
                    user.Print(sortedBooks);
                    System.out.println();
                    break;

                case 4:
                    sortedBooks = sorter.sortByTitle(books, true);
                    user.Print(sortedBooks);
                    System.out.println();
                    break;

                case 0:
                    customerMenu(user);
                    break;
                
                default:
                    break;
            }
        } while (choice != 0);
    }

    private static User Login() {
        // @SuppressWarnings("resource")
        // Scanner scanner = new Scanner(System.in);
        // ArrayList<User> users = UserManager.getUsers();

        // System.out.println("====Online Bookstore====");
        // System.out.println("====Login====");
        // System.out.println();
        // System.out.print("Username: ");
        // String userName = scanner.nextLine();
        
        // System.out.print("Password: ");
        // String password = scanner.nextLine();

        // for (User user : users) {
        //     if (user.getUserName().compareTo(userName) == 0 && user.getPassword().compareTo(password) == 0) {
        //         return user;
        //     }
        // }

        // System.out.println("Invalid username or password.");
        // return null;
        
        User user = new User(0, "Nguyen Ba Dat", "btad", "1234", "datngba2310@gmail.com");
        return user;
    }
}
