package com.example.demo.service;


import com.example.demo.model.Budget;
import com.example.demo.model.Users;
import com.example.demo.repo.BudgetRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepo budgetRepo;

    @Autowired
    private UserRepo userRepo;

    public Budget addBudget(UserDetails userDetails,Budget budget) {
        Users user = userRepo.findByUsername(userDetails.getUsername());
        if (user == null) return null;

        budget.setUser(user);
        return budgetRepo.save(budget);
    }
}

















