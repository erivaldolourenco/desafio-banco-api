package com.erivaldo.desafiobancoapi.controller.dto;

public class BalanceDto {
	private Long accountId;
	private double balance;
	private String message;
	
	
	public BalanceDto() {}
	
	public BalanceDto(Long accountId, double balance) {
		this.accountId = accountId;
		this.balance = balance;
		this.message = "";
	}
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double value) {
		this.balance = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "BalanceDto [accountId=" + accountId + ", newBalance=" + balance + ", message=" + message + "]";
	}
	
	
}
