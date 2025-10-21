package com.example.demo.controller;

import com.example.demo.model.Budget;
import com.example.demo.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


    @GetMapping("/user/all-budgets/{yearMonth}")
    public List<Budget> getAllBudgets(@AuthenticationPrincipal UserDetails userDetails,
                                      @PathVariable YearMonth yearMonth) {
        return budgetService.getAllBudgets(userDetails, yearMonth);
    }


    @PostMapping("/add-budget")
    public ResponseEntity<Budget> addBudget(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody Budget budget){
        System.out.println(userDetails.getUsername());
        Budget budget1 = budgetService.addBudget(userDetails,budget);

        if (budget1 == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(budget1, HttpStatus.OK);
    }


    @PutMapping("/user/update-budget")
    public ResponseEntity<Budget> updateBudget(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody Budget budget){
        Budget updatedBudget = budgetService.updateBudget(userDetails, budget);
        if (updatedBudget != null)
            return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/user/delete-budget/{id}")
    public ResponseEntity<String> deleteBudget(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable int id) {
        boolean deleted = budgetService.deleteBudget(userDetails, id);
        if (deleted) {
            return ResponseEntity.ok("Budget deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to delete budget");
    }


}


































