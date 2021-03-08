package pt.pa.adts;

public class InvalidPositionException extends RuntimeException {

    public InvalidPositionException(String s) {
        super(s);
    }

    public InvalidPositionException() {
    }
}

