package com.request.request.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serial;

public class ClientRequestNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 5071646428281007896L;

    public ClientRequestNotFoundException(String message){
        super(message);
    }
}
