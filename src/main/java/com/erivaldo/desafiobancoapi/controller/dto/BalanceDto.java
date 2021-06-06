package com.erivaldo.desafiobancoapi.controller.dto;

public class BalanceDto {
	private Long accountId;
	private double newBalance;
	private String message;
	
	
	public BalanceDto() {}
	
	public BalanceDto(Long accountId, double balance) {
		this.accountId = accountId;
		this.newBalance = balance;
		this.message = "";
	}
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return newBalance;
	}
	public void setBalance(double value) {
		this.newBalance = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
