package ex5.main;


import ex5.parser.FileHandler;
import ex5.parser.SJavacIOException;
import ex5.parser.SyntaxException;

/**
 * The main class for the SJavac compiler.
 * @author Eilam Soroka, Maayan Felig
 */
public class Sjavac {
    private static final String INVALID_NUMBER_OF_ARGUMENTS_MESSAGE = "Invalid number of arguments";
    private static final int OUTPUT_FOR_SUCSESS = 0;
    private static final int FIRST_INDEX_OF_ARRAY = 0;
    
    private static final int OUTPUT_FOR_ILLEAGEL_CODE = 1;
    
    private static final int OUTPUT_FOR_ILLEAGEL_FILE_PATH_IO_EXCEPTION = 2;

    /**
     * The main method for the SJavac compiler.
     * @param args path to file.
     */
    public static void main(String[] args) {
        try {
            if (args.length != OUTPUT_FOR_ILLEAGEL_CODE) {
                throw new SJavacIOException(INVALID_NUMBER_OF_ARGUMENTS_MESSAGE);
            }

            FileHandler.parseFile(args[FIRST_INDEX_OF_ARRAY]);

            System.out.println(OUTPUT_FOR_SUCSESS);

        } catch (SyntaxException e) {
            System.out.println(OUTPUT_FOR_ILLEAGEL_CODE);
            System.err.println(e.getMessage());
        } catch (SJavacIOException e) {
            System.out.println(OUTPUT_FOR_ILLEAGEL_FILE_PATH_IO_EXCEPTION);
            System.err.println(e.getMessage());
        }



//        boolean variableValidatorTestsPassed = passedBasicVariableValidatorTests();
//        if (variableValidatorTestsPassed) {
//            System.out.println("Variable validator tests passed.");
//        }
//        else{
//            System.out.println("Variable validator tests failed.");
//        }
//        boolean symbolTableTestsPassed = SymbolTableTests.passedBasicSymbolTableTests();
//        if (symbolTableTestsPassed) {
//            System.out.println("Symbol table tests passed.");
//        }
//        else{
//            System.out.println("Symbol table tests failed.");
//        }
//        boolean conditionValidatorTestsPassed = ConditionValidatorTests.passedBasicConditionValidatorTests();
//        if(conditionValidatorTestsPassed){
//            System.out.println("Condition validator tests passed.");
//        }
//        else{
//            System.out.println("Condition validator tests failed.");
//        }
//        System.out.println("Sjavac main method executed.");
//    }
    }
}
