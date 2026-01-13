package ex5.model;

import ex5.parser.BlockHandler;
import ex5.model.IllegalDuplicateException;

import java.util.HashMap;
/**
    SymbolTable class to manage methods and global variables.
    @author Eilam Soroka and Maayan Felig
 */
public class SymbolTable {
    private static final String DUPLICATE_VARIABLE_MESSAGE = "Duplicate global variable name: ";
    private static final String DUPLICATE_METHOD_MESSAGE = "Duplicate method name: ";
    private static final String NON_EXISTING_VARIABLE_MESSAGE = "Variable not found: ";
    private static final String METHOD_NOT_FOUND_MESSAGE = "Method not found: ";
    private static final String[] VARIABLE_TYPES = {"int", "double", "boolean", "char", "String"};
    private final HashMap<String, Method> methodTable;
    private final HashMap<String, Variable> variableTable;
    private final BlockHandler blockHandler;

    /**
        Constructor for SymbolTable
        @param blockHandler BlockHandler to manage scopes
     */
    public SymbolTable(BlockHandler blockHandler) {
        methodTable = new HashMap<>();
        variableTable = new HashMap<>();
        this.blockHandler = blockHandler;
    }

    /**
        Check if method exists in the table. If not, add method to the table.
        @param method to add
        @throws IllegalDuplicateException if method already exists
     */
    public void addMethod(Method method) throws IllegalDuplicateException {
        if (methodTable.containsKey(method.getName())) {
            throw new IllegalDuplicateException(DUPLICATE_METHOD_MESSAGE + method.getName());
        }
        methodTable.put(method.getName(), method);
    }
    public Method getMethod(String methodName) throws IllegalCallingToUnexistingMethodException {
        if (!methodTable.containsKey(methodName)) {
            throw new IllegalCallingToUnexistingMethodException(METHOD_NOT_FOUND_MESSAGE + methodName);
        }
        return methodTable.get(methodName);
    }

    /**
        Check if variable exists in the table. If not, add variable to the table.
        @param variable to add
        @throws IllegalDuplicateException if variable already exists
     */
    public void addGlobalVariable(Variable variable) throws IllegalDuplicateException {
        if (variableTable.containsKey(variable.getName())) {
            throw new IllegalDuplicateException(DUPLICATE_VARIABLE_MESSAGE + variable.getName());
        }
        variableTable.put(variable.getName(), variable);
    }

    /**
        Add local variable to the current scope.
        @param variable to add
        @throws IllegalDuplicateException if variable already exists in the current scope
     */
    public void addLocalVariable(Variable variable) throws IllegalDuplicateException {
        this.blockHandler.getCurrentScope().addLocalVariable(variable);
    }

    /**
        Find local variable in the current scope or any parent scope.
        @param name of the variable to find
        @throws AssignmentToNonExistingVariableException if variable does not exist
     */
    public Variable getLocalVariable(String name) throws AssignmentToNonExistingVariableException{
        Scope currentScope = blockHandler.getCurrentScope();
        while (currentScope != null) {
            if (currentScope.containsVariable(name)) {
                return currentScope.getVariable(name);
            }
            currentScope = currentScope.getParentScope();
        }
        throw new AssignmentToNonExistingVariableException(NON_EXISTING_VARIABLE_MESSAGE+name);
    }

}
