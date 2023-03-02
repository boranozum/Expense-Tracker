package com.projects.expensetracker.service.impl;

import com.projects.expensetracker.dto.ExpenseDto;
import com.projects.expensetracker.model.Expense;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.repository.ExpenseRepository;
import com.projects.expensetracker.repository.UserRepository;
import com.projects.expensetracker.service.ExpenseService;
import com.projects.expensetracker.exceptions.ExpenceNotFoundException;
import com.projects.expensetracker.utils.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean addExpense(ExpenseDto expenseDto, Long userId) {
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        User user = userRepository.findById(userId).get();
        expense.setUser(user);
        expenseRepository.save(expense);
        return true;
    }

    @Override
    public Boolean updateExpense(Long expense_id, ExpenseDto expenseUpdated) throws ExpenceNotFoundException {
        Expense expense = expenseRepository.findById(expense_id).orElseThrow(() -> new ExpenceNotFoundException("Expense not found"));
        expense.setAmount(expenseUpdated.getAmount());
        expense.setDescription(expenseUpdated.getDescription());
        expense.setExpenseType(expenseUpdated.getExpenseType());
        expenseRepository.save(expense);
        return true;
    }

    @Override
    public Boolean deleteExpense(Long expenseId) throws ExpenceNotFoundException {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenceNotFoundException("Expense not found"));
        expenseRepository.deleteById(expenseId);
        return true;
    }

    @Override
    public List<ExpenseDto> getExpensesByUser(User user) {
        return ObjectMapper.mapAll(expenseRepository.findAllByUser(user), ExpenseDto.class);
    }

    @Override
    public Expense findExpenseById(Long expenseId) throws ExpenceNotFoundException {
        return expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenceNotFoundException("Expense not found"));
    }

    @Override
    public Expense getExpenseByID(Long id) throws ExpenceNotFoundException {
        return expenseRepository.findById(id).orElseThrow(() -> new ExpenceNotFoundException("Expense not found"));
    }


}
