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
    private static final int MIN_DECLARATION_PARTS = 1;
    private static final int MAX_DECLARATION_PARTS = 2;
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    public static final int FINAL_GROUP_INDEX = 1;
    public static final int TYPE_GROUP_INDEX = 2;
    public static final int VARIABLES_GROUP_INDEX = 3;

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
        boolean isFinal = declarationMatcher.group(FINAL_GROUP_INDEX) != null;
        VariableType type = VariableType.convertFromString(declarationMatcher.group(TYPE_GROUP_INDEX));
        String allVariables = declarationMatcher.group(VARIABLES_GROUP_INDEX);
        if (!isValidInitializationValue(allVariables, type, symbolTable)) {
            throw new InvalidVariableDeclarationException(INVALID_DECLARATION_MESSAGE + declaration);
        }
    }

    private boolean isValidInitializationValue(String allVariables,
                                               VariableType type, SymbolTable symbolTable) {
        String[] allVariablesDivided = allVariables.split(String.valueOf(DELIMITER));
        for (String variable : allVariablesDivided) {
            String[] varParts = variable.split(String.valueOf(RegexPatterns.ASSIGNMENT_DELIMITER));
            if (varParts.length > MAX_DECLARATION_PARTS || varParts.length < MIN_DECLARATION_PARTS) {
                return false;
            }
            String variableName = varParts[NAME_INDEX].trim();
            if (!RegexPatterns.VARIABLE_NAME.matcher(variableName).matches()) {
                return false;
            }
            if (varParts.length == MAX_DECLARATION_PARTS) {
                String value = varParts[VALUE_INDEX].trim();
                if (!valueMatchType(value, type, symbolTable)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean valueMatchType(String value, VariableType type, SymbolTable symbolTable) {
        if (type == null) {
            return false;
        }
        if (RegexPatterns.INTEGER_LITERAL.matcher(value).matches()) {
            return type.canAccept(VariableType.INT);
        } else if (RegexPatterns.DOUBLE_LITERAL.matcher(value).matches()) {
            return type.canAccept(VariableType.DOUBLE);
        } else if (RegexPatterns.BOOLEAN_LITERAL.matcher(value).matches()) {
            return type.canAccept(VariableType.BOOLEAN);
        } else if (RegexPatterns.STRING_LITERAL.matcher(value).matches()) {
            return type.canAccept(VariableType.STRING);
        } else if (RegexPatterns.CHAR_LITERAL.matcher(value).matches()) {
            return type.canAccept(VariableType.CHAR);
        }
        VariableType rightHandVariableType;
        try{
            rightHandVariableType = symbolTable.getLocalVariable(value).getType();
        } catch (Exception e) {
            return false;
        }
        return type.canAccept(rightHandVariableType);
    }



}
