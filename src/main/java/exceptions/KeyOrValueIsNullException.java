package main.java.exceptions;

public class KeyOrValueIsNullException extends Exception {
    public KeyOrValueIsNullException() {
        super("Please,input good key");
    }

    public KeyOrValueIsNullException(String message) {
        super(message);
    }
}
