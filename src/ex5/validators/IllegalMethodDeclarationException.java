package ex5.validators;

import ex5.parser.SyntaxException;

public class IllegalMethodDeclarationException extends SyntaxException {
    public IllegalMethodDeclarationException(String message) {
        super(message);
    }
}
