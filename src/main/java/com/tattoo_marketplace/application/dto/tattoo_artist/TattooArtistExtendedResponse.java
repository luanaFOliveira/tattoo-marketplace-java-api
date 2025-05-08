package com.tattoo_marketplace.application.dto.tattoo_artist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.tattoo_marketplace.application.dto.category.CategoryResponse;

public record TattooArtistExtendedResponse(
        Long id,
        String email,
        String name,
        String location,
        Integer age,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Double rate,
        Set<CategoryResponse> categories,
        List<String> images,
        String profilePicture

){

}
