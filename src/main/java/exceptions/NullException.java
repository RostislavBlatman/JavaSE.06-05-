package main.java.exceptions;

public class NullException extends Exception {
    public NullException() {
        super("Please,input good key");
    }

    public NullException(String message) {
        super(message);
    }
}
