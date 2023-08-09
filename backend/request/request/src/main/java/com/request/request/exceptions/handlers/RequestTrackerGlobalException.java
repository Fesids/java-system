package com.request.request.exceptions.handlers;

import com.request.request.exceptions.RequestNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RequestTrackerGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RequestNotFoundException.class})
    public ResponseEntity<?> handleRequestNotFound(RequestNotFoundException requestNotFoundException, WebRequest request){
        return super.handleExceptionInternal(requestNotFoundException, requestNotFoundException.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

}
