package com.example.demo.service;


import com.example.demo.model.Budget;
import com.example.demo.model.Transaction;
import com.example.demo.model.Users;
import com.example.demo.repo.BudgetRepo;
import com.example.demo.repo.TransactionRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BudgetRepo budgetRepo;

    public int getUserIdFromUserDetails(UserDetails userDetail) {
        Users user = userRepo.findByEmail(userDetail.getUsername())
                .orElseThrow(() -> new RuntimeException("user not found"));

        return user.getId();
    }

    public List<Transaction> getAllTransaction() {
        return transRepo.findAll();
    }

//    public boolean addTransaction(int userId, Transaction transaction) {
//        Users user = userRepo.findById(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        String category = budgetRepo.findByCategory(transaction.getCategory());
//        double amount = budgetRepo.findByLimitAmount(transaction.getAmount());
//        double sumofAmount = budgetRepo.findBySumOf
//
//        transaction.setUser(user);
//        return transRepo.save(transaction);
//    }

    public Transaction updateTransaction(int userId, Transaction transaction) {
        Users user = userRepo.findById(userId).orElse(null);
        if(user == null) return null;

        Transaction transaction1 = transRepo.findById(transaction.getId()).orElse(null);
        if(transaction1 == null) return null;

        transaction1.setAmount(transaction.getAmount());
        transaction1.setType(transaction.getType());
        transaction1.setCategory(transaction.getCategory());
        transaction1.setDescription(transaction.getDescription());
        transaction1.setDate(transaction.getDate());
        transaction1.setUser(user);
        return transRepo.save(transaction1);
    }


    public boolean deleteTransactionById(int userId, int transId) {
        Users user = userRepo.findById(userId).orElse(null);
        if(user == null) return false;

        Transaction transaction = transRepo.findById(transId).orElse(null);
        if(transaction == null) return false;

        if(transaction.getUser().getId() != user.getId()) return false;

        transRepo.deleteById(transId);
        return true;
    }

    public List<Transaction> getAllTransactionByCategory(int userId, String category) {
        Users user = userRepo.findById(userId).orElse(null);
        if(user == null) return Collections.emptyList();

        return transRepo.findByUserAndCategory(user, category);
    }

    public List<Transaction> getAllTransactionByMonth(int userId, int month) {
        Users user = userRepo.findById(userId).orElse(null);
        if (user == null) return Collections.emptyList();

        return transRepo.findByUserAndMonth(user, month);

    }


}
