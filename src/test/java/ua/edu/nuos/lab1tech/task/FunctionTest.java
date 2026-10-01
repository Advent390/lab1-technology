package ua.edu.nuos.lab1tech.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {
    private final Function function = new Function();

    @Test
    void testForOne() {
        assertEquals(0.0, function.calculate(0), 1e-12);
    }

    @Test
    void testForOnePoint() {
        double expected = (Math.E - 1.0) / (Math.E + 1.0);
        assertEquals(expected, function.calculate(1), 1e-12);
    }

    @Test
    void testIsPositiveOnIntegrationInterval() {
        assertTrue(function.calculate(1) > 0);
        assertTrue(function.calculate(2) > 0);
    }
}
