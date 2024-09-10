
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
}