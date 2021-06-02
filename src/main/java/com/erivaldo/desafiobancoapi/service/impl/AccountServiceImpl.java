package com.erivaldo.desafiobancoapi.service.impl;


import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.model.Account;
import com.erivaldo.desafiobancoapi.repository.AccountRepository;
import com.erivaldo.desafiobancoapi.service.AccountService;

@Service
public class AccountServiceImpl  implements AccountService{
	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository){
		this.accountRepository = accountRepository;
	}
	
	public List<AccountDto> list() {
		List<Account> accounts = accountRepository.findAll();
		return AccountDto.convert(accounts);
	}
	
	public ResponseEntity<String> save(Account account, UriComponentsBuilder uriBuilder) {
		accountRepository.save(account);
		URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(account.getAccountId()).toUri();
		return ResponseEntity.created(uri).body("Conta cadastrada com sucesso!");
	}

	@Override
	public ResponseEntity<String> deposit(Long accountId, double value) {
		Account account = accountRepository.getById(accountId);
		account.setBalance(account.getBalance()+value);
		accountRepository.save(account);
		return ResponseEntity.accepted().body("Depósito realizado com sucesso!");
	}

	@Override
	public ResponseEntity<String> cashOut(Long accountId, double value) {
		
		Account account = accountRepository.getById(accountId);
		
		if(value > 500) {
			return ResponseEntity.accepted().body("Operação de transferência tem um limite máximo de 500 por operação.");
		}else if(value > account.getBalance()) {
			return ResponseEntity.accepted().body("Saldo insuficiente para a operação.");
		}else {
			account.setBalance(account.getBalance()-value);
			accountRepository.save(account);
			return ResponseEntity.accepted().body("Saque realizado com sucesso!");
		}
		
	}

	@Override
	public ResponseEntity<String> transfer(Long depositorID, Long beneficiaryID, double value) {
		
		Account depositor = accountRepository.getById(depositorID);
		Account beneficiary = accountRepository.getById(beneficiaryID);
		
		if(value > 500) {
			return ResponseEntity.accepted().body("Operação de transferência tem um limite máximo de 500 por operação.");
		}else if(value > depositor.getBalance()) {
			return ResponseEntity.accepted().body("Saldo insuficiente para a operação.");
		}else {
			depositor.setBalance(depositor.getBalance() - value);
			beneficiary.setBalance(beneficiary.getBalance() + value);
			accountRepository.save(depositor);
			accountRepository.save(beneficiary);
			return ResponseEntity.accepted().body("Transferência realizada com sucesso!");
		}
		
		
		
	}
}