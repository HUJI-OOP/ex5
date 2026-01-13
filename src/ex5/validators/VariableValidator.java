package ex5.validators;

import ex5.model.SymbolTable;
import ex5.model.VariableType;
import ex5.parser.SyntaxException;
import ex5.patterns.RegexPatterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for variable declarations and assignments.
 * @author Eilaem Soroka, Maayan Felig
 */
public class VariableValidator {

    private static final String INVALID_DECLARATION_MESSAGE = "Invalid variable declaration: ";
    private static final char DELIMITER = ',';

    /**
     * Validates a variable declaration.
     * @param declaration The variable declaration to validate.
     * @param symbolTable The symbol table for context.
     * @throws SyntaxException If the declaration is invalid.
     */
    public void validateVariableDeclaration(String declaration, SymbolTable symbolTable)
                                                                                throws SyntaxException {
        Pattern declarationPattern = Pattern.compile(String.valueOf(RegexPatterns.VARIABLE_DECLARATION));
        Matcher declarationMatcher = declarationPattern.matcher(declaration);
        if (!declarationMatcher.matches()) {
            throw new InvalidVariableDeclarationException(INVALID_DECLARATION_MESSAGE + declaration);
        }
        boolean isFinal = declarationMatcher.group(1) != null;
        String type = declarationMatcher.group(2);
        String allVariables = declarationMatcher.group(3);
        String[] variableParts = allVariables.split(String.valueOf(DELIMITER));
        for (String varPart : variableParts) {
        }


    }

    private boolean valueMatchType(String value, String type, SymbolTable symbolTable) {
        VariableType declaredType = VariableType.convertFromString(type);
        if (declaredType == null) {
            return false;
        }
        if (RegexPatterns.INTEGER_LITERAL.matcher(value).matches()) {
            return declaredType.canAccept(VariableType.INT);
        } else if (RegexPatterns.DOUBLE_LITERAL.matcher(value).matches()) {
            return declaredType.canAccept(VariableType.DOUBLE);
        } else if (RegexPatterns.BOOLEAN_LITERAL.matcher(value).matches()) {
            return declaredType.canAccept(VariableType.BOOLEAN);
        } else if (RegexPatterns.STRING_LITERAL.matcher(value).matches()) {
            return declaredType.canAccept(VariableType.STRING);
        } else if (RegexPatterns.CHAR_LITERAL.matcher(value).matches()) {
            return declaredType.canAccept(VariableType.CHAR);
        }
        VariableType rightHandVariableType;
        try{
            rightHandVariableType = VariableType.valueOf(symbolTable.getLocalVariable(value).getType());
        } catch (Exception e) {
            return false;
        }
    }



}
