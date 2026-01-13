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

    public static final Pattern METHOD_DECLARATION = Pattern.compile(
            "^\\s*void\\s+([a-zA-Z]\\w*)\\s*\\(([^)]*)\\)\\s*\\{\\s*$");
    public static final Pattern PARAMETER_PATTERN =
            Pattern.compile(
                    "^\\s*(final\\s+)?(int|double|boolean|char|String)\\s+([a-zA-Z_]\\w*)\\s*$");
    public static final Pattern METHOD_CALL =
            Pattern.compile("^\\s*([a-zA-Z]\\w*)\\s*\\((.*)\\)\\s*;\\s*$");

    public static final Pattern VARIABLE_DECLARATION =
            Pattern.compile("^\\s*(final\\s+)?(int|double|boolean|String|char)\\s+(.+);\\s*$");

    public static final Pattern VARIABLE_NAME =
            Pattern.compile("/^[a-zA-Z][a-zA-Z0-9_]*$|^_[a-zA-Z0-9][a-zA-Z0-9_]*$/gm");
    public static final Pattern ASSIGNMENT_DELIMITER = Pattern.compile("\\s*=\\s*");

    public static final Pattern INTEGER_TYPE = Pattern.compile("^int$");
    public static final Pattern DOUBLE_TYPE = Pattern.compile("^double$");
    public static final Pattern STRING_TYPE = Pattern.compile("^String$");
    public static final Pattern BOOLEAN_TYPE = Pattern.compile("^boolean$");
    public static final Pattern CHAR_TYPE = Pattern.compile("^char$");

    public static final Pattern INTEGER_LITERAL = Pattern.compile("^-?\\d+$");
    public static final Pattern DOUBLE_LITERAL = Pattern.compile
            ("^-?\\d+\\.\\d*$|^-?\\.\\d+$|^-?\\d+$");
    public static final Pattern STRING_LITERAL = Pattern.compile("^\".*\"$");
    public static final Pattern BOOLEAN_LITERAL = Pattern.compile("^(true|false)$");
    public static final Pattern CHAR_LITERAL = Pattern.compile("^'.'$");



}
