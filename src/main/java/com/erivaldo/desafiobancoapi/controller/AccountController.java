package com.erivaldo.desafiobancoapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.controller.dto.BalanceDto;
import com.erivaldo.desafiobancoapi.controller.dto.TransferDto;
import com.erivaldo.desafiobancoapi.controller.form.AccountForm;
import com.erivaldo.desafiobancoapi.controller.form.BalanceForm;
import com.erivaldo.desafiobancoapi.controller.form.TransferForm;
import com.erivaldo.desafiobancoapi.model.Account;
import com.erivaldo.desafiobancoapi.service.AccountService;

@CrossOrigin
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
	public ResponseEntity<AccountDto> newAccount(@RequestBody @Valid AccountForm form, UriComponentsBuilder uriBuilder) {
			Account account;
			if(form.getAccountNumber() == null) {
				 account = form.convert();
			}else {
				 account = form.convertWithAccountNumber();
			}
			return accountService.save(account, uriBuilder);
	}
	
	@GetMapping
	@RequestMapping("/account")
	public AccountDto getAccount(@RequestParam Long id ) {
		return accountService.get(id);
	}
	
	@PostMapping
	@RequestMapping("/deposit")
	public ResponseEntity<BalanceDto> depositAccount(@RequestBody  BalanceForm form){
		return accountService.deposit(form.getAccountId(), form.getValue());
	}
	
	@PostMapping
	@RequestMapping("/cashout")
	public ResponseEntity<BalanceDto> cashOutAccount(@RequestBody  BalanceForm form){
		return accountService.cashOut(form.getAccountId(), form.getValue());
	}
	
	@PostMapping
	@RequestMapping("/transfer")
	public ResponseEntity<TransferDto> transferAccount(@RequestBody  TransferForm form){
		return accountService.transfer(form.getDepositorID(),form.getBeneficiaryID() ,form.getValue());
	}
}
