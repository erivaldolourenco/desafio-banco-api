package com.erivaldo.desafiobancoapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.erivaldo.desafiobancoapi.model.Account;

public class AccountDto {
	private String name;
	private String cpf;
	private double balance;
	
	public AccountDto(Account account) {
		this.name = account.getName();
		this.cpf = account.getCpf();
		this.balance =  account.getBalance();
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

	public static List<AccountDto> convert(List<Account> bankAccounts) {
		return bankAccounts.stream().map(AccountDto::new).collect(Collectors.toList());
	}
}
