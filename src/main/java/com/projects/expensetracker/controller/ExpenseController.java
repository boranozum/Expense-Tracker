package com.projects.expensetracker.controller;

import com.projects.expensetracker.dto.ExpenseDto;
import com.projects.expensetracker.exceptions.ExpenceNotFoundException;
import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.service.ExpenseService;
import com.projects.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ExpenseDto>> getExpensesByUser(@PathVariable Long user_id) throws UserNotFoundException {

        User user = userService.getUserByID(user_id);
        return ResponseEntity.ok(expenseService.getExpensesByUser(user));
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<Boolean> addExpense(@PathVariable Long user_id, @RequestBody @Validated ExpenseDto expense) throws UserNotFoundException {

        return ResponseEntity.ok(expenseService.addExpense(expense, user_id));
    }

    @PutMapping("/{expense_id}")
    public ResponseEntity<Boolean> updateExpense(@PathVariable Long expense_id, @RequestBody @Validated ExpenseDto expense) throws ExpenceNotFoundException {

        return ResponseEntity.ok(expenseService.updateExpense(expense_id, expense));
    }

    @DeleteMapping("/{expense_id}")
    public ResponseEntity<Boolean> deleteExpense(@PathVariable Long expense_id) throws ExpenceNotFoundException {

        return ResponseEntity.ok(expenseService.deleteExpense(expense_id));
    }
}
