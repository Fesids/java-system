package com.registration.registration.Exceptions.handler;

import com.registration.registration.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserTrackerGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException userNotFoundException, WebRequest request){
        return super.handleExceptionInternal(userNotFoundException, userNotFoundException.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }


}
