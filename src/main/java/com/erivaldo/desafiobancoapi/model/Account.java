package com.erivaldo.desafiobancoapi.model;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	private Long accountNumber;
	private String name;
	private String cpf;
	private double balance;
	
	public Account(){}
	
	public  Account(String name, String cpf, double balance ) {
		this.name = name;
		this.cpf = cpf;
		this.balance = balance;
		this.accountNumber = (long) new Random().nextInt(999999);
	}
	
	public  Account(String name, String cpf, double balance, long accountNumber ) {
		this.name = name;
		this.cpf = cpf;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}
	
	
	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

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

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
}
