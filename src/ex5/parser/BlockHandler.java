package ex5.parser;

import ex5.model.Scope;

import java.util.Stack;

public class BlockHandler {
    private static final String INVALID_SCOPE_EXIT_MESSAGE = "No scope to exit";
    private static final String INVALID_SCOPE_FOR_END_FILE_MESSAGE = "Unclosed scopes at end of file";
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

    public void exitScope() throws InvalidScopeException{
        if (!scopeStack.isEmpty()) {
            scopeStack.pop();
        }
        else {
            throw new InvalidScopeException(INVALID_SCOPE_EXIT_MESSAGE);
        }
    }

    public Scope getCurrentScope() {
        if(scopeStack.isEmpty()){
            return null;
        }
        return scopeStack.peek();
    }

    public boolean isGlobalScope() {
        return scopeStack.isEmpty();
    }

    public void endFile() throws InvalidScopeException{
        if(!scopeStack.isEmpty()){
            throw new InvalidScopeException(INVALID_SCOPE_FOR_END_FILE_MESSAGE);
        }
    }
}
