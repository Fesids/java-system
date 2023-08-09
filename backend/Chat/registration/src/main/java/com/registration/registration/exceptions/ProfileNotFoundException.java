package com.registration.registration.exceptions;

import java.io.Serial;

public class ProfileNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5071646428281007896L;

    public ProfileNotFoundException(String message){
        super(message);
    }




}
