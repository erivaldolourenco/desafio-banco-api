package com.erivaldo.desafiobancoapi.service.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String s) {
        super(s);
    }
}
