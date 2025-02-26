package com.tattoo_marketplace.application.dto.tattoo_artist;

import com.tattoo_marketplace.application.dto.user.RegisterUserRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterTattooArtistRequest extends RegisterUserRequest {

    @NotNull(message = "rate must be specified")
    private Integer rate;

    private Set<Long> categoryIds; 
}
