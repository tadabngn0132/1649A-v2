import java.util.ArrayList;

public class Admin extends User {

    public Admin(int id, String fullName, String userName, String password, String email, String role) {
        super(id, fullName, userName, password, email, role);
    }

    @Override
    public void Print(ArrayList<Book> books) {
        System.out.printf("%-5s %-50s %-25s %-10s %-10s\n", "Id", "Title", "Author", "Price", "Quantity");
        for (Book book : books) {
            System.out.printf("%-5s %-50s %-25s $%-10.2f %-10d\n", book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity());
        }
    }

    @Override
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
