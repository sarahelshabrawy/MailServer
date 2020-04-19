package eg.edu.alexu.csd.datastructure.mailServer;

public class Queue {
    private Node front, rear;
    private int currentSize;

    private static class Node {
        private Object data;
        private Node next;
    }

    public Queue() {
        front = null;
        rear = null;
        currentSize = 0;
    }

    public void enqueue(Object item) {
        Node oldRear = rear;
        rear = new Node();
        rear.data = item;
        rear.next = null;
        if (isEmpty()) {
            front = rear;
        } else {
            oldRear.next = rear;
        }
        currentSize++;
    }

    public Object dequeue() {
        if (isEmpty()) { /// rear = null ; ????????
            throw new RuntimeException("Queue is empty!!");
        } else {
            Object item = front.data;
            front = front.next;
            currentSize--;
            return item;
        }
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public int size() {
        return currentSize;
    }

}
