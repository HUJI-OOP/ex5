package ex5.parser;

import ex5.patterns.RegexPatterns;
/**
 * This class is responsible for classifying lines of code in the s-java language.
 * It uses regular expressions defined in RegexPatterns to identify the type of each line.
 * @author Eilam Soroka, Maayan Felig
 */
public class LineHandler {
    private static final String INVALID_LINE_INPUT = "Line is not valid in s-java";

    /**
     * Classifies a line of code into its corresponding LineType.
     * @param line the line of code to classify
     * @return the LineType of the given line
     * @throws SyntaxException if the line does not match any known patterns
     */
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
