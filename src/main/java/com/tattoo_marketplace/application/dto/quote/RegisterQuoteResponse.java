package com.tattoo_marketplace.application.dto.quote;

import lombok.Data;

@Data
public class RegisterQuoteResponse {

    private Long id;
    private String description;
    private String placement;
    private String color;
    private Number size;
    private Number price;
}
