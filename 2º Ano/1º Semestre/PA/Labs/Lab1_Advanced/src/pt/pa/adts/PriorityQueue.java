package pt.pa.adts;

public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    private Node header, trailer; /** sentinel of Queue. Header is the sentinel for the front of the queue, and trailer is the sentinel for dwthe end of the queue. */
    private int size; /** size of the Queue */

    public PriorityQueue(){
        this.header = new Node(null,null,null, null);
        this.trailer = new Node(null,null,null, null);
        this.header.next = this.trailer;
        this.trailer.prev = this.header;
    }

    public void enqueue(T element) throws FullQueueException{
        try{
            Node newNode = new Node(element, this.trailer.prev, this.trailer, element);
            this.trailer.prev.next = newNode;
            this.trailer.prev = newNode;
            this.size++;
            if(front().compareTo(element) < 0) {
                Node nodeWithPriority = new Node(element, this.trailer.prev, this.trailer, element);
                this.header.next = nodeWithPriority;
                this.size++;
            }
        } catch (OutOfMemoryError e){
            throw new FullQueueException("No memory for more elements");
        }
    }

    public T dequeue() throws EmptyQueueException{
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        Node headerNode = header.next;
        T newElement = headerNode.element;
        header.next = headerNode.next;
        this.size--;
        return newElement;
    }

    public T front(){
        if(isEmpty()) throw new EmptyQueueException();
        return this.header.next.element;
    }

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return (this.size == 0);
    }

    public void clear(){
        this.header.next = this.trailer;
        this.trailer.prev = this.header;
        this.size = 0;
    }

    private class Node {
        private T element;
        private Node next;
        private Node prev;
        private T priority;

        public Node(T element, Node prev, Node next, T priority) {
            this.element = element;
            this.next = next;
            this.prev = prev;
            this.priority = priority;
        }
    }
}
