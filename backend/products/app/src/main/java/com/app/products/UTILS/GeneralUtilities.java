package com.app.products.UTILS;


import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GeneralUtilities {

    /*static String passEncode(String password){
        return new BCryptPasswordEncoder().encode(password);
    }*/

    static URI toUri(String uri){
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(uri)
                .toString());
    }
}












