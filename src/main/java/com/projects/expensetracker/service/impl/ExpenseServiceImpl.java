package com.projects.expensetracker.service.impl;

import com.projects.expensetracker.model.Expense;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.repository.ExpenseRepository;
import com.projects.expensetracker.service.ExpenseService;
import com.projects.expensetracker.exceptions.ExpenceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void addExpense(Expense expense) {

        expenseRepository.save(expense);
    }

    @Override
    public void updateExpense(Long expense_id, Expense expenseUpdated) throws ExpenceNotFoundException {
        Expense expense = expenseRepository.findById(expense_id).orElseThrow(() -> new ExpenceNotFoundException("Expense not found"));
        expense.setAmount(expenseUpdated.getAmount());
        expense.setDescription(expenseUpdated.getDescription());
        expense.setExpenseType(expenseUpdated.getExpenseType());
        expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long expenseId) throws ExpenceNotFoundException {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenceNotFoundException("Expense not found"));
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<Expense> getExpensesByUser(User user) {
        return expenseRepository.findAllByUser(user);
    }

    @Override
    public Expense findExpenseById(Long expenseId) throws ExpenceNotFoundException {
        return expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenceNotFoundException("Expense not found"));
    }


}
