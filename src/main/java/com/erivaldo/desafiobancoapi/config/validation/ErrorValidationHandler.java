package com.erivaldo.desafiobancoapi.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorValidationHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code =  HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<MessageDto> handle(MethodArgumentNotValidException exception) {
		
		List<MessageDto> erros = new ArrayList<>();
		
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		
		fieldErros.forEach(e -> {
				
			MessageDto erro = new MessageDto(messageSource.getMessage(e, LocaleContextHolder.getLocale()));
			erros.add(erro);
		});
		
		return erros;
	}
}
