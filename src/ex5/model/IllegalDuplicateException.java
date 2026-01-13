package ex5.model;

import ex5.parser.SyntaxException;

/**
 * Exception thrown when a duplicate declaration is found in the code.
 * @author Eilam Soroka, Maayan Felig
 */
public class IllegalDuplicateException extends SyntaxException {
    /**
     * Constructs a new IllegalDuplicateException with the specified detail message.
     * @param message the detail message
     */
    public IllegalDuplicateException(String message) {
        super(message);
    }
}
