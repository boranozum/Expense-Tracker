package com.projects.expensetracker.exceptions;

public class ExpenceNotFoundException extends Exception {

    public String message = "Expense not found";

    public ExpenceNotFoundException(String message) {
        super(message);
    }

}
