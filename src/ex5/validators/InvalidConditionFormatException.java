package ex5.validators;

import ex5.parser.SyntaxException;

public class InvalidConditionFormatException extends SyntaxException {
    public InvalidConditionFormatException(String message) {
        super(message);
    }
}
