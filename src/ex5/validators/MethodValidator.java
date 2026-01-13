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
            for (String param : paramsGroup.split(",")) {
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

    public static void validateMethodExists(String methodName, SymbolTable symbolTable) throws SyntaxException {

    }
}
