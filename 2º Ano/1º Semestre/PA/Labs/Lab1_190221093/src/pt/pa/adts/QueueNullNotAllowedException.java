package pt.pa.adts;

public class QueueNullNotAllowedException extends RuntimeException{
    public QueueNullNotAllowedException() {
        super("Null not allowed in Queue.");
    }
}
