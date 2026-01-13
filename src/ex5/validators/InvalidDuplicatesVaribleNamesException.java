package ex5.validators;

import ex5.parser.SyntaxException;

public class InvalidDuplicatesVaribleNamesException extends SyntaxException {
    public InvalidDuplicatesVaribleNamesException(String message) {
        super(message);
    }
}
