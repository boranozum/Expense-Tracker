package com.projects.expensetracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

        private String firstName;
        private String lastName;
        private String email;

        private String password;

        private List<ExpenseDto> expenses;
}
