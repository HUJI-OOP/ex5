package ex5.parser;
/** * Exception thrown when an invalid scope is encountered during parsing.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidScopeException extends SyntaxException {
    /**
     * Constructs a new InvalidScopeException with the specified detail message.
     * @param message the detail message
     */
    public InvalidScopeException(String message) {
        super(message);
    }
}
