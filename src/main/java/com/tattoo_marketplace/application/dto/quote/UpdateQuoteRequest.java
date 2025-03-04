package com.tattoo_marketplace.application.dto.quote;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateQuoteRequest {

    private String description;
    private String placement;
    private String color;
    private BigDecimal size;
    private BigDecimal price;
    private Long statusId;
}
