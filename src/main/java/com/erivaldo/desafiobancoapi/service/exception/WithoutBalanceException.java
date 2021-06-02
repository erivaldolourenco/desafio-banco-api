package com.erivaldo.desafiobancoapi.service.exception;

public class WithoutBalanceException extends SecurityException{
    public WithoutBalanceException(String s) {
        super(s);
    }
}
