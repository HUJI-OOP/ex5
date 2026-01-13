package ex5.model;

import ex5.model.IllegalDuplicateException;

import java.util.HashMap;

public class Scope {
    private static final String LOCAL_DUPLICATE_VARIABLE_MESSAGE = "Local variable with the same name: " +
            "already exists in this scope.";
    private final Scope parentScope;
    private final HashMap<String, Variable> localVariables;
    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.localVariables = new HashMap<>();
    }

    public void addLocalVariable(Variable variable) throws IllegalDuplicateException {
        if (localVariables.containsKey(variable.getName())) {
            throw new IllegalDuplicateException(LOCAL_DUPLICATE_VARIABLE_MESSAGE + variable.getName());
        }
        localVariables.put(variable.getName(), variable);
    }

    public boolean containsVariable(String name) {
        return localVariables.containsKey(name);
    }
    public Variable getVariable(String name) {
        return localVariables.get(name);
    }

    public Scope getParentScope() {
        return parentScope;
    }
}
