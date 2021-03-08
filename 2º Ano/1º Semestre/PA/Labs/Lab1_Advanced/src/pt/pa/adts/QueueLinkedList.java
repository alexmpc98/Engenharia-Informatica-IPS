package pt.pa.adts;

/**
 * Implementação de fila baseada em linked list
 *
 * @param <T> tipo de elementos a armazenar
 */
public class QueueLinkedList<T> implements Queue<T> {

    private Node header, trailer; /** sentinel of Queue. Header is the sentinel for the front of the queue, and trailer is the sentinel for dwthe end of the queue. */
    private int size; /** size of the Queue */

    public QueueLinkedList() {
        this.header = new Node(null,null,null);
        this.trailer = new Node(null,null,null);
        this.header.next = this.trailer;
        this.trailer.prev = this.header;
    }

    @Override
    public void enqueue(T element) throws FullQueueException{
        try {
            Node newNode = new Node(element, this.trailer.prev, this.trailer);
            this.trailer.prev.next = newNode;
            this.trailer.prev = newNode;
            this.size++;
        } catch (OutOfMemoryError e){
            throw new FullQueueException("No memory for more elements");
        }
    }

    @Override
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

    @Override
    public T front() throws EmptyQueueException{
        if(isEmpty()) throw new EmptyQueueException();
        return this.header.next.element;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean isEmpty(){
        return (this.size == 0);
    }

    @Override
    public void clear(){
        this.header.next = this.trailer;
        this.trailer.prev = this.header;
        this.size = 0;
    }

    private class Node {
        private T element;
        private Node next;
        private Node prev;

        public Node(T element, Node prev, Node next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
