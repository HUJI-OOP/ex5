package ex5.Tests;

import ex5.model.SymbolTable;
import ex5.parser.BlockHandler;
import ex5.validators.VariableValidator;

public class VariableValidatorTests {
    public static boolean passedBasicVariableValidatorTests(){
        return singleInitializatinTest() &&
                declarationWithoutInitializationTest() &&
                twoDeclarationsWithInitializationsTest() &&
                twoDeclarationsWithoutInitializationsTest() &&
                mixedDeclarationsTest() &&
                illegalVariableNameTest() &&
                invalidInitializationValueTest() &&
                invalidDeclarationFormatTest() &&
                nonInitializedFinalVariableTest() &&
                initializedFinalVariableTest() &&
                duplicateVariableTest() &&
                assignmentToFinalVariableTest() &&
                assignmentToNonFinalVariableTest() &&
                assignmentFromExistingVariableTest() &&
                assignmentFromNonExistingVariableTest() &&
                assignmentFromFinalVariableTest() &&
                assignmentTypeMismatchTest();
    }

    public static boolean singleInitializatinTest(){
        String example = "int a = 5;";;
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean declarationWithoutInitializationTest(){
        String example = "double b;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean twoDeclarationsWithInitializationsTest(){
        String example = "boolean c = true, d = false;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean twoDeclarationsWithoutInitializationsTest(){
        String example = "char e, f;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean mixedDeclarationsTest(){
        String example = "String g = \"hello\", h;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean illegalVariableNameTest(){
        String example = "int 1a = 5;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean invalidInitializationValueTest(){
        String example = "double b = true;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean invalidDeclarationFormatTest(){
        String example = "boolean c true;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean nonInitializedFinalVariableTest(){
        String example = "final int x;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean initializedFinalVariableTest(){
        String example = "final int x = 10;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean duplicateVariableTest(){
        String example1 = "int a = 5;";
        String example2 = "int a = 10;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(example1, symbolTable);
            VariableValidator.validateVariableDeclaration(example2, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean assignmentToFinalVariableTest(){
        String declaration = "final int x = 10;";
        String assignment = "x = 20;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(declaration, symbolTable);
            VariableValidator.validateVariableAssignment(assignment, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean assignmentToNonFinalVariableTest(){
        String declaration = "int y = 15;";
        String assignment = "y = 25;";
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            VariableValidator.validateVariableDeclaration(declaration, symbolTable);
            VariableValidator.validateVariableAssignment(assignment, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean assignmentFromExistingVariableTest(){
        String declaration1 = "int a = 5;";
        String declaration2 = "int b;";
        String assignment = "b = a;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(declaration1, symbolTable);
            variableValidator.validateVariableDeclaration(declaration2, symbolTable);
            variableValidator.validateVariableAssignment(assignment, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static  boolean assignmentFromNonExistingVariableTest(){
        String declaration = "int b;";
        String assignment = "b = a;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(declaration, symbolTable);
            variableValidator.validateVariableAssignment(assignment, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean assignmentFromFinalVariableTest(){
        String declaration1 = "final int a = 5;";
        String declaration2 = "int b;";
        String assignment = "b = a;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try {
            variableValidator.validateVariableDeclaration(declaration1, symbolTable);
            variableValidator.validateVariableDeclaration(declaration2, symbolTable);
            variableValidator.validateVariableAssignment(assignment, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean assignmentTypeMismatchTest(){
        String declaration = "int a = 5;";
        String assignment = "a = true;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(declaration, symbolTable);
            variableValidator.validateVariableAssignment(assignment, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }


}
