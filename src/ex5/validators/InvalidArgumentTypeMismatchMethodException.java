package ex5.validators;

import ex5.parser.SyntaxException;
/**
 * Exception thrown when a method is called with an argument type that does not match the expected type.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidArgumentTypeMismatchMethodException extends SyntaxException {
    /**
     * Constructs a new InvalidArgumentTypeMismatchMethodException with the specified detail message.
     * @param message the detail message
     */
    public InvalidArgumentTypeMismatchMethodException(String message) {
        super(message);
    }
}
