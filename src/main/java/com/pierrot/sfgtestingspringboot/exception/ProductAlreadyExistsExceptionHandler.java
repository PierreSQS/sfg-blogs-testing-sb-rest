package com.pierrot.sfgtestingspringboot.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductAlreadyExistsExceptionHandler {

    @Value(value = "${data.exception.message1}")
    private String message1;

    @ExceptionHandler(ProductAlreadyExistsException.class)
    ResponseEntity<String> productAlreadyExistsException(ProductAlreadyExistsException paeException){
        return new ResponseEntity<>(message1, HttpStatus.CONFLICT);

    }

}
