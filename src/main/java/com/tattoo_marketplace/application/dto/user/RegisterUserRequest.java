package com.tattoo_marketplace.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;  // Adicione essa importação
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.tattoo_marketplace.infra.utils.ValidationMessage;

@Data
public class RegisterUserRequest implements PasswordValidation{
    
    @NotBlank(message = "email" + ValidationMessage.MUST_BE_SPECIFIED)
    @Email(message = "email has incorrect format")
    private String email;
    
    @NotBlank(message = "password" + ValidationMessage.MUST_BE_SPECIFIED)
    @Pattern(message = "password must contain at least 8 characters", regexp = ".{8,}")
    private String password;

    @NotBlank(message = "passwordConfirm" + ValidationMessage.MUST_BE_SPECIFIED)
    @Pattern(message = "passwordConfirm must contain at least 8 characters", regexp = ".{8,}")
    private String passwordConfirm;

    @NotBlank(message = "name" + ValidationMessage.MUST_BE_SPECIFIED)
    private String name;

    @NotBlank(message = "location" + ValidationMessage.MUST_BE_SPECIFIED)
    private String location;

    @NotNull(message = "age" + ValidationMessage.MUST_BE_SPECIFIED)
    private Integer age;

}
