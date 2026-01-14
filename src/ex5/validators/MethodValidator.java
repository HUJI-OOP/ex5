package ex5.validators;

import ex5.model.Method;
import ex5.model.SymbolTable;
import ex5.model.Variable;
import ex5.model.VariableType;
import ex5.parser.SyntaxException;
import ex5.patterns.RegexPatterns;
import ex5.validators.VariableValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
/**
 * MethodValidator class
 * validate method declaration and method calls
 * @author Eilam Soroka, Maayan Felig
 */
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
    private static final String ARRGUMENT_TYPE_MISMATCH_TO_DECLERATION_MEESAGE =
            "Argument type mismatch for parameter: ";

    /**
     * validate method declaration line and register it in the symbol table
     * @param line the line to validate
     * @param symbolTable the symbol table
     * @throws SyntaxException if the line is invalid
     */
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
            String[] rawParams = paramsGroup.split(COMMA_SEPARATOR, -1);

            for (String param : rawParams) {
                if (param.trim().isEmpty()) {
                    throw new InvalidParameterFormatException(
                            INVALID_PARAMETER_MESAGE + paramsGroup);
                }

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

                parameters.add(new Variable(VariableType.convertFromString(type), name, isFinal));
            }
        }

        symbolTable.addMethod(new Method(methodName, parameters));
    }

    /**
     * validate method call line
     * @param line the line to validate
     * @param symbolTable the symbol table
     * @throws SyntaxException if the line is invalid
     */
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
            String[] rawArgs = argsGroup.split(COMMA_SEPARATOR, -1);
            for (String arg : rawArgs) {
                if (arg.trim().isEmpty()) {
                    throw new InvalidMethodCallFormatException(
                            INVALID_METHOD_CALL_FORMAT_MESSAGE);
                }
                args.add(arg.trim());
            }
        }

        if (args.size() != method.getParameters().size()) {
            throw new InvalidNumberOfArgsInMethodCallException(
                    INVALID_NUMBER_OF_ARGS_IN_METHOD_CALL + methodName);
        }

        for (int i = 0; i < args.size(); i++) {
            String arg = args.get(i);
            Variable param = method.getParameters().get(i);
            // Additional type checking can be implemented here if needed
            if (!VariableValidator.valueMatchType(arg, param.getType(), symbolTable)) {
                throw new InvalidArgumentTypeMismatchMethodException(
                        ARRGUMENT_TYPE_MISMATCH_TO_DECLERATION_MEESAGE + param.getName());
            }
        }
    }
}
