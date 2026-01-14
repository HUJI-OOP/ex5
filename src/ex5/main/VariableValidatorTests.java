package ex5.main;

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
                duplicateVariableTest();
    }

    public static boolean singleInitializatinTest(){
        String example = "int a = 5;";;
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean declarationWithoutInitializationTest(){
        String example = "double b;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean twoDeclarationsWithInitializationsTest(){
        String example = "boolean c = true, d = false;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean twoDeclarationsWithoutInitializationsTest(){
        String example = "char e, f;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean mixedDeclarationsTest(){
        String example = "String g = \"hello\", h;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean illegalVariableNameTest(){
        String example = "int 1a = 5;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean invalidInitializationValueTest(){
        String example = "double b = true;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean invalidDeclarationFormatTest(){
        String example = "boolean c true;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean nonInitializedFinalVariableTest(){
        String example = "final int x;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

    public static boolean initializedFinalVariableTest(){
        String example = "final int x = 10;";
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example, symbolTable);
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
        VariableValidator variableValidator = new VariableValidator();
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try{
            variableValidator.validateVariableDeclaration(example1, symbolTable);
            variableValidator.validateVariableDeclaration(example2, symbolTable);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return true;
        }
        return false;
    }

}
