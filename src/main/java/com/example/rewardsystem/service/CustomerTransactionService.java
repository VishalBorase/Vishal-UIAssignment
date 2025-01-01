package com.example.rewardsystem.service;

import java.util.List;

import com.example.rewardsystem.dto.CustomerTransactionRequest;
import com.example.rewardsystem.entity.CustomerTransaction;

public interface CustomerTransactionService {
	
	public List<CustomerTransaction> getCustomerTransactions(Long customerId);
	
	public CustomerTransaction createTransaction(CustomerTransactionRequest customerTransactionRequest);
	
    public CustomerTransaction updateTransaction(Long transactionId, CustomerTransactionRequest updatedTransaction);

    public void deleteTransaction(Long transactionId);

}
