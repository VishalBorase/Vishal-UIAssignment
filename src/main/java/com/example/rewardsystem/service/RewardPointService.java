package com.example.rewardsystem.service;

import java.util.List;

import com.example.rewardsystem.entity.RewardPoint;

public interface RewardPointService {
	
	public int calculateRewardPoints(double amount);
	
	public List<RewardPoint> getRewardPointReport(Long customerId, int month,int year);
	
	public List<RewardPoint> getAllRewardPointsForCustomer(Long customerId);
}
