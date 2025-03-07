package walle.exception;

/**
 * Exception thrown when the parameters of a command are invalid.
 */
public class InvalidCommandParameterException extends Exception {
    public InvalidCommandParameterException(String message) {
        super(message);
    }
}
