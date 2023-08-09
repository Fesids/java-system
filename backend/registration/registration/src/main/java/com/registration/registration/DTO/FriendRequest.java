package com.registration.registration.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FriendRequest(

        @NotNull @NotEmpty Long profile_id
) {
}
