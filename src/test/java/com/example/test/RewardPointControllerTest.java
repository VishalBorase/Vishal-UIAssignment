package com.example.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.rewardsystem.controller.RewardPointController;
import com.example.rewardsystem.entity.Customer;
import com.example.rewardsystem.entity.RewardPoint;
import com.example.rewardsystem.service.RewardPointService;

@ExtendWith(MockitoExtension.class)
public class RewardPointControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private RewardPointService rewardPointsService;

	@InjectMocks
	private RewardPointController rewardPointController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(rewardPointController).build();
	}

	@Test
	@Description("Test case for getRewardPointReport API")
	public void testGetRewardPointReport() throws Exception {
		Long customerId = 1L;
		int year = 2025;
		int month = 1;

		Customer customer = new Customer();
		customer.setId(customerId);

		List<RewardPoint> mockRewardPoints = new ArrayList<>();
		mockRewardPoints.add(new RewardPoint(1L, customer, 100, month, year));

		when(rewardPointsService.getRewardPointReport(customerId, month, year)).thenReturn(mockRewardPoints);

		mockMvc.perform(get("/api/rewardPoints/customer").param("customerId", String.valueOf(customerId))
				.param("year", String.valueOf(year)).param("month", String.valueOf(month))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].customer.id").value(customerId))
				.andExpect(jsonPath("$[0].month").value(month)).andExpect(jsonPath("$[0].year").value(year));

		verify(rewardPointsService, times(1)).getRewardPointReport(customerId, month, year);
	}

	@Test
	@Description("Test case for the getRewardPointReport when the customer has no reward points")
	public void testGetRewardPointReport_NoResults() throws Exception {
		Long customerId = 1L;
		int year = 2025;
		int month = 1;

		when(rewardPointsService.getRewardPointReport(customerId, month, year)).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/api/rewardPoints/customer").param("customerId", String.valueOf(customerId))
				.param("year", String.valueOf(year)).param("month", String.valueOf(month))).andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());

		verify(rewardPointsService, times(1)).getRewardPointReport(customerId, month, year);
	}

	@Test
	@Description("Test case for the getAllRewardPointsForCustomer API based on customerId")
	public void testGetAllRewardPointsForCustomer() throws Exception {
		Long customerId = 1L;

		List<RewardPoint> mockRewardPointsList = new ArrayList<>();
		mockRewardPointsList.add(new RewardPoint(2L,
				new Customer(customerId, "", "", "", new ArrayList<>(), new ArrayList<>()), 100, 1, 2025));
		mockRewardPointsList.add(new RewardPoint(3L,
				new Customer(customerId, "", "", "", new ArrayList<>(), new ArrayList<>()), 150, 2, 2025));

		when(rewardPointsService.getAllRewardPointsForCustomer(customerId)).thenReturn(mockRewardPointsList);

		mockMvc.perform(get("/api/rewardPoints/customer/all").param("customerId", String.valueOf(customerId)))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[1].id").value(3L));

		verify(rewardPointsService, times(1)).getAllRewardPointsForCustomer(customerId);
	}

}
