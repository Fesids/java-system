package com.request.request.exceptions;

import java.io.Serial;

public class RequestNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 5071646428281007896L;

    public RequestNotFoundException(String message){
        super(message);
    }

}
