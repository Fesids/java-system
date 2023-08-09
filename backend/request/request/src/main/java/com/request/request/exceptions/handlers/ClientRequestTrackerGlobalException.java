package com.request.request.exceptions.handlers;

import com.request.request.exceptions.ClientRequestNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ClientRequestTrackerGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ClientRequestNotFoundException.class})
    public ResponseEntity<?> handleClientRequestNotFound(ClientRequestNotFoundException clientRequestNotFoundException, WebRequest request){
        return super.handleExceptionInternal(clientRequestNotFoundException, clientRequestNotFoundException.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }



}
