package com.example.rewardsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rewardsystem.dto.CustomerTransactionRequest;
import com.example.rewardsystem.entity.CustomerTransaction;
import com.example.rewardsystem.repository.CustomerTransactionRepository;
import com.example.rewardsystem.service.CustomerTransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class CustomerTransactionController {

    @Autowired
    private CustomerTransactionService customerTransactionService;

    @GetMapping("/get/{customerId}")
    public ResponseEntity<List<CustomerTransaction>> getCustomerTransactions(
            @PathVariable Long customerId) {

        List<CustomerTransaction> transactions = customerTransactionService.getCustomerTransactions(customerId);

        return ResponseEntity.ok(transactions);
    }
    
    @PostMapping("/add")
    public ResponseEntity<CustomerTransaction> addCustomerTransaction(
            @RequestBody @Valid CustomerTransactionRequest transactionRequest) {

        CustomerTransaction newTransaction = customerTransactionService.createTransaction(transactionRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

 
    @PutMapping("/edit/{transactionId}")
    public ResponseEntity<CustomerTransaction> updateCustomerTransaction(
            @PathVariable Long transactionId,
            @RequestBody @Valid CustomerTransactionRequest transactionRequest) {

        CustomerTransaction updatedTransaction = customerTransactionService.updateTransaction(transactionId, transactionRequest);

        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<Void> deleteCustomerTransaction(@PathVariable Long transactionId) {
        customerTransactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
