package example;

import org.example.NumberUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberUtilsTest {
    @Test
    void shouldReturnTrueForEvenNumber(){
        NumberUtils testNumberUtils = new NumberUtils();
        assertEquals(true, testNumberUtils.isEven(10));
    }
    @Test
    void shouldReturnFalseForUnevenNumber(){
        NumberUtils testNumberUtils = new NumberUtils();
        assertEquals(false, testNumberUtils.isEven(9));
    }

    @Test
    void shouldTreatZeroAsEven(){
        NumberUtils testNumberUtils = new NumberUtils();
        assertEquals(true, testNumberUtils.isEven(0));
    }
}
