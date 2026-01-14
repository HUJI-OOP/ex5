package ex5.validators;

import ex5.parser.SyntaxException;

public class InvalidVariableAssignmentException extends SyntaxException {
    public InvalidVariableAssignmentException(String message) {
        super(message);
    }
}
