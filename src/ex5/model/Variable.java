package ex5.model;

/**
 * This class represents a variable with its type, name, initialization status, and finality.
 * @author Eilam Soroka, Maayan Felig
 */
public class Variable {
    private final VariableType type;
    private String name;
    private boolean isInitialized = false;
    private final boolean isFinal;

    /**
     * Constructor for Variable
     * @param type VariableType of the variable
     * @param name Name of the variable
     * @param isFinal Boolean indicating if the variable is final
     */
    public Variable(VariableType type, String name, boolean isFinal) {
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
    }

    /**
     * Get the type of the variable
     * @return VariableType of the variable
     */
    public VariableType getType() {
        return type;
    }

    /**
     * Get the name of the variable
     * @return String name of the variable
     */
    public  String getName() {
        return name;
    }

    /**
     * Check if the variable is initialized
     * @return boolean indicating if the variable is initialized
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Check if the variable is final
     * @return boolean indicating if the variable is final
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Assign a value to the variable, marking it as initialized
     */
    public void assignVal(){
        this.isInitialized = true;
    }
}
