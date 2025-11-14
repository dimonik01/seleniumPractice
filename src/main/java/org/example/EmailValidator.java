package org.example;

import java.util.regex.Pattern;

public class EmailValidator {
    public boolean isValid(String email){
            if (email == null || email.isEmpty()) return false;
            return Pattern.matches("^\\w+@\\w+\\.\\w+$", email);
    }
}
