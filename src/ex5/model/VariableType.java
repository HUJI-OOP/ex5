package ex5.model;

/**
 * Enum representing variable types and their compatibility rules.
 * @author Eilam Soroka, Maayan Felig
 */
public enum VariableType {
    INT,
    DOUBLE,
    BOOLEAN,
    STRING,
    CHAR;

    /**
     * Converts a string representation of a type to its corresponding VariableType enum.
     * @param type the string representation of the type
     * @return the corresponding VariableType enum, or null if the type is unrecognized
     */
    public static VariableType convertFromString(String type) {
        switch (type) {
            case "int":
                return INT;
            case "double":
                return DOUBLE;
            case "boolean":
                return BOOLEAN;
            case "String":
                return STRING;
            case "char":
                return CHAR;
            default:
                return null;
        }
    }

    /**
     * Determines if this VariableType can accept a value of another VariableType.
     * @param other the other VariableType to check compatibility with
     * @return true if this VariableType can accept the other, false otherwise
     */
    public boolean canAccept(VariableType other) {
        if (this == other) {
            return true;
        }

        if (this == DOUBLE && other == INT) {
            return true;
        }

        if (this == BOOLEAN && (other == INT || other == DOUBLE)) {
            return true;
        }

        return false;
    }
}

