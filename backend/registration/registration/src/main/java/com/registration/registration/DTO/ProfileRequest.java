package com.registration.registration.DTO;

import java.util.Set;

public record ProfileRequest(
        Long user_id,
        String name,
        String pic,
        Set<?> friends

) {
}
