package pt.pa.adts;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StackLinkedList<T> implements Stack<T> {

    private Node top; //sentinel of linked list

    public StackLinkedList() {
        /*
        The configuration of an empty singly linked list is the sentinel
        pointing to null.
         */
        this.top = new Node(null, null);
    }

    @Override
    public void push(T element) throws FullStackException {
        /*
        Element should be stored in a new node which is pointed by top sentinel.
        Must maintain the chaining of all nodes.
         */
        try{
            Node newNode = new Node(element, top.next);
            top.next = newNode;
        } catch(OutOfMemoryError e){
            throw new FullStackException("No memory for more elements");
        }
    }

    @Override
    public T pop() throws EmptyStackException {
        /*
        The element at the top of the stack is the element stored in the
        node immediately after the top sentinel.
         */
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        Node topNode = top.next;
        T topElement = topNode.element;
        top.next = topNode.next;

        //T topElement = top.next.element;
        //top.next = top.next.next;

        return topElement;
    }

    @Override
    public T peek() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Node topNode = top.next;
        T topElement = topNode.element;
        return topElement;
    }

    @Override
    public int size() {
        Node topNode = top.next;
        int counter = 0;
        while(topNode != null){
            counter++;
            topNode = topNode.next;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        Node topNode = top.next;
        return (topNode == null);
    }

    @Override
    public void clear() {
        top.next = null;
    }

    /**
     * Inner class representing a linked list node.
     * Only recognized in the context of this class (private).
     */
    private class Node {
        private T element;
        private Node next;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
