package ex5.validators;
/**
 * Exception thrown when a variable declaration has an invalid type.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidTypeException extends InvalidVariableDeclarationException {
    /**
     * Constructs a new InvalidTypeException with the specified detail message.
     * @param message the detail message
     */
    public InvalidTypeException(String message) {
        super(message);
    }
}
