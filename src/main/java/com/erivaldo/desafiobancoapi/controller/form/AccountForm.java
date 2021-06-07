package com.erivaldo.desafiobancoapi.controller.form;

import com.erivaldo.desafiobancoapi.model.Account;


public class AccountForm {
	
	private String name;
	private String cpf;	
	private double balance;
	private Long accountNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Account convert() {
		return new Account(name,cpf,balance);
	}
	public Account convertWithAccountNumber() {
		return new Account(name,cpf,balance, accountNumber);
	}
	
	@Override
	public String toString() {
		return "AccountForm [name=" + name + ", cpf=" + cpf + ", balance=" + balance + "]";
	}
	
}
