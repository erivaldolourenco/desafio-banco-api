package com.erivaldo.desafiobancoapi.config.validation;

public class MessageDto {
	private String message;
	
	

	public MessageDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}