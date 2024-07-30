package com.bankjava.proje.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
	
	public ResponseEntity<ErrorDetails> handleAccountException( AccountException accountException,
																WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				accountException.getMessage(),
				webRequest.getDescription(false), "ACCOUNT_NOT_FOUND"				
				);
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	
	}
	public ResponseEntity<ErrorDetails> handlerGenericException(Exception exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"INTERNAL_SERVER_ERROR"
				); 
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR );
	}

}
