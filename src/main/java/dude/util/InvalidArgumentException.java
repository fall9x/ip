package dude.util;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}