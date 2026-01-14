package ex5.main;

import ex5.model.SymbolTable;
import ex5.model.Variable;
import ex5.model.VariableType;
import ex5.parser.BlockHandler;
import ex5.validators.ConditionValidator;

public class ConditionValidatorTests {

    public static boolean passedBasicConditionValidatorTests(){
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        Variable a = new Variable(VariableType.convertFromString("int"), "a", false);
        Variable b = new Variable(VariableType.convertFromString("int"), "b", false);
        Variable c = new Variable(VariableType.convertFromString("int"), "c", false);
        try{
            symbolTable.addVariable(a);
            symbolTable.addVariable(b);
            symbolTable.addVariable(c);
        }
        catch (Exception e){
            System.err.println("Problem with test - cannot add variables to symbol table: " + e.getMessage());
            return false;
        }
        return allIncorrectInternalConditionFormats(symbolTable) &&
               allCorrectInternalConditionFormats(symbolTable) &&
               allIncorrectConditionFullLineStructures(symbolTable) &&
               allCorrectConditionFullLineStructures(symbolTable) &&
               allIncorrectConditionParts(symbolTable) &&
               allCorrectConditionParts(symbolTable);
    }

    public static boolean allCorrectConditionFullLineStructures(SymbolTable symbolTable){
        String[] correctConditionFullLineStructures = {
                "if (a && b) {",
                "while (a || b) {",
                "if (a && b && c) {",
                "while (a || b || c) {",
                "if (a && b || c) {",
                "while (a || b && c) {"
        };
        for (String structure : correctConditionFullLineStructures) {
            try{
                detectIncorrectFormat(structure, symbolTable);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean allIncorrectConditionFullLineStructures(SymbolTable symbolTable){
        String[] incorrectConditionFullLineStructures = {
                "if a && b) {",
                "while (a || b {",
                "if (a && b && c) ",
                "while a || b || c) {",
                "if a && b || c {",
                "while (a || b && c) "
        };
        for (String structure : incorrectConditionFullLineStructures) {
            if(!detectIncorrectFormat(structure, symbolTable)){
                return false;
            }
        }
        return true;
    }

    public static boolean allCorrectInternalConditionFormats(SymbolTable symbolTable) {
        String[] correctConditionFormats = {"if(a && b){", "while(a || b){", "if(a && b && c){",
                "while(a || b || c){", "if(a && b || c){", "while(a || b && c){", "if(   a && b   ){",
                "while(a&   &b){", "if(a |    |b){"};
        for (String format : correctConditionFormats) {
            ConditionValidator conditionValidator = new ConditionValidator();
            try{
                conditionValidator.validateConditionLine(format, symbolTable);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean allIncorrectInternalConditionFormats(SymbolTable symbolTable) {
        String[] incorrectConditionFormats = {"if(){", "while(    ){", "if(a &&){", "while(|| b){",
                "if(a && || b){", "if(a & b){", "while(a | b){","if(a && b ||){", "while(&& a || b){",
                "if(a &&& b){", "while(a ||| b){"};
        for (String format : incorrectConditionFormats) {
            if(!detectIncorrectFormat(format, symbolTable)){
                return false;
            }
        }
        return true;
    }

    public static boolean allCorrectConditionParts(SymbolTable symbolTable) {
        String[] correctConditionParts = {"true", "false", "123", "45.67"};
        for (String part : correctConditionParts) {
            ConditionValidator conditionValidator = new ConditionValidator();
            try{
                conditionValidator.validateConditionLine("if(" + part + "){", symbolTable);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean allIncorrectConditionParts(SymbolTable symbolTable) {
        String[] incorrectConditionParts = {"abc", "12a3", "45.67.89", "'c'", "\"string\""};
        for (String part : incorrectConditionParts) {
            if(!detectIncorrectFormat("if(" + part + "){", symbolTable)){
                return false;
            }
        }
        return true;
    }

    public static boolean detectIncorrectFormat(String incorrectFormat, SymbolTable symbolTable) {
        ConditionValidator conditionValidator = new ConditionValidator();
        try{
            conditionValidator.validateConditionLine(incorrectFormat, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }
}
