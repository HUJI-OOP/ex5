package ex5.parser;

import ex5.model.SymbolTable;
import ex5.patterns.RegexPatterns;
import ex5.validators.ConditionValidator;
import ex5.validators.MethodValidator;
import ex5.validators.VariableValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles reading and parsing a file line by line.
 * @author Eilam Soroka, Maayan Felig
 */
public class FileHandler {

    private static final String IO_ERROR_MESSAGE = "IO error while reading file: ";
    private static final String SYNTAX_ERROR_MESSAGE = "Syntax error at line: <lineNumber>: ";
    private static final String LINE_NUMBER_PLACEHOLDER = "<lineNumber>";
    private static final String ILLEGAL_SCOPE_FOR_METHOD_DECLARATION_MESSAGE = "Method declarations are " +
            "only allowed in the global scope";
    private static final String ILLEGAL_SCOPE_FOR_CONDITION_DECLARATION_MESSAGE = "if and while " +
            "conditions are not allowed in the global scope";
    private static final String ILLEGAL_SCOPE_FOR_METHOD_CALL_MESSAGE = "Method calls are not allowed in " +
            "global scope";
    private static final String INVALID_RETURN_MESSAGE = "Return statements are not allowed in global scope";
    private static final String INVALID_SCOPE_CLOSE_MESSAGE = "No open scope to close";
    private static final String LINE_NOT_CLASSIFIABLE_MESSAGE = "Line does not match any valid line pattern";
    private static final String SCOPE_NOT_CLOSED_MESSAGE = "Reached end of file without closing all scopes";

    /**
     * Parses a file line by line, updating the symbol table and block handler accordingly.
     * @param file the path to the file to parse
     * @throws SJavacIOException if an IO error occurs while reading the file
     * @throws SyntaxException if a syntax error is encountered during parsing
     */
    public static void parseFile(String file) throws SJavacIOException, SyntaxException {
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        int lineNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                parseLine(line, symbolTable, blockHandler);
            }
            if(!blockHandler.isGlobalScope()){
                throw new InvalidScopeException(SCOPE_NOT_CLOSED_MESSAGE);
            }

        } catch (IOException e) {
            throw new SJavacIOException(IO_ERROR_MESSAGE + e);
        }
        catch (SyntaxException e) {
            throw new SyntaxException(SYNTAX_ERROR_MESSAGE.replaceFirst(LINE_NUMBER_PLACEHOLDER,
                                                            Integer.toString(lineNumber)) + e.getMessage());
        }

    }

    private static void parseLine(String line, SymbolTable symbolTable, BlockHandler blockHandler)
                                                                                    throws SyntaxException{
        Pattern ignorePattern = Pattern.compile(String.valueOf(RegexPatterns.LINE_TO_IGNORE));
        Matcher ignoreMatcher = ignorePattern.matcher(line);
        if (ignoreMatcher.matches()) {
            return;
        }
        LineType lineType = LineHandler.classifyLine(line);
        switch (lineType) {
            case METHOD_DECLARATION -> methodDeclarationHandler(line, symbolTable, blockHandler);
            case IF_WHILE_CONDITION -> conditionStatementHandler(line, symbolTable, blockHandler);
            case VARIABLE_DECLARATION -> variableDeclarationHandler(line, symbolTable);
            case ASSIGNMENT -> variableAssignmentHandler(line, symbolTable);
            case METHOD_CALL -> methodCallHandler(line, symbolTable, blockHandler);
            case RETURN -> returnHandler(blockHandler);
            case SCOPE_CLOSE -> scopeCloseHandler(blockHandler);
            default -> throw new InvalidLineInputException(LINE_NOT_CLASSIFIABLE_MESSAGE);
        }

    }

    private static void methodDeclarationHandler(String line, SymbolTable symbolTable,
                                                 BlockHandler blockHandler) throws SyntaxException {
        if(!blockHandler.isGlobalScope()){
            throw new InvalidScopeException(ILLEGAL_SCOPE_FOR_METHOD_DECLARATION_MESSAGE);
        }
        MethodValidator.validateAndRegister(line, symbolTable);
        blockHandler.enterNewScope();
    }

    private static void conditionStatementHandler(String line, SymbolTable symbolTable,
                                               BlockHandler blockHandler) throws SyntaxException {
        if(blockHandler.isGlobalScope()){
            throw new InvalidScopeException(ILLEGAL_SCOPE_FOR_CONDITION_DECLARATION_MESSAGE);
        }
        ConditionValidator.validateConditionLine(line, symbolTable);
        blockHandler.enterNewScope();
    }

    private static void variableDeclarationHandler(String line, SymbolTable symbolTable)
                                                                                throws SyntaxException {
        VariableValidator.validateVariableDeclaration(line, symbolTable);
    }

    private static void variableAssignmentHandler(String line, SymbolTable symbolTable)
                                                                                throws SyntaxException {
        VariableValidator.validateVariableAssignment(line, symbolTable);
    }

    private static void methodCallHandler(String line, SymbolTable symbolTable, BlockHandler blockHandler)
                                                                                throws SyntaxException {
        if(blockHandler.isGlobalScope()){
            throw new InvalidScopeException(ILLEGAL_SCOPE_FOR_METHOD_CALL_MESSAGE);
        }
        MethodValidator.validateMethodExists(line, symbolTable);
    }
    
    private static void returnHandler(BlockHandler blockHandler)
                                                                                throws SyntaxException {
        if(blockHandler.isGlobalScope()){
            throw new InvalidScopeException(INVALID_RETURN_MESSAGE);
        }
    }

    private static void scopeCloseHandler(BlockHandler blockHandler)
                                                                                throws SyntaxException {
        if(blockHandler.isGlobalScope()){
            throw new InvalidScopeException(INVALID_SCOPE_CLOSE_MESSAGE);
        }
        blockHandler.exitScope();
    }
}
