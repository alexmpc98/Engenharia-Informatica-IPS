package pt.pa.adts;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

public class StackLinkedList<T> implements Stack<T> {

    private final Node top; //sentinel of linked list
    //private int size;

    public StackLinkedList() {
        /*
        The configuration of an empty singly linked list is the sentinel
        pointing to null.
         */
        this.top = new Node(null, null);
        //this.size = 0;
    }

    @Override
    public void push(T element) throws FullStackException {
        //O(1)
        try {
            Node newNode = new Node(element, top.next);
            top.next = newNode;
            //this.size++;
        } catch(OutOfMemoryError e) {
            throw new FullStackException("No memory for more elements.");
        }
    }

    @Override
    public T pop() throws EmptyStackException {
        //O(1)
        if(isEmpty()) throw new EmptyStackException();

        T element = top.next.element;
        top.next = top.next.next;

        //this.size--;

        return element;
    }

    @Override
    public T peek() throws EmptyStackException {
        if(isEmpty()) throw new EmptyStackException();

        return top.next.element;
    }

    @Override
    public int size() {
        //O(1)
        //return this.size;

        //O(n)
        int counter = 0;
        Node current = top.next;
        while(current != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return (top.next == null);
    }

    @Override
    public void clear() {
        this.top.next = null;
        //this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /* do topo para a base */
    private class MyIterator implements Iterator<T> {

        private Node currentNodePointer;

        public MyIterator() {
            currentNodePointer = top.next;
        }

        @Override
        public boolean hasNext() {
            return currentNodePointer != null;
        }

        @Override
        public T next() {
            T next = currentNodePointer.element;
            //avançar iterator?
            currentNodePointer = currentNodePointer.next;
            return next;
        }
    }

    /**
     * Inner class representing a linked list node.
     * Only recognized in the context of this class (private).
     */
    private class Node {
        private T element;
        private Node next /* = null */;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
