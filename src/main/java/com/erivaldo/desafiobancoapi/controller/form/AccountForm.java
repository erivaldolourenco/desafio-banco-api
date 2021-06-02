package com.erivaldo.desafiobancoapi.controller.form;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.erivaldo.desafiobancoapi.config.validation.Cpf;
import com.erivaldo.desafiobancoapi.model.Account;


public class AccountForm {
	
	private String name;
	
	@Cpf @NotBlank(message ="É necessário informar um cpf para abertura de nova conta.") 
	private String cpf;
	
	@Min(value = 50, message = "Saldo insuficiente para abertura de nova conta.")
	private double balance;
	
	
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
	public Account convert() {
		return new Account(name,cpf,balance);
	}
	public boolean validationCpf() {
		if(this.getCpf() == "11111111111") {
			return false;
		}else {
			return true;
		}
		
	}
	
}
