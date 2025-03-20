package com.tattoo_marketplace.application.dto.tattoo_artist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.tattoo_marketplace.application.dto.category.CategoryResponse;

public record TattooArtistResponse(
        Long id,
        String email,
        String name,
        String location,
        Integer age,
        String profilePicturePath,
        Integer rate,
        Set<CategoryResponse> categories
){

}
