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
import com.erivaldo.desafiobancoapi.service.exception.MaxLimitException;
import com.erivaldo.desafiobancoapi.service.exception.WithoutBalanceException;

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
		
		try {
			Account account = accountRepository.getById(accountId);
			checkOperation(value, account);
			account.setBalance(account.getBalance()-value);
			accountRepository.save(account);
			return ResponseEntity.accepted().body("Saque realizado com sucesso!");
			
		} catch (MaxLimitException | WithoutBalanceException e) {
			return ResponseEntity.accepted().body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> transfer(Long depositorID, Long beneficiaryID, double value) {
		
		
        try {
            Account depositor = accountRepository.getById(depositorID);
            Account beneficiary = accountRepository.getById(beneficiaryID);

            checkOperation(value, depositor);
            return execTranfer(depositor, beneficiary, value);

        } catch ( MaxLimitException | WithoutBalanceException e) {
            return ResponseEntity.accepted().body(e.getMessage());
        }
	}
	
    private void checkOperation(Double value, Account depositor) {
    	checkMaxLimit(value);
    	checkBalance(value, depositor.getBalance());
    }

    private void checkMaxLimit(double value) {
        if(value > 500) {
            throw new MaxLimitException("Operação de transferência tem um limite máximo de 500 por operação.");
        }
    }
    private void checkBalance(double value, double saldo) {
        if(value > saldo) {
            throw new WithoutBalanceException("Saldo insuficiente para a operação.");
        }
    }
    
    private ResponseEntity<String> execTranfer(Account depositor, Account beneficiary, double value) {
    	depositor.setBalance(depositor.getBalance() - value);
		beneficiary.setBalance(beneficiary.getBalance() + value);
		accountRepository.save(depositor);
		accountRepository.save(beneficiary);
		return ResponseEntity.accepted().body("Transferência realizada com sucesso!");
    	
    }

	
}