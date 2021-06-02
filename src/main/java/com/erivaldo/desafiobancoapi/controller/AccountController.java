package com.erivaldo.desafiobancoapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.form.AccountForm;
import com.erivaldo.desafiobancoapi.model.Account;
import com.erivaldo.desafiobancoapi.repository.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping
	public List<AccountDto> list(){
		List<Account> accounts = accountRepository.findAll();
		return AccountDto.convert(accounts);
	}
	
	@PostMapping
	public ResponseEntity<String> newAccount(@RequestBody @Valid AccountForm form, UriComponentsBuilder uriBuilder) {
		
			Account account = form.convert();
			accountRepository.save(account);
			URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(account.getAccountId()).toUri();
			return ResponseEntity.created(uri).body("Conta cadastrada com sucesso!");
		
	}
	
	@PostMapping
	@RequestMapping("/deposit")
	public ResponseEntity<String> depositAccount(@RequestBody AccountForm form){
		return null;
	}
	
}
