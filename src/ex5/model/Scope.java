package ex5.model;

import ex5.model.IllegalDuplicateException;

import java.util.HashMap;

/**
 * Represents a scope which can contain local variables and has a reference to its parent scope.
 * @author Eilam Soroka, Maayan Felig
 */
public class Scope {
    private static final String LOCAL_DUPLICATE_VARIABLE_MESSAGE = "Local variable with the same name: " +
            "already exists in this scope.";
    private final Scope parentScope;
    private final HashMap<String, Variable> localVariables;
    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.localVariables = new HashMap<>();
    }

    /**
     * Adds a local variable to the scope.
     * @param variable The variable to add.
     * @throws IllegalDuplicateException if a variable with the same name already exists in the scope.
     */
    public void addLocalVariable(Variable variable) throws IllegalDuplicateException {
        if (localVariables.containsKey(variable.getName())) {
            throw new IllegalDuplicateException(LOCAL_DUPLICATE_VARIABLE_MESSAGE + variable.getName());
        }
        localVariables.put(variable.getName(), variable);
    }

    /**
     * Checks if a variable with the given name exists in the scope.
     * @param name The name of the variable to check.
     * @return true if the variable exists, false otherwise.
     */
    public boolean containsVariable(String name) {
        return localVariables.containsKey(name);
    }

    /**
     * Retrieves a variable by its name from the scope.
     * @param name The name of the variable to retrieve.
     * @return The variable if found, null otherwise.
     */
    public Variable getVariable(String name) {
        return localVariables.get(name);
    }

    /**
     * Gets the parent scope of this scope.
     * @return The parent scope.
     */
    public Scope getParentScope() {
        return parentScope;
    }
}
