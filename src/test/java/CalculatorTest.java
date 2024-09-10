
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
        String input = "7\n27\n-18\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of 27 and -18 is 9"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdTwoNegativeNumbers() {
        String input = "7\n27\n-18\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of -27 and -18 is 9"));
        assertTrue(output.contains("Exiting the calculator. Goodbye!"));
    }

    @Test
    public void gcdWithOneNumberIsZero() {
        String input = "7\n27\n-0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        initCalculator();

        String output = outputStream.toString();
        assertTrue(output.contains("The Greatest Common Divisor (GCD) of 27 and 0 is 27"));
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

    @Test(expected = NoSuchElementException.class)
    public void gcdInvalidInput() {
        // Simulating valid and invalid input
        String input = "7\na\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        initCalculator();  // Method that runs gcd()
    }



//    @Test
}