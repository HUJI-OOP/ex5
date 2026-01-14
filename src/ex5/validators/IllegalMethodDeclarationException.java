package ex5.validators;

import ex5.parser.SyntaxException;
/** Exception thrown when a method declaration is illegal.
 * @author Eilam Soroka, Maayan Felig
 */
public class IllegalMethodDeclarationException extends SyntaxException {
    /**
     * Constructs a new IllegalMethodDeclarationException with the specified detail message.
     * @param message the detail message
     */
    public IllegalMethodDeclarationException(String message) {
        super(message);
    }
}
