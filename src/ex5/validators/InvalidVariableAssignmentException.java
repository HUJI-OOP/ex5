package ex5.validators;

import ex5.parser.SyntaxException;
/** * Exception thrown when there is an invalid variable assignment.
 * @author Eilam Soroka, Maayan Felig
 */
public class InvalidVariableAssignmentException extends SyntaxException {
    /**
     * Constructs a new InvalidVariableAssignmentException with the specified detail message.
     * @param message the detail message
     */
    public InvalidVariableAssignmentException(String message) {
        super(message);
    }
}
