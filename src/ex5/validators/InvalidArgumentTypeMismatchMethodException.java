package ex5.validators;

import ex5.parser.SyntaxException;

public class InvalidArgumentTypeMismatchMethodException extends SyntaxException {
    public InvalidArgumentTypeMismatchMethodException(String message) {
        super(message);
    }
}
