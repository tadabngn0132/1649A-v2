/*
Độ phức tạp O(log n)
 */

import java.util.ArrayList;

public class BinarySearch {
    // Sort trước khi search (tăng dần)
    // Trả về index
    public int searchById(ArrayList<Book> books, int id) {
        int left = 0;
        int right = books.size() - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;

            // Kiểm tra xem id ở giữa có phải là id cần tìn không
            if (books.get(mid).getId() == id) {
                return mid;
            }

            // Nếu id cần tìm lớn hơn, tìm ở nửa bên phải
            // Ngược lại, tìm ở nửa bên trái
            if (books.get(mid).getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Không tìm thấy
        return -1;
    }

    public int searchByTitle(ArrayList<Book> books, String title) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // Kiểm tra xem title ở giữa có phải là title cần tìm không
            if (books.get(mid).getTitle().compareTo(title) == 0) {
                return mid;
            }

            // Nếu title cần tìm lớn hơn, tìm ở nửa bên phải
            // Ngược lại, tìm ở nửa bên trái
            if (books.get(mid).getTitle().compareTo(title) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Không tìm thấy
        return -1;
    }

    public int searchByAuthor(ArrayList<Book> books, String author) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // Kiểm tra xem author ở giữa có phải là author cần tìm không
            if (books.get(mid).getAuthor().compareTo(author) == 0) {
                return mid;
            }

            // Nếu author cần tìm lớn hơn, tìm ở nửa bên phải
            // Ngược lại, tìm ở nửa bên trái
            if (books.get(mid).getAuthor().compareTo(author) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Không tìm thấy
        return -1;
    }

    public ArrayList<Book> searchBooksByAuthorName(ArrayList<Book> books, String author) {
        int left;
        int right;
        ArrayList<Book> searchedBooks = new ArrayList<>();

        int idx = searchByAuthor(books, author);

        if (idx == -1) {
            return searchedBooks;
        } else {
            searchedBooks.add(books.get(idx));

            right = idx + 1;
            while (right < books.size()) {
                if (books.get(right).getAuthor().compareTo(author) == 0) {
                    searchedBooks.add(books.get(right));
                    right++;
                } else {
                    right++;
                }
            }

            left = idx - 1;
            while (left >= 0) {
                if (books.get(left).getAuthor().compareTo(author) == 0) {
                    searchedBooks.add(books.get(left));
                    left--;
                } else {
                    left--;
                }
            }

            return searchedBooks;
        }
    }

    public int searchOrderById(ArrayList<Order> orders, int id) {
        int left = 0;
        int right = orders.size() - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if (orders.get(mid).getId() == id) {
                return mid;
            }

            if (orders.get(mid).getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
