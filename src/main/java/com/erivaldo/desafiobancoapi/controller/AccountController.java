package com.erivaldo.desafiobancoapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.form.AccountForm;
import com.erivaldo.desafiobancoapi.controller.form.BalanceForm;
import com.erivaldo.desafiobancoapi.controller.form.TransferForm;
import com.erivaldo.desafiobancoapi.model.Account;
import com.erivaldo.desafiobancoapi.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	

	private final AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
	@GetMapping
	public List<AccountDto> list(){
		return accountService.list();
	}
	
	@PostMapping
	public ResponseEntity<String> newAccount(@RequestBody @Valid AccountForm form, UriComponentsBuilder uriBuilder) {
			
			Account account = form.convert();
			return accountService.save(account, uriBuilder);
	}
	
	@PostMapping
	@RequestMapping("/deposit")
	public ResponseEntity<String> depositAccount(@RequestBody  @Valid BalanceForm form){
		return accountService.deposit(form.getAccountId(), form.getValue());
	}
	
	@PostMapping
	@RequestMapping("/cashout")
	public ResponseEntity<String> cashOutAccount(@RequestBody  @Valid BalanceForm form){
		return accountService.cashOut(form.getAccountId(), form.getValue());
	}
	
	@PostMapping
	@RequestMapping("/transfer")
	public ResponseEntity<String> transferAccount(@RequestBody  @Valid TransferForm form){
		return accountService.transfer(form.getDepositorID(),form.getBeneficiaryID() ,form.getValue());
	}
}
