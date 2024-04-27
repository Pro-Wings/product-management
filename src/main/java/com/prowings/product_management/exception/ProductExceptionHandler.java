package com.prowings.product_management.exception;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prowings.product_management.model.ErrorResponse;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(InvalidProductException.class)
	public ResponseEntity<ErrorResponse> handleInvalidProductException(WebRequest webRequest, Exception ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCause(ex.getMessage());
		errorResponse.setStatusCode(400);
		errorResponse.setDescription("The root cause of this is : "+ex.getMessage());

		String reqUrl = webRequest.getContextPath();
		System.out.println(reqUrl);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(WebRequest webRequest, Exception ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCause(ex.getMessage());
		errorResponse.setStatusCode(404);
		errorResponse.setDescription("The root cause of this is : "+ex.getMessage());
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        errorResponse.setTimestamp(now.format(formatter));;

        String reqUrl = webRequest.getContextPath();
		System.out.println(reqUrl);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
		
	}

}
