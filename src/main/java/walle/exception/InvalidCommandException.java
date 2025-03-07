package walle.exception;

/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
