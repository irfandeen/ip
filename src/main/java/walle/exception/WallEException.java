package walle.exception;

/**
 * A custom exception class for handling general exceptions in the WallE application.
 */
public class WallEException extends Exception {
    public WallEException(String errorMessage) {
        super(errorMessage);
    }
}
