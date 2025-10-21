package com.example.demo.service;

import com.example.demo.DTO_classes.CategorySpending;
import com.example.demo.DTO_classes.MonthlySummary;
import com.example.demo.model.Users;
import com.example.demo.repo.TransactionRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TransactionRepo transRepo;

    public MonthlySummary getMonthlySummary(UserDetails userDetails, int month, int year) {
        Users user = userRepo.findByUsername(userDetails.getUsername());

        BigDecimal totalExpense = transRepo.sumByUserAndTypeAndMonth(user, "Expense", month, year);
        BigDecimal totalIncome = transRepo.sumByUserAndTypeAndMonth(user,"Income", month, year);

        totalExpense = totalExpense != null ? totalExpense : BigDecimal.ZERO;
        totalIncome = totalIncome != null ? totalIncome : BigDecimal.ZERO;

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new MonthlySummary(totalIncome, totalExpense, balance);
    }

    public List<CategorySpending> getByCategorySpending(Users user, int month, int year) {
        List<Object[]> results = transRepo.sumByCategory(user,month,year);

        List<CategorySpending> list = new ArrayList<>();
        for (Object[] row : results){
            String category = (String) row[0];
            BigDecimal spent = (BigDecimal) row[1];
            list.add(new CategorySpending(category, spent != null ? spent : BigDecimal.ZERO));
        }
        return list;
    }
}
