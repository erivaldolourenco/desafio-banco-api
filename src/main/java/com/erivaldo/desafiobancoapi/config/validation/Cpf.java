package com.erivaldo.desafiobancoapi.config.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfValidation.class)
public @interface Cpf {

	String message() default "CPF informado para criação de conta está inválido.";
	
	Class<?>[] groups() default {};
	public abstract Class<? extends Payload>[] payload() default {};
}
