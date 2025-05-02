/*
Chọn LinkedList-based Queue vì:
    - Kích thước linh hoạt, không giới hạn cố định
    - Enqueue và dequeue đều là O(1) nếu giữ tham chiếu đến cả front và rear
    - Không lãng phí bộ nhớ cho phần tử không sử dụng
 */

import java.util.LinkedList;

public class LinkedListBasedQueue<T> {
    private LinkedList<T> items;

    public LinkedListBasedQueue() {
        this.items = new LinkedList<>();
    }

    public void enqueue(T item) {
        items.add(item);
    }

    public T dequeue() {        
        if (isEmpty()) {
            System.out.println("Queue không có phần tử nào để lấy!");
            return null;
        }

        return items.removeFirst();
    }

    public T front() {
        if (isEmpty()) {
            System.out.println("Queue không có phần tử nào để lấy!");
            return null;
        }
        
        return items.getFirst();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }
}
