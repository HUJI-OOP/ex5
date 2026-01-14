package ex5.validators;

import ex5.parser.SyntaxException;
/** * Exception thrown when a method call does not conform to the expected format.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidMethodCallFormatException extends SyntaxException {
    /**
     * Constructs a new InvalidMethodCallFormatException with the specified detail message.
     * @param message the detail message
     */
    public InvalidMethodCallFormatException(String message) {
        super(message);
    }
}
