package com.example.demo.repo;

import com.example.demo.model.Transaction;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByUserAndCategory(Users user, String category);

    @Query("SELECT t FROM Transaction t WHERE " +
            "t.user = :user AND MONTH(t.date) = :month")
    List<Transaction> findByUserAndMonth(@Param("user") Users user, @Param("month") int month);

}
