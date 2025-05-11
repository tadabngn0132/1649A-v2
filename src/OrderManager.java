import java.util.ArrayList;

public class OrderManager {
    private static LinkedListBasedQueue<Order> pendingOrders;
    private static LinkedListBasedQueue<Order> processingOrders;
    private static LinkedListBasedQueue<Order> completedOrders;
    private static ArrayList<Order> allOrders;
    private static int nextOrderId = 1;

    public static int getNextOrderId() {
        return nextOrderId++;
    }
    
    // Lấy danh sách đơn hàng đang chờ xử lý
    public static LinkedListBasedQueue<Order> getPendingOrders() {
        if (pendingOrders == null) {
            pendingOrders = new LinkedListBasedQueue<>();
            processingOrders = new LinkedListBasedQueue<>();
            completedOrders = new LinkedListBasedQueue<>();
            allOrders = new ArrayList<>();
            
        }
        return pendingOrders;
    }
    
    // Lấy danh sách đơn hàng đang xử lý
    public static LinkedListBasedQueue<Order> getProcessingOrders() {
        if (processingOrders == null) {
            getPendingOrders(); // Đảm bảo khởi tạo tất cả các queue
        }
        return processingOrders;
    }
    
    // Lấy danh sách đơn hàng đã hoàn thành
    public static LinkedListBasedQueue<Order> getCompletedOrders() {
        if (completedOrders == null) {
            getPendingOrders(); // Đảm bảo khởi tạo tất cả các queue
        }
        return completedOrders;
    }
    
    // Lấy tất cả đơn hàng
    public static ArrayList<Order> getAllOrders() {
        if (allOrders == null) {
            getPendingOrders(); // Đảm bảo khởi tạo tất cả các queue
        }
        return allOrders;
    }
    
    // Chuyển đơn hàng từ trạng thái Pending sang Processing
    public static Order processOrder() {
        if (pendingOrders.isEmpty()) {
            System.out.println("Không có đơn hàng nào đang chờ xử lý!");
            return null;
        }
        
        Order order = pendingOrders.dequeue();
        order.setStatus("Processing");
        processingOrders.enqueue(order);
        return order;
    }
    
    // Chuyển đơn hàng từ trạng thái Processing sang Completed
    public static Order completeOrder() {
        if (processingOrders.isEmpty()) {
            System.out.println("Không có đơn hàng nào đang xử lý!");
            return null;
        }
        
        Order order = processingOrders.dequeue();
        order.setStatus("Completed");
        completedOrders.enqueue(order);
        return order;
    }
    
    // Tìm đơn hàng theo ID
    public static Order findOrderById(int id) {
        for (Order order : allOrders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
    
    // Lấy đơn hàng theo khách hàng
    public static ArrayList<Order> getOrdersByCustomer(String customerName) {
        ArrayList<Order> customerOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getCustomerName().equals(customerName)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }
    
    // Sắp xếp sách trong đơn hàng theo tiêu đề
    public static void sortBooksByTitle(Order order, boolean ascending) {
        InsertionSort sorter = new InsertionSort();
        ArrayList<Book> sortedBooks = sorter.sortByTitle(order.getBooks(), ascending);
        order.setBooks(sortedBooks);
    }
    
    // Sắp xếp sách trong đơn hàng theo giá
    public static void sortBooksByPrice(Order order, boolean ascending) {
        InsertionSort sorter = new InsertionSort();
        ArrayList<Book> sortedBooks = sorter.sortByPrice(order.getBooks(), ascending);
        order.setBooks(sortedBooks);
    }
    
    // Cập nhật trạng thái đơn hàng theo ID
    public static boolean updateOrderStatus(int orderId, String status) {
        Order order = findOrderById(orderId);
        if (order != null) {
            String oldStatus = order.getStatus();
            order.setStatus(status);
            
            // Cập nhật queue tương ứng
            if (oldStatus.equals("Pending") && !status.equals("Pending")) {
                // Xóa khỏi pending queue
                LinkedListBasedQueue<Order> tempQueue = new LinkedListBasedQueue<>();
                while (!pendingOrders.isEmpty()) {
                    Order tempOrder = pendingOrders.dequeue();
                    if (tempOrder.getId() != orderId) {
                        tempQueue.enqueue(tempOrder);
                    }
                }
                while (!tempQueue.isEmpty()) {
                    pendingOrders.enqueue(tempQueue.dequeue());
                }
            }
            
            if (oldStatus.equals("Processing") && !status.equals("Processing")) {
                // Xóa khỏi processing queue
                LinkedListBasedQueue<Order> tempQueue = new LinkedListBasedQueue<>();
                while (!processingOrders.isEmpty()) {
                    Order tempOrder = processingOrders.dequeue();
                    if (tempOrder.getId() != orderId) {
                        tempQueue.enqueue(tempOrder);
                    }
                }
                while (!tempQueue.isEmpty()) {
                    processingOrders.enqueue(tempQueue.dequeue());
                }
            }
            
            // Thêm vào queue mới
            if (status.equals("Processing")) {
                processingOrders.enqueue(order);
            } else if (status.equals("Completed")) {
                completedOrders.enqueue(order);
            } else if (status.equals("Pending")) {
                pendingOrders.enqueue(order);
            }
            
            return true;
        }
        return false;
    }
    
    // Hủy đơn hàng
    public static boolean cancelOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null && !order.getStatus().equals("Completed")) {
            // Trả lại số lượng sách vào kho
            for (Book orderBook : order.getBooks()) {
                for (Book inventoryBook : BookManager.getBooks()) {
                    if (orderBook.getId() == inventoryBook.getId()) {
                        inventoryBook.setQuantity(inventoryBook.getQuantity() + orderBook.getQuantity());
                        break;
                    }
                }
            }
            
            // Cập nhật trạng thái
            order.setStatus("Cancelled");
            
            // Xóa khỏi queue hiện tại
            if (order.getStatus().equals("Pending")) {
                LinkedListBasedQueue<Order> tempQueue = new LinkedListBasedQueue<>();
                while (!pendingOrders.isEmpty()) {
                    Order tempOrder = pendingOrders.dequeue();
                    if (tempOrder.getId() != orderId) {
                        tempQueue.enqueue(tempOrder);
                    }
                }
                while (!tempQueue.isEmpty()) {
                    pendingOrders.enqueue(tempQueue.dequeue());
                }
            } else if (order.getStatus().equals("Processing")) {
                LinkedListBasedQueue<Order> tempQueue = new LinkedListBasedQueue<>();
                while (!processingOrders.isEmpty()) {
                    Order tempOrder = processingOrders.dequeue();
                    if (tempOrder.getId() != orderId) {
                        tempQueue.enqueue(tempOrder);
                    }
                }
                while (!tempQueue.isEmpty()) {
                    processingOrders.enqueue(tempQueue.dequeue());
                }
            }
            
            return true;
        }
        return false;
    }
    
    // Tính tổng doanh thu của các đơn hàng đã hoàn thành
    public static double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : allOrders) {
            if (order.getStatus().equals("Completed")) {
                for (Book book : order.getBooks()) {
                    totalRevenue += book.getPrice() * book.getQuantity();
                }
            }
        }
        return totalRevenue;
    }
    
    // Đếm số lượng đơn hàng theo trạng thái
    public static int countOrdersByStatus(String status) {
        int count = 0;
        for (Order order : allOrders) {
            if (order.getStatus().equals(status)) {
                count++;
            }
        }
        return count;
    }
}