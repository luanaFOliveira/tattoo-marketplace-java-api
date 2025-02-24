package com.tattoo_marketplace.application.dto.status;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusRequest {
    
    @NotBlank(message = "Name cannot be empty")
    private String name;
}
