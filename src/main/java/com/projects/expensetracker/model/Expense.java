package com.projects.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(generator = "expense_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String expenseType;
    private String description;
    private Double amount;

    private Date dateCreated;
    private Date dateUpdated;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Expense(Long id, Double amount, String description, String expenseType, User user) {
    }

    public Expense(String expenseType, String description, Double amount, User user) {
        this.expenseType = expenseType;
        this.description = description;
        this.amount = amount;
        this.user = user;
    }

    public Expense() {

    }
}
