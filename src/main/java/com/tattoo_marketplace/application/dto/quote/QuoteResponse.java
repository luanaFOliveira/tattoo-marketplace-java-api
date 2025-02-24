package com.tattoo_marketplace.application.dto.quote;

import com.tattoo_marketplace.application.dto.status.StatusResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistResponse;
import com.tattoo_marketplace.application.dto.user.UserResponse;

public record QuoteResponse {

    Long id,

    String description,

    String placement,

    String color,

    Number size,

    Number price,

    UserResponse user,

    TattooArtistResponse tattooArtist,

    StatusResponse status

}
