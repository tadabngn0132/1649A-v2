import java.util.ArrayList;

public class UserManager {
    private static ArrayList<User> users;
    public static ArrayList<User> getUsers() {
        // if (book_file exists) {
        //     read from file and return the list of books
        // } else {
        //     1. create a new file
        //     2. create a book list
        //     3. save the list to the file
        // }

        if (users == null) {
            users = new ArrayList<>();
            users.add(new Admin(1, "Nguyễn Văn A", "nguyenvana", "password123", "nguyenvana@example.com", "admin"));
            users.add(new Customer(2, "Trần Thị B", "tranthib", "abc@123", "tranthib@example.com", "customer"));
            users.add(new Customer(3, "Lê Văn C", "levanc", "securepass", "levanc@example.com", "customer"));
            users.add(new Customer(4, "Phạm Thị D", "phamthid", "mypassword", "phamthid@example.com", "customer"));
            users.add(new Admin(5, "Hoàng Văn E", "hoangvane", "123456", "hoangvane@example.com", "admin"));
            users.add(new Customer(6, "Nguyen Ba Dat", "btad0132", "Datngba2310@", "datngba2310@gmail.com", "customer"));
            users.add(new Admin(7, "Dat Nguyen Ba", "datngba2310", "Datngba2310@", "datnbgdh220895@gmail.com", "admin"));
        }
        return users;
    }
}
