package com.example.medial.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)  
public class UserNotFoundException extends Exception {

	public UserNotFoundException(String message)   
	{  
	super(message);  
	}
}
