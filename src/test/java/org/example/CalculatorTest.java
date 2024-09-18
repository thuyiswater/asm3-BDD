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
}