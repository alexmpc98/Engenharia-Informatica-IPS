package pt.pa.adts;

public class FullQueueException extends RuntimeException{
    public FullQueueException(String message) {
        super(message);
    }

    public FullQueueException() {
        super("The stack is empty.");
    }
}