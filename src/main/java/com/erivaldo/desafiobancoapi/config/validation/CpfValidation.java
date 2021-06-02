package com.erivaldo.desafiobancoapi.config.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidation implements ConstraintValidator<Cpf, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println(value);
		if(value.equals("11111111111")){
			return false;
		}else {
			return true;
		}
		
	}

}
