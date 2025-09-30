package com.example.demo.repo;

import com.example.demo.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Integer> {

    Budget findByCategory(String category);

    double findByLimitAmount(BigDecimal amount);

}
