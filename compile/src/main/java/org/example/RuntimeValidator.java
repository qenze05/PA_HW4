package org.example;

import java.lang.reflect.Method;

public class RuntimeValidator {
    public static boolean validate(Object obj, String password) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(MinLength.class)) {
                MinLength minLength = method.getAnnotation(MinLength.class);
                if (password.length() <= minLength.value()) return false;
            }
            if (method.isAnnotationPresent(ContainsSpecialChar.class)) {
                if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) return false;
            }
            if (method.isAnnotationPresent(ContainsDigit.class)) {
                if (!password.matches(".*\\d.*")) return false;
            }
            if (method.isAnnotationPresent(ContainsUpperLowerCase.class)) {
                if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) return false;
            }
        }
        return true;
    }


}
