package ex5.validators;

import ex5.parser.SyntaxException;

public class InvalidMethodCallFormatException extends SyntaxException {
    public InvalidMethodCallFormatException(String message) {
        super(message);
    }
}
