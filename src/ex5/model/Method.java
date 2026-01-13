package ex5.model;

import java.util.List;

/**
 * Method class
 * wrapper of a method
 * @author Eilam Soroka, Maayan felig
 */
public class Method {
    private String name;
    private final List<Variable> parameters;

    /**
     * constructor of method
     * @param name
     * @param parameters
     */
    public Method(String name, List<Variable> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    /**
     * get name
     * @return String name
     */
    public String getName() {
        return name;
    }
    /**
     * get parameters
     * @return List<Variable> parameters
     */
    public List<Variable> getParameters() {
        return parameters;
    }
}
