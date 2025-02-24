package com.tattoo_marketplace.application.dto.user;

import com.tattoo_marketplace.infra.utils.ValidationMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email" + ValidationMessage.MUST_BE_SPECIFIED)
    @Email(message = "email has incorrect format")
    private String email;

    @NotBlank(message = "password" + ValidationMessage.MUST_BE_SPECIFIED)
    private String password;
};
