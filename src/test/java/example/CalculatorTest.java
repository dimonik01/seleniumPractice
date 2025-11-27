package example;

import org.example.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void calculatorSmokeTest() {
        Calculator calculator = new Calculator();
        assertAll(
                "Calculator division behavior",
                () -> assertEquals(10.0, calculator.divide(100, 10)),
                () -> {
                    IllegalArgumentException ex = assertThrows(
                            IllegalArgumentException.class,
                            () -> calculator.divide(10, 0)
                    );
                    assertEquals("Division by zero is not allowed", ex.getMessage());
                }
        );
    }
}