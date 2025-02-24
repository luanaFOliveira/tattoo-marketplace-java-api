package com.tattoo_marketplace.application.dto.user;

import com.tattoo_marketplace.application.dto.user.PasswordValidation;
import com.tattoo_marketplace.infra.utils.ValidationMessage;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateUserRequest implements PasswordValidation {
    @Email(message = "email has incorrect format")
    private String email;
    
    @Pattern(message = "password must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter and 1 digit", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")
    private String password;
    
    @Pattern(message = "passwordConfirm must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter and 1 digit", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")
    private String passwordConfirm;

    private String name;

    private String location;

    private Integer age;

}
