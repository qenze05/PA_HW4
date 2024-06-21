package org.example;


public class Main {
    public static void main(String[] args) {
        PasswordValidator validator = new PasswordValidator();

        String password = "Password@123";
        boolean isValid = validator.isValid(password);
        System.out.println("Is password \""+password+"\" valid? " + isValid);

        password = "password@123";
        isValid = validator.isValid(password);
        System.out.println("Is password \""+password+"\" valid? " + isValid);

        password = "Pass12@";
        isValid = validator.isValid(password);
        System.out.println("Is password \""+password+"\" valid? " + isValid);

        password = "Pass1@";
        isValid = validator.isValid(password);
        System.out.println("Is password \""+password+"\" valid? " + isValid);


    }
}