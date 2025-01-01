package com.example.rewardsystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rewardsystem.entity.CustomerTransaction;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
	
	List<CustomerTransaction> findByCustomerId(Long customerId);

}
