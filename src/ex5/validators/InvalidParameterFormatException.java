package ex5.validators;

import ex5.parser.SyntaxException;

public class InvalidParameterFormatException extends SyntaxException {
    public InvalidParameterFormatException(String message) {

        super(message);
    }
}
