package ex5.patterns;

import java.util.regex.Pattern;

/**
 * A utility class that holds regex patterns for method and parameter validation.
 * @author Eilam Soroka, Maayan Felig
 */
public final class RegexPatterns {
    /**
     * Private constructor to prevent instantiation
     */
    private RegexPatterns() {}

    public static final Pattern METHOD_DECLARATION = Pattern.compile
            ( "^\\s*void\\s+([a-zA-Z]\\w*)\\s*\\(([^)]*)\\)\\s*\\{\\s*$");
    public static final Pattern PARAMETER_PATTERN = Pattern.compile(
            "^\\s*(final\\s+)?" + "(int|double|boolean|char|String)" +
                    "\\s+([a-zA-Z]\\w*|_[a-zA-Z0-9]\\w*)\\s*$");
    public static final Pattern METHOD_CALL = Pattern.compile(
            "^\\s*([a-zA-Z]\\w*)\\s*\\((.*)\\)\\s*;\\s*$");

    // Variable patterns
    public static final Pattern VARIABLE_DECLARATION =
            Pattern.compile("^\\s*(final\\s+)?(int|double|boolean|String|char)\\s+(.+);\\s*$");

    public static final Pattern VARIABLE_NAME =
            Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*$|^_[a-zA-Z0-9][a-zA-Z0-9_]*$");
    public static final Pattern ASSIGNMENT_DELIMITER = Pattern.compile("\\s*=\\s*");
    public static final Pattern INTEGER_LITERAL = Pattern.compile("^-?\\d+$");
    public static final Pattern DOUBLE_LITERAL = Pattern.compile
            ("^-?\\d+\\.\\d*$|^-?\\.\\d+$|^-?\\d+$");
    public static final Pattern STRING_LITERAL = Pattern.compile("^\".*\"$");
    public static final Pattern BOOLEAN_LITERAL = Pattern.compile("^(true|false)$");
    public static final Pattern CHAR_LITERAL = Pattern.compile("^'.'$");

    //Condition patterns
    public static final Pattern FULL_CONDITION_LINE_STRUCTURE =
            Pattern.compile("^\\s*(if|while)\\s*\\((\\s*(.*?)\\s*)\\)\\s*\\{\\s*$");
        public static final Pattern CONDITION_EXPRESSION =
            Pattern.compile("^\\s*[^&|\\s]+(\\s*(\\|\\s*\\||&\\s*&)\\s*[^&|\\s]+)*\\s*$");
    public static final Pattern BOOLEAN_VALUE_PATTERN =
            Pattern.compile("^\\s*(true|false)\\s*$");



}
