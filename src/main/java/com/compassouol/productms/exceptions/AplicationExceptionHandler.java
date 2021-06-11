package com.compassouol.productms.exceptions;

import javax.swing.JSpinner.DefaultEditor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compassouol.productms.exceptions.dto.DefaultError;




@ControllerAdvice
public class AplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e ) {
	
		
		DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(),"Erro ao processar sua requisição");
		
		
		return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
	}
	
}
