package com.registration.registration.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FriendDTO

    (@NotNull @NotEmpty Long profile_id){
}
