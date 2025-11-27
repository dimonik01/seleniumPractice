package example;

import org.example.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    private EmailValidator emailValidator;
    @BeforeEach
    void setUp(){
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = { "text@email.ru", "VeryLong234And@gmail.ru", "12476534564573@a.rambler","POS1ITIVE3EMAIL@5text.co4m"})
    void shouldReturnTrueForValidEmails(String email){
        assertTrue(emailValidator.isValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"@email.ru", "email.ru", "email", ".ru", "testmail@email.ru.", "testmail@email.", "", "12315345213125434534"})
    void shouldReturnFalseForInvalidEmails(String email){
        assertFalse(emailValidator.isValid(email));
    }

    @Test
    void shouldReturnFalseForNull(){
        assertFalse(emailValidator.isValid(null));
    }


}

