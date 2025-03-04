package com.tattoo_marketplace.application.dto.quote;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RegisterQuoteResponse {

    private Long id;
    private String description;
    private String placement;
    private String color;
    private BigDecimal size;
    private BigDecimal price;
}
