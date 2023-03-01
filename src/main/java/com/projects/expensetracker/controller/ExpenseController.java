package com.projects.expensetracker.controller;

import com.projects.expensetracker.exceptions.ExpenceNotFoundException;
import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.Expense;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.service.ExpenseService;
import com.projects.expensetracker.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }


    @GetMapping("/{user_id}")
    public List<Expense> getExpensesByUser(@PathVariable Long user_id) throws UserNotFoundException {

        User user = userService.findUserById(user_id);
        return expenseService.getExpensesByUser(user);
    }

    @PostMapping("/{user_id}")
    public void addExpense(@PathVariable Long user_id, @RequestBody @Validated Expense expense) throws UserNotFoundException {

        User user = userService.findUserById(user_id);
        expense.setUser(user);
        expenseService.addExpense(expense);
    }

    @PutMapping("/{expense_id}")
    public void updateExpense(@PathVariable Long expense_id, @RequestBody @Validated Expense expense) throws ExpenceNotFoundException {

        expenseService.updateExpense(expense_id, expense);
    }

    @DeleteMapping("/{expense_id}")
    public void deleteExpense(@PathVariable Long expense_id) throws ExpenceNotFoundException {

        expenseService.deleteExpense(expense_id);
    }
}
