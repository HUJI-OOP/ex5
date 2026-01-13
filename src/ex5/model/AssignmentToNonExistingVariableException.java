package ex5.model;

import ex5.parser.SyntaxException;

public class AssignmentToNonExistingVariableException extends SyntaxException {
    public AssignmentToNonExistingVariableException(String message) {
        super(message);
    }
}
