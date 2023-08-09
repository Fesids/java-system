package com.registration.registration.DTO;

import com.registration.registration.models.Friend;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record ProfileDTO(


        @NotNull @NotEmpty Long user_id,

        @NotNull @NotEmpty String name,

        @NotNull @NotEmpty String pic,

        Set<Long> friends

) {
}
