package com.example.rewardsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rewardsystem.dto.CustomerTransactionRequest;
import com.example.rewardsystem.entity.CustomerTransaction;
import com.example.rewardsystem.exception.ResourceNotFoundException;
import com.example.rewardsystem.repository.CustomerRepository;
import com.example.rewardsystem.repository.CustomerTransactionRepository;

@Service("customerTransactionService")
public class CustomerTransactionServiceImpl implements CustomerTransactionService {

	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<CustomerTransaction> getCustomerTransactions(Long customerId) {
		List<CustomerTransaction> customerTransactions = customerTransactionRepository.findByCustomerId(customerId);
		return customerTransactions;
	}

	@Override
	public CustomerTransaction createTransaction(CustomerTransactionRequest customerTransactionRequest) {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCustomer(customerRepository.getById(customerTransactionRequest.getCustomerId()));
		transaction.setSpentDetails(customerTransactionRequest.getSpentDetails());
		transaction.setAmount(customerTransactionRequest.getAmount());
		transaction.setDate(customerTransactionRequest.getDate());
		return customerTransactionRepository.save(transaction);
	}

	@Override
	public CustomerTransaction updateTransaction(Long transactionId, CustomerTransactionRequest updatedTransaction) {

		Optional<CustomerTransaction> existingTransactionOpt = customerTransactionRepository.findById(transactionId);

		if (!existingTransactionOpt.isPresent()) {
			throw new ResourceNotFoundException("Transaction with ID " + transactionId + " not found.");
		}

		CustomerTransaction existingTransaction = existingTransactionOpt.get();

		if (updatedTransaction.getSpentDetails() != null) {
			existingTransaction.setSpentDetails(updatedTransaction.getSpentDetails());
		}
		if (updatedTransaction.getAmount() != 0.0) {
			existingTransaction.setAmount(updatedTransaction.getAmount());
		}
		if (updatedTransaction.getDate() != null) {
			existingTransaction.setDate(updatedTransaction.getDate());
		}

		return customerTransactionRepository.save(existingTransaction);
	}

	@Override
	public void deleteTransaction(Long transactionId) {
		Optional<CustomerTransaction> existingTransactionOpt = customerTransactionRepository.findById(transactionId);

		if (!existingTransactionOpt.isPresent()) {
			throw new ResourceNotFoundException("Transaction with ID " + transactionId + " not found.");
		}
		customerTransactionRepository.deleteById(transactionId);
	}

}
