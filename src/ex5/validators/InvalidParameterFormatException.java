package ex5.validators;

import ex5.parser.SyntaxException;
/**
 * Exception thrown when a parameter has an invalid format.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidParameterFormatException extends SyntaxException {
    /**
     * Constructs a new InvalidParameterFormatException with the specified detail message.
     * @param message the detail message
     */
    public InvalidParameterFormatException(String message) {

        super(message);
    }
}
