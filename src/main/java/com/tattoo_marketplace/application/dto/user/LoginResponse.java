package com.tattoo_marketplace.application.dto.user;

public record LoginResponse(Long id, String token, Long expiresIn, boolean isTattooArtist, String profilePicture) {
}
