package ex5.patterns;

import java.util.regex.Pattern;

public class RegexPatterns {
    public static final Pattern METHOD_DECLARATION = Pattern.compile(
            "^\\s*void\\s+([a-zA-Z]\\w*)\\s*\\(([^)]*)\\)\\s*\\{\\s*$");
    public static final Pattern PARAMETER_PATTERN =
            Pattern.compile
                    ("^\\s*(final\\s+)?(int|double|boolean|char|String)\\s+([a-zA-Z_]\\w*)\\s*$");

}
