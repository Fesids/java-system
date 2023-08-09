package com.registration.registration.exceptions.handlers;

import com.registration.registration.exceptions.ProfileNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serial;

public class ProfileTrackerGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProfileNotFoundException.class})
    public ResponseEntity<?> handleProfileNotFound(ProfileNotFoundException profileNotFoundException, WebRequest profile){
        return super.handleExceptionInternal(profileNotFoundException, profileNotFoundException.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, profile);
    }



}
