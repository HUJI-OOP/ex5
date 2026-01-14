package ex5.validators;

import ex5.model.SymbolTable;
import ex5.parser.SyntaxException;
import ex5.patterns.RegexPatterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConditionValidator {

    private static final String CONDITION_DELIMITER_REGEX = "\\s*(\\|\\s*\\||&\\s*&)\\s*";
    private static final int CONDITION_STATEMENT_GROUP_INDEX = 2;

    public void validateConditionLine(String declaration, SymbolTable symbolTable) throws SyntaxException {
        //check full structure "if ( ... ) {" or "while ( ... ) {"
        Pattern fullConditionLinePattern = Pattern.compile(
                String.valueOf(RegexPatterns.FULL_CONDITION_LINE_STRUCTURE));
        Matcher fullLineMatcher = fullConditionLinePattern.matcher(declaration);
        if(!fullLineMatcher.matches()){
            throw new InvalidConditionFormatException(declaration);
        }//check structure inside the parentheses
        String conditionLine = fullLineMatcher.group(CONDITION_STATEMENT_GROUP_INDEX);
        Pattern conditionPattern = Pattern.compile(String.valueOf(RegexPatterns.CONDITION_EXPRESSION));
        Matcher matcher = conditionPattern.matcher(conditionLine);
        if(!matcher.matches()){
            throw new InvalidConditionFormatException(declaration);
        }//check each condition part
        String[] conditionParts = conditionLine.split(CONDITION_DELIMITER_REGEX);
        for(String conditionPart : conditionParts){
            conditionPart = conditionPart.trim();
            if(!isValidConditionPart(conditionPart, symbolTable)){
                throw new InvalidConditionFormatException(declaration);
            }
        }
    }

    private boolean isValidConditionPart(String conditionPart, SymbolTable symbolTable)
                                                                                    throws SyntaxException {
        Pattern booleanValuePattern = Pattern.compile(String.valueOf(RegexPatterns.BOOLEAN_VALUE_PATTERN));
        Matcher booleanMatcher = booleanValuePattern.matcher(conditionPart);
        if (booleanMatcher.matches()) {
            return true;
        }
        Pattern validLiteralPattern = Pattern.compile(
                String.valueOf(RegexPatterns.DOUBLE_LITERAL));
        Matcher validLiteralMatcher = validLiteralPattern.matcher(conditionPart);
        if (validLiteralMatcher.matches()) {
            return true;
        }
        if(symbolTable.getVariable(conditionPart) != null){
            return true;
        };
        return false;
    }
}
