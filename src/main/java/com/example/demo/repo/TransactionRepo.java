package com.example.demo.repo;

import com.example.demo.model.Budget;
import com.example.demo.model.Transaction;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByUserAndCategory(Users user, String category);

    @Query("SELECT t FROM Transaction t WHERE " +
            "t.user = :user AND MONTH(t.date) = :month")
    List<Transaction> findByUserAndMonth(@Param("user") Users user, @Param("month") int month);


    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE " +
            "t.user = :user " +
            "AND t.category = :category " +
            "AND YEAR(t.date) = :year " +
            "AND MONTH(t.date) = :month")
    BigDecimal sumByUserAndCategoryAndMonth(@Param("user") Users user,
                                            @Param("category") String category,
                                            @Param("year") int year,
                                            @Param("month") int month);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE " +
            "t.user = :user AND t.type = :type " +
            " AND MONTH(t.date) = :month AND  YEAR(t.date) = :year")
    BigDecimal sumByUserAndTypeAndMonth(@Param("user") Users user, @Param("type") String type, @Param("month") int month, @Param("year") int year);


    @Query("SELECT category, SUM(t.amount) FROM Transaction t WHERE " +
            "t.user = :user AND MONTH(t.date) = :month " +
            "AND YEAR(t.date) = :year " +
            "GROUP BY t.category")
    List<Object[]> sumByCategory(Users user, int month, int year);

}
