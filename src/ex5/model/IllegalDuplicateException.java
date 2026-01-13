package ex5.model;

import ex5.parser.SyntaxException;

public class IllegalDuplicateException extends SyntaxException {
    public IllegalDuplicateException(String message) {
        super(message);
    }
}
