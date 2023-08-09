package com.request.request.UTILS;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GeralUtilities {

    static URI toURi(String uri){
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(uri).toString());
    }

}
