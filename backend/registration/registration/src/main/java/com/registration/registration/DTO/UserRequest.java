package com.registration.registration.DTO;


import com.registration.registration.ENUMS.URole;
import com.registration.registration.UTILS.GeneralUtilities;
import com.registration.registration.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserRequest(
        @NotNull @NotEmpty String username,

        @NotNull @NotEmpty @Email String email,

        @NotNull @NotEmpty String password,

         String department_id
) {
    public User createClient(UserRequest userRequest){
        return User.builder().email(userRequest.email)
                .username(userRequest.username)
                .password(GeneralUtilities.passEncode(userRequest.password))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(URole.EXTERNAL_USER)
                .build();
    }

    public User createAdmin(UserRequest userRequest){
        return User.builder().email(userRequest.email)
                .username(userRequest.username)
                .password(GeneralUtilities.passEncode(userRequest.password))
                .createdAt(LocalDateTime.now())
                .department_id(userRequest.department_id())
                .updatedAt(LocalDateTime.now())
                .role(URole.ADMIN)
                .build();
    }

    public User createEmployee(UserRequest userRequest){
        return User.builder().email(userRequest.email)
                .username(userRequest.username)
                .password(GeneralUtilities.passEncode(userRequest.password))
                .department_id(userRequest.department_id())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(URole.EMPLOYEE)
                .build();
    }









}
