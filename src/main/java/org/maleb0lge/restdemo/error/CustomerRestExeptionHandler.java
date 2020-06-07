package org.maleb0lge.restdemo.error;

import org.maleb0lge.restdemo.StudentErrorResponse;
import org.maleb0lge.restdemo.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExeptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException e) {
        CustomerErrorResponse error = new CustomerErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception e) {
        CustomerErrorResponse error = new CustomerErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
