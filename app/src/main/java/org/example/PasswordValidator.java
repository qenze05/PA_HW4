package org.example;

public class PasswordValidator {

    @MinLength(6)
    @ContainsSpecialChar
    @ContainsDigit
    @ContainsUpperLowerCase
    public boolean isValid(String password) {

        return RuntimeValidator.validate(this, password);
    }
}
