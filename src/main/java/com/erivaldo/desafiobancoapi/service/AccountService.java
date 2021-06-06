package com.erivaldo.desafiobancoapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.dto.BalanceDto;
import com.erivaldo.desafiobancoapi.controller.dto.TransferDto;
import com.erivaldo.desafiobancoapi.model.Account;

public interface AccountService {
	
	public List<AccountDto> list(); 
	
	public AccountDto get(Long accountId); 
	
	public ResponseEntity<AccountDto> save(Account account, UriComponentsBuilder uriBuilder) ;
	
	public ResponseEntity<BalanceDto> deposit(Long accountId, double value);
	
	public ResponseEntity<BalanceDto> cashOut(Long accountId, double value);
	
	public ResponseEntity<TransferDto> transfer(Long depositorID, Long beneficiaryID, double value);
}
