package com.projects.expensetracker.service;

import com.projects.expensetracker.dto.ExpenseDto;
import com.projects.expensetracker.exceptions.ExpenceNotFoundException;
import com.projects.expensetracker.model.Expense;
import com.projects.expensetracker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    Expense getExpenseByID(Long id) throws ExpenceNotFoundException;
    Boolean addExpense(ExpenseDto expense, Long userId) throws ExpenceNotFoundException;

    Boolean updateExpense(Long expense_id, ExpenseDto expenseUpdated) throws ExpenceNotFoundException;

    Boolean deleteExpense(Long expenseId) throws ExpenceNotFoundException;

    List<ExpenseDto> getExpensesByUser(User user, int page, int size, String sortOrder, String sortBy);

    Expense findExpenseById(Long expenseId) throws ExpenceNotFoundException;
}
