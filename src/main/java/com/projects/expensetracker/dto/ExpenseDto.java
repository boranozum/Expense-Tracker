package com.projects.expensetracker.dto;

import lombok.Data;


@Data
public class ExpenseDto {

    private String description;
    private String expenseType;
    private Double amount;
}
