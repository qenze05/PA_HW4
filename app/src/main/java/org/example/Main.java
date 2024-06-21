package org.example;


public class Main {
    public static void main(String[] args) {
        PasswordValidator validator = new PasswordValidator("Password@123");

        boolean isValid = validator.isValid();
        System.out.println("Is password \""+validator.password+"\" valid? " + isValid);

        validator.password = "password@123";
        isValid = validator.isValid();
        System.out.println("Is password \""+validator.password+"\" valid? " + isValid);

        validator.password = "Pass12@";
        isValid = validator.isValid();
        System.out.println("Is password \""+validator.password+"\" valid? " + isValid);

        validator.password = "Pass1@";
        isValid = validator.isValid();
        System.out.println("Is password \""+validator.password+"\" valid? " + isValid);


    }
}