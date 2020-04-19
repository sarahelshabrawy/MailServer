package eg.edu.alexu.csd.datastructure.mailServer;

import java.util.EmptyStackException;

public class Stack implements IStack {
    private class Node{
        Object data;
        Node next;

        private Node(Object data, Node next) {
            this.data = data;
            this.next=next;
        }
    }
    Node top = null;
    int size = 0;
    @Override
    public Object pop() {
       if(size()==0)
           throw new EmptyStackException();
        Object removed = top.data;
        top = top.next;
        size--;
        return removed;
    }

    @Override
    public Object peek() {
        if(size()==0)
            throw new EmptyStackException();
        return top.data;
    }

    @Override
    public void push(Object element) {
        top = new Node(element,top);
     size++;
    }

    @Override
    public boolean isEmpty() {
        return (size()==0);
    }

    @Override
    public int size() {
        return size;
    }
}
