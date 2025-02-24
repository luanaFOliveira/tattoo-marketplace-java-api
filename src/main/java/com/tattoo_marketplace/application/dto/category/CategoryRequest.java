package com.tattoo_marketplace.application.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    
    @NotBlank(message = "Name cannot be empty")
    private String name;
}
