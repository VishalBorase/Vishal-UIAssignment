package com.example.test;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import com.example.rewardsystem.entity.Customer;
import com.example.rewardsystem.entity.RewardPoint;
import com.example.rewardsystem.repository.RewardPointRepository;
import com.example.rewardsystem.service.RewardPointServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RewardPointServiceTest {

	@InjectMocks
	private RewardPointServiceImpl rewardPointService;

	@Mock
	private RewardPointRepository rewardPointRepository;

	RewardPoint rewardPoint = new RewardPoint();

	Customer customer = new Customer();

	List<RewardPoint> rewardPoints = new ArrayList<>();

	@BeforeEach
	public void setup() {

	}

	@Test
	@Description("Test case for calculateRewardPoint method when amount is greater than 100")
	public void testCalculateRewardPoints_AmountGreaterThan100() {

		int points = rewardPointService.calculateRewardPoints(150);
		Assertions.assertEquals(150, points, "Points should be 150 for amount 150");

	}

	@Test
	@Description("Test case for calculateRewardPoint method when amount is between 51 and 100")
	public void testCalculateRewardPoints_AmountBetween51And100() {
		int points = rewardPointService.calculateRewardPoints(75);
		Assertions.assertEquals(25, points, "Points should be 25 for amount 75");
	}

	@Test
	@Description("Test case for calculateRewardPoint method when amount is between 0 and 50")
	public void testCalculateRewardPoints_AmountBetween0And50() {
		int points = rewardPointService.calculateRewardPoints(40);
		Assertions.assertEquals(0, points, "Points should be 0 for amount 40");
	}

	@Test
	@Description("Test case for calculateRewardPoint method when amount is 0")
	public void testCalculateRewardPoints__AmountZero() {
		int points = rewardPointService.calculateRewardPoints(0);
		Assertions.assertEquals(0, points, "Points should be 0 for amount 0");
	}

	@Test
	@Description("Test case for getRewardPointReport method when reward is present for customer in given month and year")
	public void testGetRewardPointReport_ValidInput() {
		Long customerId = 1L;
		int month = 1;
		int year = 2025;

		customer.setId(customerId);
		rewardPoint.setCustomer(customer);
		rewardPoints.add(rewardPoint);

		when(rewardPointRepository.findByCustomerIdAndMonthAndYear(customerId, month, year)).thenReturn(rewardPoints);

		List<RewardPoint> result = rewardPointService.getRewardPointReport(customerId, month, year);
		Assertions.assertNotNull(result, "Result should not be null");
		Assertions.assertEquals(1, result.size(), "There should be 1 reward point in the result");
	}

	@Test
	@Description("Test case for getRewardPointReport method when reward is not present for customer")
	public void testGetRewardPointReport_NoReward() {
		Long customerId = 1L;
		int month = 1;
		int year = 2025;

		customer.setId(customerId);
		rewardPoint.setCustomer(customer);
		rewardPoints.add(rewardPoint);

		when(rewardPointRepository.findByCustomerIdAndMonthAndYear(customerId, month, year))
				.thenReturn(new ArrayList<>());

		List<RewardPoint> result = rewardPointService.getRewardPointReport(customerId, month, year);
		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	@Description("Test case for getAllRewardPointsForCustomer method when reward point is present for customer")
	public void testGetRewardPointsForCustomer_ValidCase() {
		Long customerId = 2L;

		customer.setId(customerId);
		rewardPoint.setCustomer(customer);
		rewardPoints.add(rewardPoint);

		when(rewardPointRepository.findByCustomerId(customerId)).thenReturn(rewardPoints);

		List<RewardPoint> result = rewardPointService.getAllRewardPointsForCustomer(customerId);
		Assertions.assertNotNull(result, "Result should not be null");
		Assertions.assertEquals(1, result.size(), "There should be 1 reward point in the result");
	}

	@Test
	@Description("Test case for getAllRewardPointsForCustomer method when no reward point for customer")
	public void testGetRewardPointsForCustomer_NoRewardPoint() {
		Long customerId = 2L;

		when(rewardPointRepository.findByCustomerId(customerId)).thenReturn(new ArrayList<>());

		List<RewardPoint> result = rewardPointService.getAllRewardPointsForCustomer(customerId);
		Assertions.assertTrue(result.isEmpty());
	}

}
