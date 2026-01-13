package ex5.validators;

import ex5.model.SymbolTable;
import ex5.parser.SyntaxException;

/**
 * Validator for variable declarations and assignments.
 * @author Eilaem Soroka, Maayan Felig
 */
public class VariableValidator {

    private static final String INVALID_DECLARATION_MESSAGE = "Invalid variable declaration: ";
    private static final int MIN_DECLARATION_PARTS = 2;
    private  static final int MAX_DECLARATION_PARTS = 4;

    /**
     * Validates a variable declaration.
     * @param declaration The variable declaration to validate.
     * @param symbolTable The symbol table for context.
     * @throws SyntaxException If the declaration is invalid.
     */
    public void validateVariableDeclaration(String declaration, SymbolTable symbolTable)
                                                                                throws SyntaxException {
        String[] parts = declaration.trim().split("\\s+");
        if (parts.length < MIN_DECLARATION_PARTS || parts.length > MAX_DECLARATION_PARTS) {
            throw new InvalidVariableDeclarationException(INVALID_DECLARATION_MESSAGE + declaration);
        }

    }
}
