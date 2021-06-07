package com.erivaldo.desafiobancoapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.erivaldo.desafiobancoapi.model.Account;
//import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

public class AccountDto {
	private Long accountId;
	private String name;
	private String cpf;
	private double balance;
	private String message;
	private Long accountNumber;
	
	public AccountDto() {}
	
	public AccountDto(Account account) {
		this.accountId = account.getAccountId();
		this.name = account.getName();
		this.cpf = account.getCpf();
		this.balance =  account.getBalance();
		this.accountNumber = account.getAccountNumber();
	}

	public Long getAccountId() {
		return accountId;
	}
	
	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public double getBalance() {
		return balance;
	}
	
	
	
	public Long getAccountNumber() {
		return accountNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static List<AccountDto> convert(List<Account> bankAccounts) {
		return bankAccounts.stream().map(AccountDto::new).collect(Collectors.toList());
	}
}
