package org.example;


@GeneratePassValidator(fields = {"String password"})
public class PasswordValidator {

    public String password;

    public PasswordValidator(String password) {
        this.password = password;
    }

    @MinLength(6)
    @ContainsSpecialChar
    @ContainsDigit
    @ContainsUpperLowerCase
    public boolean isValid() {

        return RuntimeValidator.validate(this, password);
    }
}
