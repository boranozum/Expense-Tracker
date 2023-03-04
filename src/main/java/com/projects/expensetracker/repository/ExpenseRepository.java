package com.projects.expensetracker.repository;

import com.projects.expensetracker.model.Expense;
import com.projects.expensetracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findAllByUser(User user, Pageable pageable);
}
