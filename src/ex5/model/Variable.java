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

    public Variable(VariableType type, String name, boolean isFinal) {
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
    }

    public VariableType getType() {
        return type;
    }

    public  String getName() {
        return name;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void assignVal(){
        this.isInitialized = true;
    }
}
