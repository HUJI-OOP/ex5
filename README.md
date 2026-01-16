eilam.soroka,yellowcow598
209091230,318854429

Two Regex Usage examples in the Project - 
1. Method Declaration Pattern: "^\\s*void\\s+([a-zA-Z]\\w*)\\s*\\(([^)]*)\\)\\s*\\{\\s*$"
This pattern matches a method declaration line in s-Java. It verifies that the line follows the correct 
syntax and extracts the method name and parameter list for further validation.
Structure Explanation:
   * `^\\s*`
     Matches the beginning of the line and allows optional leading whitespace.
   * `void\\s+`
     Matches the keyword `void` followed by at least one space.
     This enforces that all methods return `void`, as required by the language.
   * `([a-zA-Z]\\w*)`
     Capturing group for the method name. The name must start with a letter and may include letters, 
     digits, or underscores. Capturing the name allows referencing it later for method calls.
   * `\\s*\\(`
     Allows optional whitespace and matches the opening parenthesis of the parameter list.
   * `([^)]*)`
     Capturing group for the entire parameter list as a raw string.
     Everything inside the parentheses is captured and validated later.
   * `\\)\\s*\\{`
     Matches the closing parenthesis followed by the opening curly brace `{`, allowing whitespace in between.
   * `\s*$`
     Allows trailing whitespace and enforces the end of the line.

2. Condition Expression Pattern: "^\\s*[^&|\\s]+(\\s*(\\|\\s*\\||&\\s*&)\\s*[^&|\\s]+)*\\s*$"
This pattern validates the format of boolean conditions inside `if` and `while` statements
It ensures that logical operators are used correctly and placed only between valid condition elements.
Structure Explanation:
   * `^\\s*`
     Matches the beginning of the line and allows optional leading whitespace.
   * `[^&|\\s]+`
     Matches a single atomic condition. An atom may be a variable name or a literal and cannot contain `&`,
     `|`, or whitespace. 
   * `(\\s*(\\|\\s*\\||&\\s*&)\s*[^&|\\s]+)*`
     Allows zero or more repetitions of a logical operator `||` or `&&` with optional whitespace followed 
     by another atomic condition. This ensures operators always appear between valid operands.
   * `\\s*$`
     Allows trailing whitespace and enforces the end of the line.

Project Structure and Module Responsibilities - 
The program is built as a two-phase s-Java verifier with clear separation between reading and controlling 
the flow (parser package), validating the language rules (validator package) and managing program states and 
objects (model package). In addition, regex patterns are centralized in the patterns package and the 
package 'main' handles input, output, and exit codes.
Module responsibilities are as follows:
1. Sjavac (ex5.main) - Entry point, handles command-line arguments, calls FileParser, manages exit codes.
2. FileHandler (ex5.parser) - Coordinates reading the file with two-pass validation, enforcing structural 
   rules.
3. LineHandler (ex5.parser) - Classifies lines as variable declarations, method declarations, assignments, 
   returns, method calls, conditions, or block delimiters.
4. LineType (ex5.parser) - Enum representing different line types.
5. BlockHandler (ex5.parser) - Manages scope blocks and nesting.
6. SymbolTable (ex5.model) - Keeps track of declared variables and methods.
7. Scope (ex5.model) - Represents a single scope block with its local variables.
8. Variable (ex5.model) - Represents a variable with its type, name, final status, and initialization state.
9. Method (ex5.model) - Represents a method with its name, return type, and parameters.
10. VarType (ex5.model) - Enum representing variable types and their compatibility.
11. VariableValidator (ex5.validator) - Validates variable declarations and assignments.
12. MethodValidator (ex5.validator) - Validates method declarations and calls.
13. ConditionValidator (ex5.validator) - Validates conditions in if and while statements.
14. RegexRepository (ex5.patterns) - Centralizes regex patterns.


