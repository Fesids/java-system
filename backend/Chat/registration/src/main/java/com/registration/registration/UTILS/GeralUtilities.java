package com.registration.registration.UTILS;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GeralUtilities {

    static URI toURI(String uri){
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(uri).toString());
    }
}
