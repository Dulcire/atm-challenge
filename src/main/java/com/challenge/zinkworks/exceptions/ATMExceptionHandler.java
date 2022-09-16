package com.challenge.zinkworks.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ATMExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ATMExceptionHandler.class);

    @ExceptionHandler(ATMException.class)
    public ResponseEntity<Object> handleATMException(final ATMException ex) {
        log.error(String.format("CODE:%s MESSAGE:%s",ex.getCode(),ex.getMessage()));
        return new ResponseEntity<Object>(ex.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
