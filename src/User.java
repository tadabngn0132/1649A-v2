import java.util.ArrayList;

public class User {
    private int id;
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private Cart cart;

    public User(int id, String fullName, String userName, 
                String password, String email) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.cart = new Cart();
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void Print(ArrayList<Book> books) {
        System.out.printf("| %-5s | %-50s | %-25s | %-11s | %-10s |\n", "ID", "Title", "Author", "Price", "Quantity");
        for (Book book : books) {
            System.out.printf("| %-5s | %-50s | %-25s | $%-10.2f | %-10s |\n", book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity());
        }
    };

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void PrintCart(User user) {
        System.out.println("============CART============");
        double totalPrice = 0;

        System.out.printf("| %-50s | %-25s | %-11s x %-10s = %-11s |\n", "Title", "Author", "Price", "Quantity", "Total Price");
        for (Book item : user.getCart().getItems()) {
            System.out.printf("| %-50s | %-25s | $%-10.2f x %-10s = $%-10.2f |\n", 
                item.getTitle(), item.getAuthor(), item.getPrice(), item.getQuantity(), item.getPrice() * item.getQuantity());
            totalPrice += item.getPrice() * item.getQuantity();
        }

        System.out.println();
        System.out.println("Total: $" + String.format("%.2f", totalPrice));
    }

    public void PrintOrder(ArrayList<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("You have no orders.");
            return;
        }
        
        System.out.println("================== YOUR ORDERS ==================");
        System.out.printf("| %-5s | %-15s | %-25s | %-20s | %-10s |\n", 
            "ID", "Book Count", "Shipping Address", "Customer Name", "Status");
        System.out.println("|-------|-----------------|---------------------------|----------------------|------------|");
        
        for (Order order : orders) {
            // Lấy số lượng sách thay vì cố gắng in toàn bộ danh sách sách
            int bookCount = order.getBooks().size();
            
            System.out.printf("| %-5d | %-15d | %-25s | %-20s | %-10s |\n", 
                order.getId(), 
                bookCount, 
                truncateString(order.getShippingAddress(), 25),
                truncateString(order.getCustomerName(), 20), 
                order.getStatus());
        }
        System.out.println("=================================================");
    }

    // Thêm phương thức này để cắt bớt chuỗi quá dài
    private String truncateString(String input, int maxLength) {
        if (input == null) {
            return "";
        }
        
        if (input.length() <= maxLength) {
            return input;
        }
        
        return input.substring(0, maxLength - 3) + "...";
    }

    // Thêm phương thức này để hiển thị chi tiết của một đơn hàng cụ thể
    public void PrintOrderDetail(Order order) {
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        
        System.out.println("\n=============== ORDER DETAILS ===============");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Shipping Address: " + order.getShippingAddress());
        System.out.println("Status: " + order.getStatus());
        
        System.out.println("\nBooks:");
        System.out.printf("| %-40s | %-20s | %-10s | %-8s | %-12s |\n", 
            "Title", "Author", "Price", "Quantity", "Subtotal");
        System.out.println("|------------------------------------------|----------------------|------------|----------|--------------|");
        
        double totalPrice = 0;
        for (Book book : order.getBooks()) {
            double subtotal = book.getPrice() * book.getQuantity();
            totalPrice += subtotal;
            
            System.out.printf("| %-40s | %-20s | $%-9.2f | %-8d | $%-11.2f |\n", 
                truncateString(book.getTitle(), 40),
                truncateString(book.getAuthor(), 20),
                book.getPrice(),
                book.getQuantity(),
                subtotal);
        }
        
        System.out.println("\nTotal Order Value: $" + String.format("%.2f", totalPrice));
        System.out.println("==============================================");
    }
}
