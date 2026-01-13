package ex5.model;

import java.util.HashMap;

public class Scope {
    private final Scope parentScope;
    private final HashMap<String, Variable> localVariables;
    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.localVariables = new HashMap<>();
    }

    public void declareVariable(Variable variable) {
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
