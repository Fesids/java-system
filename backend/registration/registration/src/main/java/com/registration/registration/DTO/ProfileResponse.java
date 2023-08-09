package com.registration.registration.DTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public class ProfileResponse {

    private Long id;

    private Long user_id;

    private String name;

    private String pic;


    private Set<?> friends;

}
