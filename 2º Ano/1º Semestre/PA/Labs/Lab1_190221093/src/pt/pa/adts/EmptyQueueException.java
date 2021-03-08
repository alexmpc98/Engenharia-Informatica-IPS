package pt.pa.adts;

public class EmptyQueueException extends RuntimeException{
    public EmptyQueueException() {
        super("The queue is empty.");
    }
}

