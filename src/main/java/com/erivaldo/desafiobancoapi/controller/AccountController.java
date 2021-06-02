package com.erivaldo.desafiobancoapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erivaldo.desafiobancoapi.controller.dto.AccountDto;
import com.erivaldo.desafiobancoapi.model.Account;
import com.erivaldo.desafiobancoapi.repository.AccountRepository;

@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping("/accounts")
	public List<AccountDto> list(){
		
		List<Account> accounts = accountRepository.findAll();
		return AccountDto.convert(accounts);
	}
}
