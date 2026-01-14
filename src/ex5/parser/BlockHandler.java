package ex5.parser;

import ex5.model.Scope;

import java.util.Stack;

public class BlockHandler {
    private final Stack<Scope> scopeStack = new Stack<Scope>();

    public void enterNewScope() {
        Scope newScope;
        if (scopeStack.isEmpty()) {
            newScope = new Scope(null);
        } else {
            newScope = new Scope(scopeStack.peek());
        }
        scopeStack.push(newScope);
    }

    public Scope getCurrentScope() {
        return scopeStack.peek();
    }

    public boolean isGlobalScope() {
        return scopeStack.isEmpty();
    }
}
