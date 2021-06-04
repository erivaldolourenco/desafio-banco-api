package com.erivaldo.desafiobancoapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.config.validation.MessageDto;
import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.model.Account;

public interface AccountService {
	
	public List<AccountDto> list(); 
	
	public AccountDto get(Long accountId); 
	
	public ResponseEntity<MessageDto> save(Account account, UriComponentsBuilder uriBuilder) ;
	
	public ResponseEntity<MessageDto> deposit(Long accountId, double value);
	
	public ResponseEntity<MessageDto> cashOut(Long accountId, double value);
	
	public ResponseEntity<MessageDto> transfer(Long depositorID, Long beneficiaryID, double value);
}
