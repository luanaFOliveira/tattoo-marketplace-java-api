package com.tattoo_marketplace.application.dto.quote;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.tattoo_marketplace.infra.utils.ValidationMessage;

@Data
public class RegisterQuoteRequest {

    @NotBlank(message = "description" + ValidationMessage.MUST_BE_SPECIFIED)
    @Size(max = 500, message = "description must be at most 500 characters long")
    private String description;

    @NotBlank(message = "placement" + ValidationMessage.MUST_BE_SPECIFIED)
    private String placement;

    @NotBlank(message = "color" + ValidationMessage.MUST_BE_SPECIFIED)
    private String color;

    @NotNull(message = "size" + ValidationMessage.MUST_BE_SPECIFIED)
    @Positive(message = "size must be a positive number")
    private Number size;

    @NotNull(message = "price" + ValidationMessage.MUST_BE_SPECIFIED)
    @Positive(message = "price must be a positive number")
    private Number price;

    @NotNull(message = "userId" + ValidationMessage.MUST_BE_SPECIFIED)
    private Long userId;

    @NotNull(message = "tattooArtistId" + ValidationMessage.MUST_BE_SPECIFIED)
    private Long tattooArtistId;

    @NotNull(message = "statusId" + ValidationMessage.MUST_BE_SPECIFIED)
    private Long statusId;
}
