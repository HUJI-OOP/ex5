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
    private final HashMap<String, Method> methodTable;
    private final HashMap<String, Variable> globalVariableTable;
    private final BlockHandler blockHandler;

    /**
        Constructor for SymbolTable
        @param blockHandler BlockHandler to manage scopes
     */
    public SymbolTable(BlockHandler blockHandler) {
        methodTable = new HashMap<>();
        globalVariableTable = new HashMap<>();
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
    /**
        Get method by name.
        @param methodName name of the method to get
        @throws IllegalCallingToUnexistingMethodException if method does not exist
     */
    public Method getMethod(String methodName) throws IllegalCallingToUnexistingMethodException {
        if (!methodTable.containsKey(methodName)) {
            throw new IllegalCallingToUnexistingMethodException(METHOD_NOT_FOUND_MESSAGE + methodName);
        }
        return methodTable.get(methodName);
    }

    /**
        Check if whether variable is global or local and add to the appropriate table.
        @param variable to add
        @throws IllegalDuplicateException if variable already exists in the appropriate scope
     */
    public void addVariable(Variable variable) throws IllegalDuplicateException {
        if(this.blockHandler.isGlobalScope()){
            addGlobalVariable(variable);
        }
        else{
            addLocalVariable(variable);
        }
    }

    private void addGlobalVariable(Variable variable) throws IllegalDuplicateException {
        if (globalVariableTable.containsKey(variable.getName())) {
            throw new IllegalDuplicateException(DUPLICATE_VARIABLE_MESSAGE + variable.getName());
        }
        globalVariableTable.put(variable.getName(), variable);
    }

    private void addLocalVariable(Variable variable) throws IllegalDuplicateException {
        this.blockHandler.getCurrentScope().addLocalVariable(variable);
    }

    /**
        Find local variable in the current scope or any parent scope.
        @param name of the variable to find
        @throws AssignmentToNonExistingVariableException if variable does not exist
     */
    public Variable getVariable(String name) throws AssignmentToNonExistingVariableException{
        Scope currentScope = blockHandler.getCurrentScope();
        while (currentScope != null) {
            if (currentScope.containsVariable(name)) {
                return currentScope.getVariable(name);
            }
            currentScope = currentScope.getParentScope();
        }
        if (globalVariableTable.containsKey(name)) {
            return globalVariableTable.get(name);
        }
        throw new AssignmentToNonExistingVariableException(NON_EXISTING_VARIABLE_MESSAGE+name);
    }

}
