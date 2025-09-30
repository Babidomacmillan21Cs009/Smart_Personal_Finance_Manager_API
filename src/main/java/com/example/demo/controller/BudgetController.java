package com.example.demo.controller;

import com.example.demo.model.Budget;
import com.example.demo.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


    @PostMapping("/add-budget")
    public ResponseEntity<Budget> addBudget(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody Budget budget){

        Budget budget1 = budgetService.addBudget(userDetails,budget);

        if (budget1 == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(budget1, HttpStatus.OK);
    }


//    @PutMapping("/update-budget")
//    public ResponseEntity<Budget> updateBudget(@AuthenticationPrincipal UserDetails userDetails,
//                                               @RequestBody Budget budget){
//
//    }
}


































