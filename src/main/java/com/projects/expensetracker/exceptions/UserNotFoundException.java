package com.projects.expensetracker.exceptions;

public class UserNotFoundException extends Exception {

    public String message = "User not found";
    public UserNotFoundException(String message) {
        super(message);
    }
}
