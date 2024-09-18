package org.example;

import org.junit.Before;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Test;

public class CalculatorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(originalOut);
    }

    private void initCalculator(){
        Calculator calculator = new Calculator();
        calculator.run();
    }

    //Addition Testing
    @Test
    public void testTwoPositiveNumAddition() {
        // Simulate user input: 1 (Addition), 5, 3, 0 (Exit)
        String input = "1\n5\n3\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Redirect input stream

        // Create the calculator instance and run it
        // Run the calculator
        initCalculator(); // Run the calculator
        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("5 + 3 = 8")); // Check if the addition result is printed
        assertTrue(output.contains("Exiting the calculator. Goodbye!")); // Check if the exit message is printed
    }

    @Test
    public void testTwoNegativeNumAddition() {
        // Simulate user input: 1 (Addition), 5, 3, 0 (Exit)
        String input = "1\n-4\n-6\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Redirect input stream

        // Create the calculator instance and run it
        // Run the calculator
        initCalculator(); // Run the calculator
        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("-4 + -6 = -10")); // Check if the addition result is printed
        assertTrue(output.contains("Exiting the calculator. Goodbye!")); // Check if the exit message is printed
    }


    //Invalid Testing
    @Test
    public void testInvalidInput() {
        // Simulate user input: 10 (Invalid option), 0 (Exit)
        String input = "10\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Redirect input stream
        // Create the calculator instance and run it
        initCalculator(); // Run the calculator
        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid choice. Please select a valid option.")); // Check for invalid input message
        assertTrue(output.contains("Exiting the calculator. Goodbye!")); // Check if the exit message is printed
    }

    //Subtraction Testing
    @Test
    public void testTwoPositiveNumSubtraction() {

        String input = "2\n10\n4\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Redirect input stream

        // Create the calculator instance and run it
        // Run the calculator
        initCalculator();
        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("10 - 4 = 6")); // Check if the addition result is printed
        assertTrue(output.contains("Exiting the calculator. Goodbye!")); // Check if the exit message is printed
    }

    @Test
    public void testSubtractionASmallerNumberFromALargerNumber() {

        String input = "2\n5\n7\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Redirect input stream

        // Create the calculator instance and run it
        // Run the calculator
        initCalculator();
        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("5 - 7 = -2")); // Check if the addition result is printed
        assertTrue(output.contains("Exiting the calculator. Goodbye!")); // Check if the exit message is printed
    }


    @Test
    public void testMultiplicationWithTwoPositive() {
        String input = "3\n6\n7\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Initialize the calculator or any necessary setup
        initCalculator();

        // Capture the output
        String output = outputStream.toString();

        // Verify the output
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));

        // Check if the multiplication result is not positive
        String resultLine = output.lines()
                .filter(line -> line.contains(" * "))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Multiplication result line not found in the output."));

        // Extract the result from the line, assuming the format is "a * b = result"
        String[] parts = resultLine.split("=");
        if (parts.length < 2) {
            throw new AssertionError("Result format is incorrect in the output.");
        }

        String resultString = parts[1].trim();

        try {
            int result = Integer.parseInt(resultString);
            assertTrue("Expected result to be non-positive but got: " + result, result >= 0);
        } catch (NumberFormatException e) {
            throw new AssertionError("Result is not a valid integer: " + resultString);
        }
    }

    @Test
    public void testMultiplicationWithZero(){

        String input = "3\n8\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Redirect input stream

        // Create the calculator instance and run it
        // Run the calculator
        initCalculator();
        // Verify the output
        String output = outputStream.toString();
        assertTrue(output.contains("8 * 0 = 0")); // Check if the addition result is printed
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }


    @Test
    public void testMultiplicationWithTwoNegative() {
        String input = "3\n-2\n-4\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Initialize the calculator or any necessary setup
        initCalculator();

        // Capture the output
        String output = outputStream.toString();

        // Verify the output
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));

        // Check if the multiplication result is not positive
        String resultLine = output.lines()
                .filter(line -> line.contains(" * "))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Multiplication result line not found in the output."));

        // Extract the result from the line, assuming the format is "a * b = result"
        String[] parts = resultLine.split("=");
        if (parts.length < 2) {
            throw new AssertionError("Result format is incorrect in the output.");
        }

        String resultString = parts[1].trim();

        try {
            int result = Integer.parseInt(resultString);
            assertTrue("Expected result to be non-positive but got: " + result, result >= 0);
        } catch (NumberFormatException e) {
            throw new AssertionError("Result is not a valid integer: " + resultString);
        }
    }

    @Test
    public void testMultiplicationWithPositiveAndNegative() {
        String input = "3\n-4\n5\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Initialize the calculator or any necessary setup
        initCalculator();

        // Capture the output
        String output = outputStream.toString();

        // Verify the output
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));

        // Check if the multiplication result is not positive
        String resultLine = output.lines()
                .filter(line -> line.contains(" * "))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Multiplication result line not found in the output."));

        // Extract the result from the line, assuming the format is "a * b = result"
        String[] parts = resultLine.split("=");
        if (parts.length < 2) {
            throw new AssertionError("Result format is incorrect in the output.");
        }

        String resultString = parts[1].trim();

        try {
            int result = Integer.parseInt(resultString);
            assertTrue("Expected result to be non-positive but got: " + result, result <= 0);
        } catch (NumberFormatException e) {
            throw new AssertionError("Result is not a valid integer: " + resultString);
        }
    }


    @Test
    public void dividePass () {
        String input = "4\n10\n2\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("10 / 2 = 5"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void divideWZero() {
        String input = "4\n10\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Cannot divide by zero!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void divideFirstNumChar() {
        String input = "4\nabc\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void divideSecondNumChar() {
        String input = "4\n10\nxyz\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void moduloPass() {
        String input = "5\n10\n3\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("10 % 3 = 1"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void moduloWZero() {
        String input = "5\n10\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Cannot modulo by zero!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void moduloFirstNumChar() {
        String input = "5\nabc\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void moduloSecondNumChar() {
        String input = "5\n10\nxyz\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void powerPass() {
        String input = "6\n2\n3\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("2 ^ 3 = 8"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void powerNegativeRoot() {
        String input = "6\n2\n-2\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Does not support negative root number"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void powerFirstNumChar() {
        String input = "6\nabc\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void powerSecondNumChar() {
        String input = "6\n2\nxyz\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdTwoPositiveNumbers() {
        String input = "7\n27\n18\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of 27 and 18 is 9"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdOnePositiveOneNegativeNumber() {
        String input = "7\n100\n-20\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of 100 and -20 is 20"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdTwoNegativeNumbers() {
        String input = "7\n-72\n-27\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of -72 and -27 is 9"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdWithOneNumberIsZero() {
        String input = "7\n127\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of 127 and 0 is 127"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdTwoPrimeNumbers() {
        String input = "7\n13\n17\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of 13 and 17 is 1"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdInvalidInput() {
        // Simulating valid and invalid input
        String input = "7\nabc\n0\nb";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();  // Method that runs gcd()

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void lcmTwoPositiveNumbers() {
        String input = "8\n4\n5\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Least Common Multiple (LCM) of 4 and 5 is 20"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void lcmOnePositiveOneNegativeNumber() {
        String input = "8\n27\n-2\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Least Common Multiple (LCM) of 27 and -2 is 54"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void lcmTwoNegativeNumbers() {
        String input = "8\n-30\n-12\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Least Common Multiple (LCM) of -30 and -12 is 60"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void lcmWithOneNumberIsZero() {
        String input = "8\n203\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Least Common Multiple (LCM) of 203 and 0 is 0"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void lcmTwoEqualNumbers() {
        String input = "8\n123\n123\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Least Common Multiple (LCM) of 123 and 123 is 123"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void lcmTwoPrimeNumbers() {
        String input = "8\n13\n17\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Least Common Multiple (LCM) of 13 and 17 is 221"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void lcmInvalidInput() {
        // Simulating valid and invalid input
        String input = "8\na\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void fibonacciPositiveNumber() {
        // Simulating valid and invalid input
        String input = "9\n7\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Fibonacci of 7 is 13"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void fibonacciOfZero() {
        // Simulating valid and invalid input
        String input = "9\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Fibonacci of 0 is 0"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void fibonacciOfOne() {
        // Simulating valid and invalid input
        String input = "9\n1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Fibonacci of 1 is 1"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void fibonacciNegativeNumber() {
        // Simulating valid and invalid input
        String input = "9\n-5\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Fibonacci is not defined for negative numbers"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void fibonacciInvalidInput() {
        // Simulating valid and invalid input
        String input = "9\nabc\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("Support integer only!"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }
}