import java.util.ArrayList;

public class Cart {
    private ArrayList<Book> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public void addItem(Book book) {
        for (Book item : items) {
            if (item.getId() == book.getId()) {
                item.setQuantity(item.getQuantity() + book.getQuantity());
                return;
            }
        }

        items.add(book);
    }
    
    public void removeItem(int bookId) {
        items.removeIf(book -> book.getId() == bookId);
    }
    
    public void updateQuantity(int bookId, int quantity) {
        for (Book item : items) {
            if (item.getId() == bookId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    
    public ArrayList<Book> getItems() {
        return items;
    }
    
    public void clear() {
        items.clear();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public double getTotalPrice() {
        double total = 0;
        for (Book item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
