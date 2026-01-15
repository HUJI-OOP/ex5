package ex5.Tests;

import ex5.model.SymbolTable;
import ex5.parser.BlockHandler;
import ex5.validators.MethodValidator;

public class MethodValidatorTests {

    /* -------------------- Test Runner -------------------- */

    public static boolean passedAllMethodDeclarationTests() {
        return
                MethodDeclarationWithoutParametersLegal()
                        && MethodDeclarationIllegalVoidKeyword()
                        && MethodDeclarationIllegalMethodNameStartsWithNumber()
                        && MethodDeclarationWithOneParameterLegal()
                        && MethodDeclarationWithIllegalType()
                        && MethodDeclarationWithTwoParametersLegal()
                        && MethodDeclarationWithIllegalSecondParameter()
                        && MethodDeclarationWithIllegalFirstParameter()
                        && MethodDeclarationDuplicateMethodNameIllegal()
                        && MethodDeclarationWithFinalParameterLegal()
                        && MethodDeclarationWithIllegalFinalPlacement()
                        && MethodDeclarationWithTrailingCommaIllegal()
                        && MethodDeclarationWithDoubleCommaIllegal()
                        && MethodDeclarationWithIllegalUnderscoreName()
                        && MethodDeclarationWhitespaceTortureLegal();
    }

    /* -------------------- Individual Tests -------------------- */

    public static boolean MethodDeclarationWithoutParametersLegal() {
        String example = "void foo() {";
        return expectLegal(example, "MethodDeclarationWithoutParametersLegal");
    }

    public static boolean MethodDeclarationIllegalVoidKeyword() {
        String example = "voi foo() {";
        return expectIllegal(example, "MethodDeclarationIllegalVoidKeyword");
    }

    public static boolean MethodDeclarationIllegalMethodNameStartsWithNumber() {
        String example = "void 1foo() {";
        return expectIllegal(example, "MethodDeclarationIllegalMethodNameStartsWithNumber");
    }

    public static boolean MethodDeclarationWithOneParameterLegal() {
        String example = "void foo(int a) {";
        return expectLegal(example, "MethodDeclarationWithOneParameterLegal");
    }

    public static boolean MethodDeclarationWithIllegalType() {
        String example = "void foo(inti a) {";
        return expectIllegal(example, "MethodDeclarationWithIllegalType");
    }

    public static boolean MethodDeclarationWithTwoParametersLegal() {
        String example = "void foo(int a, String b) {";
        return expectLegal(example, "MethodDeclarationWithTwoParametersLegal");
    }

    public static boolean MethodDeclarationWithIllegalSecondParameter() {
        String example = "void foo(int a, b) {";
        return expectIllegal(example, "MethodDeclarationWithIllegalSecondParameter");
    }

    public static boolean MethodDeclarationWithIllegalFirstParameter() {
        String example = "void foo(int 123, String b) {";
        return expectIllegal(example, "MethodDeclarationWithIllegalFirstParameter");
    }

    public static boolean MethodDeclarationDuplicateMethodNameIllegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);
        try {
            MethodValidator.validateAndRegister("void foo() {", st);
            MethodValidator.validateAndRegister("void foo(int a) {", st);
        } catch (Exception e) {
            return true;
        }
        System.err.println("FAILED: MethodDeclarationDuplicateMethodNameIllegal");
        return false;
    }

    public static boolean MethodDeclarationWithFinalParameterLegal() {
        String example = "void foo(final int a) {";
        return expectLegal(example, "MethodDeclarationWithFinalParameterLegal");
    }

    public static boolean MethodDeclarationWithIllegalFinalPlacement() {
        String example = "void foo(int final a) {";
        return expectIllegal(example, "MethodDeclarationWithIllegalFinalPlacement");
    }

    public static boolean MethodDeclarationWithTrailingCommaIllegal() {
        String example = "void foo(int a,) {";
        return expectIllegal(example, "MethodDeclarationWithTrailingCommaIllegal");
    }

    public static boolean MethodDeclarationWithDoubleCommaIllegal() {
        String example = "void foo(int a,, String b) {";
        return expectIllegal(example, "MethodDeclarationWithDoubleCommaIllegal");
    }

    public static boolean MethodDeclarationWithIllegalUnderscoreName() {
        String example = "void foo(int __a) {";
        return expectIllegal(example, "MethodDeclarationWithIllegalUnderscoreName");
    }

    public static boolean MethodDeclarationWhitespaceTortureLegal() {
        String example = "   void    foo   (   int   a   ,   String b   )    {   ";
        return expectLegal(example, "MethodDeclarationWhitespaceTortureLegal");
    }

    /* -------------------- Helpers -------------------- */

    private static boolean expectLegal(String example, String testName) {
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try {
            MethodValidator.validateAndRegister(example, symbolTable);
        } catch (Exception e) {
            System.err.println("FAILED: " + testName);
            System.err.println("Reason: " + e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean expectIllegal(String example, String testName) {
        BlockHandler blockHandler = new BlockHandler();
        SymbolTable symbolTable = new SymbolTable(blockHandler);
        try {
            MethodValidator.validateAndRegister(example, symbolTable);
        } catch (Exception e) {
            return true;
        }
        System.err.println("FAILED: " + testName);
        return false;
    }


    public static boolean passedAllMethodCallTests() {
        return
                MethodCallNoParametersLegal()
                        && MethodCallOneParameterLegal()
                        && MethodCallTwoParametersLegal()
                        && MethodCallWrongNumberOfArgsIllegal()
                        && MethodCallWrongTypeIllegal()
                        && MethodCallUndefinedMethodIllegal()
                        && MethodCallTrailingCommaIllegal()
                        && MethodCallDoubleCommaIllegal()
                        && MethodCallWhitespaceTortureLegal();
    }

    /* -------------------- Individual Tests -------------------- */

    public static boolean MethodCallNoParametersLegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister("void foo() {", st);
            MethodValidator.validateMethodExists("foo();", st);
        } catch (Exception e) {
            System.err.println("FAILED: MethodCallNoParametersLegal");
            System.err.println("Reason: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean MethodCallOneParameterLegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister("void foo(int a) {", st);
            MethodValidator.validateMethodExists("foo(5);", st);
        } catch (Exception e) {
            System.err.println("FAILED: MethodCallOneParameterLegal");
            System.err.println("Reason: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean MethodCallTwoParametersLegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister("void foo(int a, String b) {", st);
            MethodValidator.validateMethodExists("foo(5, \"hi\");", st);
        } catch (Exception e) {
            System.err.println("FAILED: MethodCallTwoParametersLegal");
            System.err.println("Reason: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean MethodCallWrongNumberOfArgsIllegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister("void foo(int a) {", st);
            MethodValidator.validateMethodExists("foo();", st);
        } catch (Exception e) {
            return true;
        }
        System.err.println("FAILED: MethodCallWrongNumberOfArgsIllegal");
        return false;
    }

    public static boolean MethodCallWrongTypeIllegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister("void foo(int a) {", st);
            MethodValidator.validateMethodExists("foo(\"hello\");", st);
        } catch (Exception e) {
            return true;
        }
        System.err.println("FAILED: MethodCallWrongTypeIllegal");
        return false;
    }

    public static boolean MethodCallUndefinedMethodIllegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateMethodExists("foo();", st);
        } catch (Exception e) {
            return true;
        }
        System.err.println("FAILED: MethodCallUndefinedMethodIllegal");
        return false;
    }

    public static boolean MethodCallTrailingCommaIllegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister("void foo(int a) {", st);
            MethodValidator.validateMethodExists("foo(5,);", st);
        } catch (Exception e) {
            return true;
        }
        System.err.println("FAILED: MethodCallTrailingCommaIllegal");
        return false;
    }

    public static boolean MethodCallDoubleCommaIllegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister("void foo(int a, int b) {", st);
            MethodValidator.validateMethodExists("foo(1,,2);", st);
        } catch (Exception e) {
            return true;
        }
        System.err.println("FAILED: MethodCallDoubleCommaIllegal");
        return false;
    }

    public static boolean MethodCallWhitespaceTortureLegal() {
        BlockHandler bh = new BlockHandler();
        SymbolTable st = new SymbolTable(bh);

        try {
            MethodValidator.validateAndRegister(
                    "   void   foo   (   int a , String b   )   {", st);
            MethodValidator.validateMethodExists(
                    "   foo   (   5   ,   \"hi\"   )   ;", st);
        } catch (Exception e) {
            System.err.println("FAILED: MethodCallWhitespaceTortureLegal");
            System.err.println("Reason: " + e.getMessage());
            return false;
        }
        return true;
    }
    /* -------------------- Main -------------------- */

    public static void main(String[] args) {
        boolean passed = passedAllMethodDeclarationTests();
        if (passed) {
            System.out.println("All MethodValidator declaration tests PASSED.");
        } else {
            System.out.println("Some MethodValidator declaration tests FAILED.");
        }
        passed = passedAllMethodCallTests();
        if (passed) {
            System.out.println("All MethodValidator call tests PASSED.");
        } else {
            System.out.println("Some MethodValidator call tests FAILED.");
        }
    }
}
