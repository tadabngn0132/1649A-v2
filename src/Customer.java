import java.util.ArrayList;

public class Customer extends User {

    public Customer(int id, String fullName, String userName, String password, String email, String role) {
        super(id, fullName, userName, password, email, role);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void Print(ArrayList<Book> books) {
        System.out.printf("%-25s %-10.2f %-10d\n", "Title", "Author", "Price", "Quantity");
        for (Book book : books) {
            System.out.printf("%-25s %-10.2f %-10d\n", book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity());
        }
    }

}
