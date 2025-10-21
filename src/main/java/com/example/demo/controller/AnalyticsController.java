package com.example.demo.controller;


import com.example.demo.DTO_classes.CategorySpending;
import com.example.demo.DTO_classes.MonthlySummary;
import com.example.demo.model.Users;
import com.example.demo.service.AnalyticsService;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {


    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/monthly/{month}/{year}")
    public MonthlySummary getMonthlySummary(@AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable int month,
                                            @PathVariable int year) {
        return analyticsService.getMonthlySummary(userDetails,month,year);
    }

    @GetMapping("/category/{month}/{year}")
    public List<CategorySpending> getByCategorySpending(@AuthenticationPrincipal UserDetails userDetails,
                                                        @PathVariable int month,
                                                        @PathVariable int year) {
        Users user = userRepo.findByUsername(userDetails.getUsername());
        return analyticsService.getByCategorySpending(user,month,year);
    }
}

















