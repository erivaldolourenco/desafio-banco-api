package com.erivaldo.desafiobancoapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.model.Account;

public interface AccountService {
	
	public List<AccountDto> list(); 
	
	public ResponseEntity<String> save(Account account, UriComponentsBuilder uriBuilder) ;
	
	public ResponseEntity<String> deposit(Long accountId, double value);
	
	public ResponseEntity<String> cashOut(Long accountId, double value);
	
	public ResponseEntity<String> transfer(Long depositorID, Long beneficiaryID, double value);
}
