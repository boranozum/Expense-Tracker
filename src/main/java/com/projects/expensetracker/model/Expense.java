package com.projects.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projects.expensetracker.enumaration.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(generator = "expense_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;
    private String description;
    private Double amount;
    private Date dateCreated;
    private Date dateUpdated;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Expense(Long id, Double amount, String description, String expenseType, User user) {
    }

    public Expense(ExpenseCategory category, String description, Double amount, User user) {
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.user = user;
    }

    public Expense() {

    }
}
