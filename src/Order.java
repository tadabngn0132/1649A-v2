import java.util.ArrayList;

public class Order {
    private int id;
    private String customerName;
    private String shippingAddress;
    private ArrayList<Book> books;
    private String status;
    
    public Order(int id, String customerName, String shippingAddress) {
        this.id = id;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.books = new ArrayList<>();
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
