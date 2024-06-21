public class PasswordValidator {

    @MinLength(6)
    @ContainsSpecialChar
    @ContainsDigit
    @ContainsUpperLowerCase
    public boolean isValid(String password) {
        // Логіка перевірки залишилася незмінною
    }
}
