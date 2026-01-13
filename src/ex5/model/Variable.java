package ex5.model;

public class Variable {
    private final String type;
    private String name;
    private boolean isInitialized = false;
    private final boolean isFinal;

    public Variable(String type, String name, boolean isFinal) {
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
    }

    public String getType() {
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
