import java.util.ArrayList;

public class Customer extends User {
    private Cart cart;

    public Customer(int id, String fullName, String userName, String password, String email, String role) {
        super(id, fullName, userName, password, email, role);
        this.cart = new Cart();
    }

    @Override
    public void Print(ArrayList<Book> books) {
        System.out.printf("| %-5s | %-50s | %-25s | %-11s | %-10s |\n", "ID", "Title", "Author", "Price", "Quantity");
        for (Book book : books) {
            System.out.printf("| %-5s | %-50s | %-25s | $%-10.2f | %-10s |\n", book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity());
        }
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void PrintCart(Customer customer) {
        System.out.println("============CART============");
        double totalPrice = 0;

        System.out.printf("| %-50s | %-25s | %-11s x %-10s = %-11s |\n", "Title", "Author", "Price", "Quantity", "Total Price");
        for (Book item : customer.getCart().getItems()) {
            System.out.printf("| %-50s | %-25s | $%-10.2f x %-10s = $%-10.2f |\n", 
                item.getTitle(), item.getAuthor(), item.getPrice(), item.getQuantity(), item.getPrice() * item.getQuantity());
            totalPrice += item.getPrice() * item.getQuantity();
        }

        System.out.println();
        System.out.println("Total: $" + String.format("%.2f", totalPrice));
    }
}
