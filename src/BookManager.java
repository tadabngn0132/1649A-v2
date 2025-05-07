import java.util.ArrayList;

public class BookManager {
    // list of product in the store
    // Data structure: Array/ArrayList/LinkedList/HashMap/HashSet
    private static ArrayList<Book> books;
    public static ArrayList<Book> getBooks() {
        // if (book_file exists) {
        //     read from file and return the list of books
        // } else {
        //     1. create a new file
        //     2. create a book list
        //     3. save the list to the file
        // }

        if (books == null) {
            books = new ArrayList<>();
            books.add(new Book(10, "The Great Gatsby", "F. Scott Fitzgerald", 12.99, 50));
            books.add(new Book(3, "The Silmarillion", "J.R.R. Tolkien", 18.30, 25));
            books.add(new Book(6, "The Lord of the Rings: The Fellowship of the Ring", "J.R.R. Tolkien", 20.50, 45));
            books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", 10.50, 30));
            books.add(new Book(9, "1984", "George Orwell", 8.99, 45));
            books.add(new Book(1, "A Game of Thrones", "George R.R. Martin", 22.99, 50));
            books.add(new Book(8, "The Lord of the Rings: The Return of the King", "J.R.R. Tolkien", 21.25, 35));
            books.add(new Book(4, "Pride and Prejudice", "Jane Austen", 7.25, 25));
            books.add(new Book(5, "The Hobbit", "J.R.R. Tolkien", 15.75, 60));
            books.add(new Book(7, "A Clash of Kings", "George R.R. Martin", 23.50, 30));
            books.add(new Book(11, "The Lord of the Rings: The Two Towers", "J.R.R. Tolkien", 19.99, 40));
        }
        return books;
    }
}
