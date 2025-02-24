package com.tattoo_marketplace.application.dto.tattooartist;

import com.tattoo_marketplace.application.dto.user.RegisterUserRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;

@Data
public class RegisterTattooArtistRequest extends RegisterUserRequest {

    @NotNull(message = "rate must be specified")
    private Integer rate;

    private Set<Long> categoryIds; 
}
