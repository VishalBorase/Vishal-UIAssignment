package com.example.rewardsystem.controller;

import com.example.rewardsystem.entity.RewardPoint;
import com.example.rewardsystem.service.RewardPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rewardPoints")
public class RewardPointController {

    @Autowired
    private RewardPointService rewardPointsService;

    @GetMapping("/customer")
    public ResponseEntity<List<RewardPoint>> getRewardPointReport(
            @RequestParam Long customerId,
            @RequestParam int year, 
            @RequestParam int month) {
        
    	List<RewardPoint> rewardPoints = rewardPointsService.getRewardPointReport(customerId, month, year);
        
        return ResponseEntity.ok(rewardPoints);
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<RewardPoint>> getAllRewardPointsForCustomer(
            @RequestParam Long customerId) { 
        
        List<RewardPoint> rewardPointsList = rewardPointsService.getAllRewardPointsForCustomer(customerId);
        
        return ResponseEntity.ok(rewardPointsList);
    }
}
