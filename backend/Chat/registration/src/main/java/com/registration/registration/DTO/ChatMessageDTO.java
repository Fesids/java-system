package com.registration.registration.DTO;

import com.registration.registration.models.Profile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChatMessageDTO(

        @NotEmpty @NotNull Long msg_sender,

        @NotEmpty @NotNull Long msg_receiver,

        @NotEmpty @NotNull String body


) {
}
