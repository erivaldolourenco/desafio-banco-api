package com.erivaldo.desafiobancoapi.service.exception;

public class MinimalBalanceException extends SecurityException {
    public MinimalBalanceException(String s) {
        super(s);
    }
}
