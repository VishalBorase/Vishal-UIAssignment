package com.example.rewardsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rewardsystem.entity.RewardPoint;
import com.example.rewardsystem.repository.RewardPointRepository;

@Service("rewardPointService")
public class RewardPointServiceImpl implements RewardPointService {
	
	@Autowired
	private RewardPointRepository rewardPointRepository;

	@Override
	public int calculateRewardPoints(double amount) {
		int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            amount = 100;
        }
        if (amount > 50) {
            points += (int) ((amount - 50));
        }
        return points;
	}

	@Override
	public List<RewardPoint> getRewardPointReport(Long customerId, int month, int year) {
		return rewardPointRepository.findByCustomerIdAndMonthAndYear(customerId,month,year);
	}

	@Override
	public List<RewardPoint> getAllRewardPointsForCustomer(Long customerId) {
		return rewardPointRepository.findByCustomerId(customerId);
	}

}
