package com.tattoo_marketplace.application.dto.quote;

import lombok.Data;

@Data
public class UpdateQuoteRequest {

    private String description;
    private String placement;
    private String color;
    private Number size;
    private Number price;
    private Long statusId;
}
