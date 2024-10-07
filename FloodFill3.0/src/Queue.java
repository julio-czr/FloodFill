public class Queue<T> {
    private int front;
    private int rear;
    private int size;
    private int capacity;
    private T[] elements;

    public Queue(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.capacity = capacity;
    }

    public void enqueue(T item) {
        rear = moveToNextIndex(rear);
        elements[rear] = item;
        if (size < capacity) size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue vazia.");
        }
        T item = elements[front];
        elements[front] = null;
        front = moveToNextIndex(front);
        size--;
        return item;
    }

    public void clear() {
        elements = (T[]) new Object[capacity];
        rear = -1;
        front = 0;
        size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int moveToNextIndex(int index) {
        return (index + 1) % capacity;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue vazia.");
        }
        return elements[front];
    }

    public int getSize() {
        return size;
    }
}
