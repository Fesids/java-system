package com.request.request.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRequestDTO(
        @NotEmpty @NotNull String sender_dept_id,

        @NotEmpty @NotNull String destination_dept_id,

        Long user_sender_id
) {
}
