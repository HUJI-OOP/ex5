package ex5.main;


import ex5.model.SymbolTable;
import ex5.parser.BlockHandler;
import ex5.validators.VariableValidator;

import static ex5.main.VariableValidatorTests.passedBasicVariableValidatorTests;

public class Sjavac {
    public  static void main(String[] args) {
        boolean variableValidatorTestsPassed = passedBasicVariableValidatorTests();
        if (variableValidatorTestsPassed) {
            System.out.println("Variable validator tests passed.");
        }
        else{
            System.out.println("Variable validator tests failed.");
        }
        boolean symbolTableTestsPassed = SymbolTableTests.passedBasicSymbolTableTests();
        if (symbolTableTestsPassed) {
            System.out.println("Symbol table tests passed.");
        }
        else{
            System.out.println("Symbol table tests failed.");
        }
        System.out.println("Sjavac main method executed.");
    }

    }
