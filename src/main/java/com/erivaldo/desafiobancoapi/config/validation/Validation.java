package com.erivaldo.desafiobancoapi.config.validation;

import com.erivaldo.desafiobancoapi.model.Account;
import com.erivaldo.desafiobancoapi.service.exception.CpfNotInformedException;
import com.erivaldo.desafiobancoapi.service.exception.MaxLimitException;
import com.erivaldo.desafiobancoapi.service.exception.MinimalBalanceException;
import com.erivaldo.desafiobancoapi.service.exception.WithoutBalanceException;

public class Validation {
	
	public Validation() {}
	
    public void checkOperation(Double value, Account depositor) {
    	checkMaxLimit(value);
    	checkBalance(value, depositor.getBalance());
    }

    public void checkMaxLimit(double value) {
        if(value > 500) {
            throw new MaxLimitException("Esta operação tem um limite máximo de 500 por operação.");
        }
    }
    
    public void checkBalance(double value, double saldo) {
        if(value > saldo) {
            throw new WithoutBalanceException("Saldo insuficiente para a operação.");
        }
    }
    
    public void checkMinimalBalance(double value) {
        if(value < 50) {
            throw new MinimalBalanceException("Saldo insuficiente para abertura de nova conta.");
        }
    }
    
    public void checkCPF(String cpf) {
        if(cpf.isEmpty()) {
            throw new CpfNotInformedException("É necessário informar um cpf para abertura de nova conta.");
        }else if (cpf.equals("111111111111")){
    	throw new CpfNotInformedException("CPF informado para criação de conta está inválido.");
        }
    }
    
}
