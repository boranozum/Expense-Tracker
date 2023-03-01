package com.projects.expensetracker.service;

import com.projects.expensetracker.exceptions.ExpenceNotFoundException;
import com.projects.expensetracker.model.Expense;
import com.projects.expensetracker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    void addExpense(Expense expense);

    void updateExpense(Long expense_id, Expense expenseUpdated) throws ExpenceNotFoundException;

    void deleteExpense(Long expenseId) throws ExpenceNotFoundException;

    List<Expense> getExpensesByUser(User user);

    Expense findExpenseById(Long expenseId) throws ExpenceNotFoundException;
}
