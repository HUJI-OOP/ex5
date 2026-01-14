package ex5.parser;

import ex5.patterns.RegexPatterns;

public class LineHandler {
    private static final String INVALID_LINE_INPUT = "Line is not valid in s-java";

    public static LineType classifyLine(String line) throws SyntaxException {

        if (RegexPatterns.SCOPE_CLOSE.matcher(line).matches()) {
            return LineType.SCOPE_CLOSE;
        }
        if (RegexPatterns.METHOD_RECOGNIZER.matcher(line).matches()) {
            return LineType.METHOD_DECLARATION;
        }
        if (RegexPatterns.IF_WHILE.matcher(line).matches()) {
            return LineType.IF_WHILE_CONDITION;
        }

        if (RegexPatterns.RETURN.matcher(line).matches()) {
            return LineType.RETURN;
        }


        // (); -> method call
        // (int|bool|String); -> variable declaration
        // = -> assignment
        if (RegexPatterns.END_WITH_SEMI_COLLOM.matcher(line).matches()){

            if (RegexPatterns.VARIABLE_DECLARATION_RECOGNIZER.matcher(line).matches()){
                return LineType.VARIABLE_DECLARATION;
            }
            if (RegexPatterns.ASSIGNMENT.matcher(line).matches()){
                return LineType.ASSIGNMENT;
            }

            if (RegexPatterns.METHOD_CALL_RECOGNIZER.matcher(line).matches()) {
                return LineType.METHOD_CALL;
            }
        }
        throw new InvalidLineInputException(INVALID_LINE_INPUT);
    }
}
