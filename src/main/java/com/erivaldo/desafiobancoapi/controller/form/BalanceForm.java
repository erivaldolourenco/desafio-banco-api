package com.erivaldo.desafiobancoapi.controller.form;


public class BalanceForm {
	
	private Long accountId;
	private double value;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "BalanceForm [accountId=" + accountId + ", value=" + value + "]";
	}
	
	
	
}
