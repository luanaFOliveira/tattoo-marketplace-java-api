package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.UpdateTattooArtistRequest;
import com.tattoo_marketplace.application.dto.user.RegisterUserRequest;
import com.tattoo_marketplace.application.dto.user.RegisterUserResponse;
import com.tattoo_marketplace.application.dto.user.UserResponse;
import com.tattoo_marketplace.application.dto.user.UpdateUserRequest;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.domain.entities.models.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface TattooArtistService {

    TattooArtistResponse getAuthenticatedTattooArtistResponse();

    List<TattooArtistResponse> findAll();

    RegisterTattooArtistResponse register(RegisterTattooArtistRequest request);

    TattooArtist getTattooArtistById(Long tattooArtistId);

    TattooArtistResponse editTattooArtist(Long tattooArtistId, UpdateTattooArtistRequest request);

    void deleteTattooArtist(Long tattooArtistId);
}
