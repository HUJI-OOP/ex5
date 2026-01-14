package ex5.validators;

import ex5.parser.SyntaxException;
/** * Exception thrown when a variable declaration is invalid.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidVariableDeclarationException extends SyntaxException {
    /**
     * Constructs a new InvalidVariableDeclarationException with the specified detail message.
     * @param message the detail message
     */
    public InvalidVariableDeclarationException(String message) {
        super(message);
    }
}
