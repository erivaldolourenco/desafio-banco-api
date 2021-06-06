package com.erivaldo.desafiobancoapi.controller.dto;

public class TransferDto {
	private Long depositorID;
	private Long beneficiaryID;
	private double value;
	private String message;
	
	
	public Long getDepositorID() {
		return depositorID;
	}
	public void setDepositorID(Long depositorID) {
		this.depositorID = depositorID;
	}
	public Long getBeneficiaryID() {
		return beneficiaryID;
	}
	public void setBeneficiaryID(Long beneficiaryID) {
		this.beneficiaryID = beneficiaryID;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
