package ex5.parser;
/** * Exception thrown when a line of input is invalid according to the syntax rules.
 * * @author Eilam Soroka, Maayan Felig
 */
public class InvalidLineInputException extends SyntaxException {
    /**
     * Constructs a new InvalidLineInputException with the specified detail message.
     * @param message the detail message
     */
    public InvalidLineInputException(String message) {
        super(message);
    }
}
