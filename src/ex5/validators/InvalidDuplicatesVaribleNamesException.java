package ex5.validators;

import ex5.parser.SyntaxException;
/**
 * Exception thrown when there are duplicate variable names in the code.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidDuplicatesVaribleNamesException extends SyntaxException {
    /**
     * Constructs a new InvalidDuplicatesVaribleNamesException with the specified detail message.
     * @param message the detail message
     */
    public InvalidDuplicatesVaribleNamesException(String message) {
        super(message);
    }
}
