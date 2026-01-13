package ex5.model;

import ex5.parser.SyntaxException;

public class IllegalCallingToUnexistingMethodException extends SyntaxException {
    public IllegalCallingToUnexistingMethodException(String message) {
        super(message);
    }
}
