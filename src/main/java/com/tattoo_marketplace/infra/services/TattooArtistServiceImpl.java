package com.tattoo_marketplace.infra.services;

import com.tattoo_marketplace.application.dto.user.PasswordValidation;
import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistExtendedResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.UpdateTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistResponse;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.domain.entities.models.TattooArtistImage;
import com.tattoo_marketplace.domain.entities.models.Category;
import com.tattoo_marketplace.domain.repository.CategoryRepository;
import com.tattoo_marketplace.domain.repository.TattooArtistRepository;
import com.tattoo_marketplace.infra.mappers.TattooArtistMapper;
import com.tattoo_marketplace.application.services.TattooArtistService;
import com.tattoo_marketplace.application.services.TattooArtistImageService;

import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TattooArtistServiceImpl implements TattooArtistService {

    private final TattooArtistRepository tattooArtistRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final TattooArtistMapper tattooArtistMapper;
    private final TattooArtistImageService tattooArtistImageService;

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationCredentialsNotFoundException("No authenticated user found");
        }
        return authentication;
    }

    @Override
    public TattooArtistResponse getAuthenticatedTattooArtistResponse() {
        Authentication authentication = getAuthentication();
        TattooArtist tattooArtist = (TattooArtist) authentication.getPrincipal();
        return tattooArtistMapper.toResponse(tattooArtist);
    }

    private void assignPassword(TattooArtist tattoo_artist, String password) {
        final var encodedPassword = passwordEncoder.encode(password);
        tattoo_artist.setPassword(encodedPassword);
    }

    private void assertPasswordsMatch(PasswordValidation request) {
        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
    }

    @Override
    public RegisterTattooArtistResponse register(RegisterTattooArtistRequest request, List<MultipartFile> images) {
        assertPasswordsMatch(request);

        TattooArtist tattooArtist = tattooArtistMapper.fromRegisterRequest(request);

        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        tattooArtist.setCategories(categories);

        assignPassword(tattooArtist, request.getPassword());

        TattooArtist savedTattooArtist = tattooArtistRepository.save(tattooArtist);
        tattooArtistImageService.uploadImages(images, savedTattooArtist); 
        
        return tattooArtistMapper.toRegisterResponse(savedTattooArtist);
    }

    @Override
    public TattooArtist getTattooArtistById(Long tattooArtistId) {
        return tattooArtistRepository.findById(tattooArtistId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find tattoo artist for id=%s", tattooArtistId)));
    }

    @Override
    public TattooArtistExtendedResponse getDetailedTattooArtistById(Long tattooArtistId) {
        TattooArtist tattooArtist = getTattooArtistById(tattooArtistId);
        return mapToExtendedResponse(tattooArtist);
    }

    private TattooArtistExtendedResponse mapToExtendedResponse(TattooArtist tattooArtist) {
        final var images = tattooArtistImageService.findAllImageBytesByTattooArtistId(tattooArtist.getId());
        // final var images = tattooArtistImageService.findAllByTattooArtistId(tattooArtist.getId())
        //         .stream()
        //         .map(TattooArtistImage::getUrl).toList();
        return tattooArtistMapper.toExtendedResponse(tattooArtist, images);
    }

    @Override
    public TattooArtistResponse editTattooArtist(Long tattooArtistId, UpdateTattooArtistRequest request) {
        TattooArtist tattooArtist = tattooArtistRepository.findById(tattooArtistId)
            .orElseThrow(() -> new RuntimeException("Tattoo artist not found"));

        tattooArtistMapper.updateTattooArtistPartial(tattooArtist, request);

        if (request.getPassword() != null && request.getPasswordConfirm() != null) {
            assertPasswordsMatch(request);
            assignPassword(tattooArtist, request.getPassword());
        }

        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

            tattooArtist.setCategories(categories);
        }

        TattooArtist updatedTattooArtist = tattooArtistRepository.save(tattooArtist);

        return tattooArtistMapper.toResponse(updatedTattooArtist);
    }


    @Override
    public void deleteTattooArtist(Long tattooArtistId) {
        if (!tattooArtistRepository.existsById(tattooArtistId)) {
            throw new RuntimeException("Tattoo artist not found");
        }

        tattooArtistRepository.deleteById(tattooArtistId);
    }

    @Override
    public List<TattooArtistResponse> findAll() {
        return tattooArtistMapper.toResponses(tattooArtistRepository.findAll());
    }

}
