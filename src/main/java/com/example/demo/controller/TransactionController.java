package com.example.demo.controller;


import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transService;


    @GetMapping("/")
    public List<Transaction> getAllTransaction(){
        return transService.getAllTransaction();
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> addTransaction(@PathVariable int userId,
                                            @RequestBody Transaction transaction){

        boolean withInBudget = transService.addTransaction(userId,transaction);
        if (withInBudget)
            return new ResponseEntity<>(transaction,HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you are exceeding your budget amount");
    }

    @PutMapping("/user/update-user/{userId}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int userId,
                                                         @RequestBody Transaction transaction){

        Transaction transaction1 = transService.updateTransaction(userId, transaction);

        if(transaction1 != null)
            return new ResponseEntity<>(transaction1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/user/{userId}/delete-Transaction/{transId}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable int userId,
                                                   @PathVariable int transId ) {

        boolean deleted = transService.deleteTransactionById(userId, transId);
        if(deleted)
            return new ResponseEntity<>("Successfully deleted the Trasaction", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<List<Transaction>> getAllTransactionBycategory(@PathVariable int userId,
                                                                   @PathVariable String category) {

        List<Transaction> transaction = transService.getAllTransactionByCategory(userId, category);
        if (!transaction.isEmpty())
            return new ResponseEntity<>(transaction, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}/month/{month}")
    public ResponseEntity<List<Transaction>> getAllTransactionByMonth(@PathVariable int userId,
                                                      @PathVariable int month) {
        List<Transaction> transactions = transService.getAllTransactionByMonth(userId, month);

        if (!transactions.isEmpty())
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}













