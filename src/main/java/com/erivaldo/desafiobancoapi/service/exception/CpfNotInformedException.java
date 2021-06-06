package com.erivaldo.desafiobancoapi.service.exception;

public class CpfNotInformedException extends SecurityException {
    public CpfNotInformedException(String s) {
        super(s);
    }
}
