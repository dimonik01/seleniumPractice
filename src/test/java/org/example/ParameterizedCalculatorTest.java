package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedCalculatorTest {


    @ParameterizedTest
    @CsvSource({
            "3, false",
            "2, true",
            "310000, true",
            "-6, true",
            "0, true",
    })
    void shouldDetermineEvennessCorrectly(int number, boolean expectedResult) {
        NumberUtils numberUtils = new NumberUtils();
        assertEquals(expectedResult, numberUtils.isEven(number));
    }
}