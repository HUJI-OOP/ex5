package ex5.model;

public enum VariableType {
    INT,
    DOUBLE,
    BOOLEAN,
    STRING,
    CHAR;

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

