package ex5.validators;

import ex5.model.Method;
import ex5.model.SymbolTable;
import ex5.model.Variable;
import ex5.parser.SyntaxException;
import ex5.patterns.RegexPatterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

public class MethodValidator {
    private static final String INVALID_METOHD_DECLARATION_MESSAGE = "Invalid method declaration";
    private static final String INVALID_DOUBLE_METHOD_NAME_MESSAGE = "Method already defined: ";
    private static final String INVALID_PARAMETER_MESAGE = "Invalid parameter: ";
    private static final String INVALID_DUPLICATES_VARIABLE_NANES_IN_METHOD_MESAGE =
            "Duplicate parameter: ";
    private static final String INVALID_METHOD_CALL_FORMAT_MESSAGE =
            "calling the function was in the wrong format";
    private static final String INVALID_NUMBER_OF_ARGS_IN_METHOD_CALL
            = "Invalid number of arguments in call" +
            " to ";
    private static final String COMMA_SEPARATOR = ",";


    public static void validateAndRegister(String line, SymbolTable symbolTable) throws SyntaxException {
        Matcher matcher = RegexPatterns.METHOD_DECLARATION.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalMethodDeclarationException(INVALID_METOHD_DECLARATION_MESSAGE);
        }
        String methodName = matcher.group(1);

        String paramsGroup = matcher.group(2).trim();
        List<Variable> parameters = new ArrayList<>();
        Set<String> names = new HashSet<>();

        if (!paramsGroup.isEmpty()) {
            for (String param : paramsGroup.split(COMMA_SEPARATOR)) {
                Matcher pm = RegexPatterns.PARAMETER_PATTERN.matcher(param);
                if (!pm.matches()) {
                    throw new InvalidParameterFormatException(INVALID_PARAMETER_MESAGE + param);
                }

                boolean isFinal = pm.group(1) != null;
                String type = pm.group(2);
                String name = pm.group(3);

                if (!names.add(name)) {
                    throw new InvalidDuplicatesVaribleNamesException(
                            INVALID_DUPLICATES_VARIABLE_NANES_IN_METHOD_MESAGE + name);
                }

                parameters.add(new Variable(type, name, isFinal));
            }
        }

        symbolTable.addMethod(new Method(methodName, parameters));
    }

    public static void validateMethodExists(String line, SymbolTable symbolTable) throws SyntaxException {
        Matcher matcher = RegexPatterns.METHOD_CALL.matcher(line);
        if (!matcher.matches()) {
            throw new InvalidMethodCallFormatException(INVALID_METHOD_CALL_FORMAT_MESSAGE);
        }

        String methodName = matcher.group(1);
        String argsGroup = matcher.group(2).trim();

        Method method = symbolTable.getMethod(methodName);

        List<String> args = new ArrayList<>();
        if (!argsGroup.isEmpty()) {
            for (String arg : argsGroup.split(COMMA_SEPARATOR)) {
                args.add(arg.trim());
            }
        }

        if (args.size() != method.getParameters().size()) {
            throw new InvalidNumberOfArgsInMethodCallException(
                    INVALID_NUMBER_OF_ARGS_IN_METHOD_CALL + methodName);
        }

        for (int i = 0; i < args.size(); i++) {
            // todo validate each argument vs parameter
        }
    }
}
