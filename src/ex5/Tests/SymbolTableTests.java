package ex5.Tests;

import ex5.model.SymbolTable;
import ex5.model.Variable;
import ex5.model.VariableType;
import ex5.parser.BlockHandler;

public class SymbolTableTests {

    public static boolean passedBasicSymbolTableTests() {
        return addGlobalVariableTest() && addLocalVariableTest();
    }

    public static boolean addGlobalVariableTest() {
        Variable variable = new Variable(VariableType.convertFromString("int"), "x", false);
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            symbolTable.addVariable(variable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        try{
            Variable retrievedVariable = symbolTable.getVariable("x");
            return retrievedVariable != null && retrievedVariable.getName().equals("x") && retrievedVariable.getType() == VariableType.INT;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean addLocalVariableTest() {
        Variable variable = new Variable(VariableType.convertFromString("double"), "y", false);
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        blockHandler.enterNewScope();
        try{
            symbolTable.addVariable(variable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        try{
            Variable retrievedVariable = symbolTable.getVariable("y");
            return retrievedVariable != null && retrievedVariable.getName().equals("y") && retrievedVariable.getType() == VariableType.DOUBLE;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
}
