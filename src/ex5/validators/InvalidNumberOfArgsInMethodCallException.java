package ex5.validators;

public class InvalidNumberOfArgsInMethodCallException extends RuntimeException {
    public InvalidNumberOfArgsInMethodCallException(String message) {
        super(message);
    }
}
