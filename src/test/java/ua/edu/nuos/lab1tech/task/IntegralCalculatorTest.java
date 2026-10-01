package ua.edu.nuos.lab1tech.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegralCalculatorTest {

    private final Function function = new Function();

    @Test
    void testWith1000() {
        IntegralCalculator calculator = new IntegralCalculator(1, 2, 1000, function);

        double result = calculator.calculate();

        assertEquals(0.6273326470, result, 1e-9);
    }

    @Test
    void testWith10() {
        IntegralCalculator calculator = new IntegralCalculator(1, 2, 10, function);

        double result = calculator.calculate();

        assertEquals(0.6273326470, result, 1e-7);
    }

    @Test
    void rejectsOddNumberOfIntervals() {
        assertThrows(IllegalArgumentException.class,
                () -> new IntegralCalculator(1, 2, 5, function));
    }
}
