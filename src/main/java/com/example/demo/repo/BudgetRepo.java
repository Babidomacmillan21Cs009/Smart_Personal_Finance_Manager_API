package com.example.demo.repo;

import com.example.demo.model.Budget;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Integer> {

    String findByCategory(String category);

    double findByLimitAmount(BigDecimal amount);

    @Query("SELECT b FROM Budget b WHERE " +
            "b.user = :user " +
            "AND b.category = :category " +
            "AND b.month = :month")
    Budget findByUserAndCategoryAndMonth(@Param("user") Users user,
                                         @Param("category") String category,
                                         @Param("month") YearMonth month);

    @Query("SELECT b FROM Budget b " +
            "WHERE b.user = :user AND " +
            "b.month = :yearMonth")
    List<Budget> findByUserAndMonth(@Param("user") Users user, @Param("yearMonth") YearMonth yearMonth);
}









