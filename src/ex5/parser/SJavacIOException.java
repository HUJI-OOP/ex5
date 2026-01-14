package ex5.parser;
/**
 * Custom exception to handle IO errors specific to SJavac operations.
 * @author Eilam Soroka, Maayan Felig
 */
public class SJavacIOException extends Exception {
    /**
     * Constructs a new SJavacIOException with the specified detail message.
     * @param message the detail message
     */
    public SJavacIOException(String message) {
        super(message);
    }
}
