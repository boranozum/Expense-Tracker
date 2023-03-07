package com.projects.expensetracker.dto;

import lombok.Data;

import java.util.Date;


@Data
public class ExpenseDto {

    private String description;
    private String category;
    private Double amount;
    private Date dateCreated;
    private Date dateUpdated;
}
