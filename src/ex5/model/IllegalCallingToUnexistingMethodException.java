package ex5.model;

import ex5.parser.SyntaxException;

/**
 * Exception thrown when there is an illegal call to a non-existing method.
 * @author Eilam Soroka, Maayan Felig
 */
public class IllegalCallingToUnexistingMethodException extends SyntaxException {
    /**
     * Constructs a new IllegalCallingToUnexistingMethodException with the specified detail message.
     * @param message the detail message
     */
    public IllegalCallingToUnexistingMethodException(String message) {
        super(message);
    }
}
