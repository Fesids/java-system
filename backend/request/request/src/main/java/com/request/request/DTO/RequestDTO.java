package com.request.request.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestDTO(

        String request_image,


        @NotEmpty @NotNull String sender_dept_id,

        @NotEmpty @NotNull String destination_dept_id,
        @NotEmpty @NotNull String subject,

        @NotEmpty @NotNull String body


) {
}
