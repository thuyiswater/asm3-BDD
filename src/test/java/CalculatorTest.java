
import org.junit.Before;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;

public class CalculatorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inputStream = null;
    private final PrintStream originalIn = System.out;

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

//    @Test
}