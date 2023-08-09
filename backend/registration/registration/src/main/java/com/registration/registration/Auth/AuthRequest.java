package com.registration.registration.Auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthRequest(
        @NotEmpty @NotNull String username,

        @NotEmpty @NotNull String password

) {
}
