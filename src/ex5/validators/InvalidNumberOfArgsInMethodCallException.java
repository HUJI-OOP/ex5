package ex5.validators;

import ex5.parser.SyntaxException;

/**
 * Exception thrown when a method call has an invalid number of arguments.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidNumberOfArgsInMethodCallException extends SyntaxException {
    /**
     * Constructs a new InvalidNumberOfArgsInMethodCallException with the specified detail message.
     * @param message the detail message
     */
    public InvalidNumberOfArgsInMethodCallException(String message) {
        super(message);
    }
}
