package ex5.parser;

import ex5.model.Scope;

import java.util.Stack;
/** * This class is responsible for handling block scopes during parsing.
 * It maintains a stack of scopes to manage entering and exiting scopes.
 * @author Eilam Soroka, Maayan Felig
 */
public class BlockHandler {
    private static final String INVALID_SCOPE_EXIT_MESSAGE = "No scope to exit";
    private static final String INVALID_SCOPE_FOR_END_FILE_MESSAGE = "Unclosed scopes at end of file";
    private final Stack<Scope> scopeStack = new Stack<Scope>();

    /** Enters a new scope by pushing a new Scope onto the stack.
     * The new scope's parent is the current top scope, or null if the stack is empty.
     */
    public void enterNewScope() {
        Scope newScope;
        if (scopeStack.isEmpty()) {
            newScope = new Scope(null);
        } else {
            newScope = new Scope(scopeStack.peek());
        }
        scopeStack.push(newScope);
    }

    /** Exits the current scope by popping the top Scope from the stack.
     * @throws InvalidScopeException if there is no scope to exit (stack is empty).
     */
    public void exitScope() throws InvalidScopeException{
        if (!scopeStack.isEmpty()) {
            scopeStack.pop();
        }
        else {
            throw new InvalidScopeException(INVALID_SCOPE_EXIT_MESSAGE);
        }
    }

    /** Gets the current scope (top of the stack).
     * @return the current Scope, or null if the stack is empty (global scope).
     */
    public Scope getCurrentScope() {
        if(scopeStack.isEmpty()){
            return null;
        }
        return scopeStack.peek();
    }

    /** Checks if the current scope is the global scope.
     * @return true if the stack is empty (global scope), false otherwise.
     */
    public boolean isGlobalScope() {
        return scopeStack.isEmpty();
    }
    /** Validates that all scopes have been closed at the end of file parsing.
     * @throws InvalidScopeException if there are unclosed scopes (stack is not empty).
     */

    public void endFile() throws InvalidScopeException{
        if(!scopeStack.isEmpty()){
            throw new InvalidScopeException(INVALID_SCOPE_FOR_END_FILE_MESSAGE);
        }
    }
}
