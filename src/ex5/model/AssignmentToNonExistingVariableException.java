package ex5.model;

import ex5.parser.SyntaxException;

/**
 * Exception thrown when there is an attempt to assign a value to a variable that does not exist in the
 * current scope.
 * @author Eilam Soroka, Maayan Felig
 */
public class AssignmentToNonExistingVariableException extends SyntaxException {
    public AssignmentToNonExistingVariableException(String message) {
        super(message);
    }
}
