package com.example.rewardsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<com.example.rewardsystem.exception.ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        com.example.rewardsystem.exception.ErrorResponse errorResponse=new com.example.rewardsystem.exception.ErrorResponse("RESOURCE_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<com.example.rewardsystem.exception.ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.example.rewardsystem.exception.ErrorResponse> handleGenericException(Exception ex) {
        com.example.rewardsystem.exception.ErrorResponse errorResponse=new ErrorResponse("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
