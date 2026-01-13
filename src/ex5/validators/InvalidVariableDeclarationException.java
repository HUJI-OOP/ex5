package ex5.validators;

import ex5.parser.SyntaxException;

public class InvalidVariableDeclarationException extends SyntaxException {
    public InvalidVariableDeclarationException(String message) {
        super(message);
    }
}
