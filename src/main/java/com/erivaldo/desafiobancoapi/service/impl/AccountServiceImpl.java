package com.erivaldo.desafiobancoapi.service.impl;


import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.config.validation.Validation;
import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.dto.BalanceDto;
import com.erivaldo.desafiobancoapi.controller.dto.TransferDto;
import com.erivaldo.desafiobancoapi.model.Account;
import com.erivaldo.desafiobancoapi.repository.AccountRepository;
import com.erivaldo.desafiobancoapi.service.AccountService;
import com.erivaldo.desafiobancoapi.service.exception.MaxLimitException;
import com.erivaldo.desafiobancoapi.service.exception.WithoutBalanceException;

@Service
public class AccountServiceImpl  implements AccountService{
	
	private AccountRepository accountRepository;
	private Validation valid = new Validation();
	
	public AccountServiceImpl(AccountRepository accountRepository){
		this.accountRepository = accountRepository;
	}
	
	@Override
	public List<AccountDto> list() {
		List<Account> accounts = accountRepository.findAll();
		return AccountDto.convert(accounts);
	}
	
	@Override
	public AccountDto get(Long accountId) {
		try {
			Account account = accountRepository.getById(accountId);
			return new AccountDto(account);
		}catch(EntityNotFoundException e) {
			return null;
		}
	}
	
	@Override
	public ResponseEntity<AccountDto> save(Account account, UriComponentsBuilder uriBuilder) {
		AccountDto accountDto = new AccountDto();
		try {
			valid.checkMinimalBalance(account.getBalance());
			valid.checkCPF(account.getCpf());
			accountRepository.save(account);	
			URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(account.getAccountId()).toUri();
			accountDto = new AccountDto(account);
			accountDto.setMessage("Conta cadastrada com sucesso!");
			return ResponseEntity.created(uri).body(accountDto);
		}catch ( Exception e ) {
			accountDto.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(accountDto);
		}
	}

	@Override
	public ResponseEntity<BalanceDto> deposit(Long accountId, double value) {
		BalanceDto newBalance =  new BalanceDto();
		try {
			Account account = accountRepository.getById(accountId);
			account.setBalance(account.getBalance()+value);
			accountRepository.save(account);
			newBalance.setAccountId(account.getAccountId());
			newBalance.setBalance(account.getBalance());
			newBalance.setMessage("Depósito realizado com sucesso!");
			return ResponseEntity.accepted().body(newBalance);
		}catch(EntityNotFoundException e) {
			newBalance.setMessage("Esta conta não existe!");
			return ResponseEntity.badRequest().body(newBalance);
	    }
	}

	@Override
	public ResponseEntity<BalanceDto> cashOut(Long accountId, double value) {
		BalanceDto newBalance =  new BalanceDto();
		try {
			Account account = accountRepository.getById(accountId);
			valid.checkOperation(value, account);
			account.setBalance(account.getBalance()-value);
			accountRepository.save(account);
			newBalance.setAccountId(account.getAccountId());
			newBalance.setBalance(account.getBalance());
			newBalance.setMessage("Saque realizado com sucesso!");
			return ResponseEntity.accepted().body(newBalance);
			
		} catch (MaxLimitException | WithoutBalanceException e) {
			newBalance.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(newBalance);
		}
		 catch(EntityNotFoundException e) {
			newBalance.setMessage("Esta conta não existe!");
	     	return ResponseEntity.badRequest().body(newBalance);
	    }
	}

	@Override
	public ResponseEntity<TransferDto> transfer(Long depositorID, Long beneficiaryID, double value) {
		TransferDto trf = new TransferDto();
        try {
            Account depositor = accountRepository.getById(depositorID);
            Account beneficiary = accountRepository.getById(beneficiaryID);

            valid.checkOperation(value, depositor);
            return execTranfer(depositor, beneficiary, value);

        } catch ( MaxLimitException | WithoutBalanceException e) {
        	trf.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(trf);
        } catch(EntityNotFoundException e) {
        	trf.setMessage("Uma conta nao foi encontrada");
        	return ResponseEntity.badRequest().body(trf);
        }
	}
	
    private ResponseEntity<TransferDto> execTranfer(Account depositor, Account beneficiary, double value) {
    	depositor.setBalance(depositor.getBalance() - value);
		beneficiary.setBalance(beneficiary.getBalance() + value);
		accountRepository.save(depositor);
		accountRepository.save(beneficiary);
		
		TransferDto trf = new TransferDto();
		trf.setMessage("Transferência realizada com sucesso!");
		
		return ResponseEntity.accepted().body(trf);
    	
    }

	
}