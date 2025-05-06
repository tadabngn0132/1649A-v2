/*
Độ phức tạp O(n^2)
 */

import java.util.ArrayList;

public class InsertionSort {    
    public ArrayList<Book> sortByTitle(ArrayList<Book> books, boolean asc) {
        int n = books.size();
        ArrayList<Book> sorted = new ArrayList<>(books);
        for (int i = 1; i < n; i++) {
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                if((asc && sorted.get(k).getTitle().compareTo(sorted.get(j).getTitle()) < 0) || 
                    (!asc && sorted.get(k).getTitle().compareTo(sorted.get(j).getTitle()) > 0)) {
                    swap(sorted, k, j);
                    k = j;
                } else 
                    break;
            }
        }
        return sorted; 
    }

    public ArrayList<Book> sortByAuthor(ArrayList<Book> books, boolean asc) {
        int n = books.size();
        ArrayList<Book> sorted = new ArrayList<>(books);
        for (int i = 1; i < n; i++) {
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                if((asc && sorted.get(k).getAuthor().compareTo(sorted.get(j).getAuthor()) < 0) || 
                    (!asc && sorted.get(k).getAuthor().compareTo(sorted.get(j).getAuthor()) > 0)) {
                    swap(sorted, k, j);
                    k = j;
                } else 
                    break;
            }
        }
        return sorted;
    }
    
    public ArrayList<Book> sortByPrice(ArrayList<Book> books, boolean asc) {
        int n = books.size();
        ArrayList<Book> sorted = new ArrayList<>(books);
        for (int i = 1; i < n; i++) {
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                if((asc && sorted.get(k).getPrice() < sorted.get(j).getPrice()) || 
                    (!asc && sorted.get(k).getPrice() > sorted.get(j).getPrice())) {
                    swap(sorted, k, j);
                    k = j;
                } else 
                    break;
            }
        }
        return sorted; 
    }
    
    protected static void swap(ArrayList<Book> books, int i, int j) {
        Book temp = books.get(i);
        books.set(i, books.get(j));
        books.set(j, temp);
    }
}
