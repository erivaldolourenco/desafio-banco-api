package com.erivaldo.desafiobancoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	private String name;
	private String cpf;
	private double balance;
	
	public Account(){}
	
	public  Account(String name, String cpf, double balance ) {
		this.name = name;
		this.cpf = cpf;
		this.balance = balance;
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
	
}
