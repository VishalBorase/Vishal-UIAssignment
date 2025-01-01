package com.example.rewardsystem.dto;

import java.time.LocalDate;
import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class CustomerTransactionRequest {
	
	@NotNull(message = "Customer ID is required")
	private Long customerId;

	@DecimalMin(value = "0.0", message = "Amount must be greater than 0")
	private Double amount;

	@NotNull(message = "Transaction date is required")
	@FutureOrPresent(message = "Transaction date cannot be in the past")
	private Date date;
	
	private String spentDetails;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSpentDetails() {
		return spentDetails;
	}

	public void setSpentDetails(String spentDetails) {
		this.spentDetails = spentDetails;
	}
	


}
