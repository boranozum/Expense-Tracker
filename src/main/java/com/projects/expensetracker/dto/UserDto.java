package com.projects.expensetracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

        private String firstName;
        private String lastName;
        private String email;
        private List<ExpenseDto> expenses;
        private Double monthlyBudget;
        private Double currentBudget;
}
