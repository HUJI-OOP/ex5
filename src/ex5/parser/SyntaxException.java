package ex5.parser;
/**
 * Custom exception to indicate syntax errors during parsing.
 * @author Eilam Soroka, Maayan Felig
 */
public class SyntaxException extends Exception {
    /**
     * Constructs a new SyntaxException with the specified detail message.
     * @param message the detail message
     */
    public SyntaxException(String message) {
        super(message);
    }
}
