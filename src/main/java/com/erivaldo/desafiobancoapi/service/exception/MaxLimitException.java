package com.erivaldo.desafiobancoapi.service.exception;

public class MaxLimitException extends SecurityException{
    public MaxLimitException(String s) {
        super(s);
    }
}
