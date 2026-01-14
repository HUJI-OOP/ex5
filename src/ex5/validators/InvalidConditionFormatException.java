package ex5.validators;

import ex5.parser.SyntaxException;
/** Exception thrown when a condition format is invalid.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidConditionFormatException extends SyntaxException {
    /**
     * Constructs a new InvalidConditionFormatException with the specified detail message.
     * @param message the detail message
     */
    public InvalidConditionFormatException(String message) {
        super(message);
    }
}
