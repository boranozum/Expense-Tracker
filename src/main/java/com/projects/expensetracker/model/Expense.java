package com.projects.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(generator = "expense_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String expenseType;
    private String description;
    private Double amount;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
