/*
Chọn ArrayList-based Stack vì:
    - Số lượng item có thể thay đổi, và ArrayList tự động mở rộng
    - Triển khai đơn giản hơn so với LinkedList-based
    - Không cần lo lắng về việc khai báo kích thước tối đa như Array-based
 */

import java.util.ArrayList;

public class ArrayListBasedStack<T> {
    private ArrayList<T> items;
    private int top;

    public ArrayListBasedStack(int size) {
        this.items = new ArrayList<>(size);
        this.top = -1;
    }
    
    public ArrayListBasedStack() {
        this.items = new ArrayList<>(25);
        this.top = -1;
    }

    public void push(T item) {
        items.add(item);
        top++;
    }

    public T pop() {
        T item;

        if (isEmpty()) {
            System.out.println("Stack không có phần tử nào để lấy!");
            return null;
        }

        item = items.remove(top);
        top--;

        return item;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack không có phần tử nào để lấy!");
            return null;
        }

        return items.get(top);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}
