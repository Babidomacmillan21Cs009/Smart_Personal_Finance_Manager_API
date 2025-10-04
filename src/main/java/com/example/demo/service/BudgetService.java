package com.example.demo.service;


import com.example.demo.model.Budget;
import com.example.demo.model.Users;
import com.example.demo.repo.BudgetRepo;
import com.example.demo.repo.TransactionRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepo budgetRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TransactionRepo transRepo;

    public Budget addBudget(UserDetails userDetails,Budget budget) {
        System.out.println(userDetails.getUsername());
        Users user = userRepo.findByUsername(userDetails.getUsername());
        if (user == null) return null;

        budget.setUser(user);
        return budgetRepo.save(budget);
    }

    public List<Budget> getAllBudgets(UserDetails userDetails, int month) {
        Users user = userRepo.findByUsername(userDetails.getUsername());
        if (user == null) return null;

        List<Budget> budgets = budgetRepo.findByUser(user);
        for (Budget budget : budgets){
            BigDecimal spent = transRepo.sumByUserAndCategoryAndMonth(user, budget.getCategory(),
                    budget.getMonth().getYear(),budget.getMonth().getMonthValue());

            budget.setSpent(spent != null ? spent : BigDecimal.ZERO);
        }
        return budgets;
    }

    public Budget updateBudget(UserDetails userDetails, Budget budget) {
        Users user = userRepo.findByUsername(userDetails.getUsername());
        if (user == null) return null;

        Budget budget1 = budgetRepo.findById(budget.getId()).orElse(null);
        if (budget1 != null && budget1.getUser().getId() == user.getId()){
            budget1.setCategory(budget.getCategory());
            budget1.setLimitAmount(budget.getLimitAmount());
            budget1.setMonth(budget.getMonth());
            budget1.setUser(user);
            return budgetRepo.save(budget1);
        }
        return null;
    }

    public boolean deleteBudget(UserDetails userDetails, int budgetId) {
        System.out.println(userDetails.getUsername());
        Users user = userRepo.findByUsername(userDetails.getUsername());
        if (user == null) return false;

        System.out.println("budget id : " + budgetId);

        Budget budget = budgetRepo.findById(budgetId).orElse(null);
        if (budget != null && budget.getUser().getId() == user.getId()) {
            budgetRepo.delete(budget);
            return true;
        }
        return false;
    }

}

















