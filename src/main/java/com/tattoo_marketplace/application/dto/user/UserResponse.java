package com.tattoo_marketplace.application.dto.user;

import java.time.LocalDateTime;

public record UserResponse(

        Long id,

        String email,

        String name,

        String location,

        Integer age,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String profilePicturePath
) {

}
