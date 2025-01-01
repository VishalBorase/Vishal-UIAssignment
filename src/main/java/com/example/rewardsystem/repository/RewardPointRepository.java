package com.example.rewardsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rewardsystem.entity.RewardPoint;

public interface RewardPointRepository extends JpaRepository<RewardPoint, Long> {
	
	List<RewardPoint> findByCustomerId(Long customerId);
	
	List<RewardPoint> findByCustomerIdAndMonthAndYear(Long customerId, int month, int year);

}
