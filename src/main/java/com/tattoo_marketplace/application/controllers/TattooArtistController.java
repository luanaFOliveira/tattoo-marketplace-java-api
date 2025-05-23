package com.tattoo_marketplace.application.controllers;

import java.util.List;

import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.RateTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.UpdateTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistExtendedResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistFilter;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.application.services.TattooArtistService;
import com.tattoo_marketplace.infra.mappers.TattooArtistMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@Tag(name = "Tattoo Artist Controller")
@RequestMapping("/tattoo-artist")
@RestController
public class TattooArtistController {

    private final TattooArtistService tattooArtistService;
    private final TattooArtistMapper tattooArtistMapper;

    @GetMapping("/me")
    @Operation(summary = "Get authenticated tattoo artist", description = "Get details of the authenticated tattoo artist.")
    public ResponseEntity<TattooArtistResponse> authenticatedTattooArtist() {
        TattooArtistResponse tattoo_artist = tattooArtistService.getAuthenticatedTattooArtistResponse();

        return ResponseEntity.status(HttpStatus.OK).body(tattoo_artist);
    }

    @GetMapping("/{tattooArtistId}")
    @Operation(summary = "Get tattoo artist by id", description = "Get details of the tattoo artist by id.")
    public ResponseEntity<TattooArtistExtendedResponse> tattooArtistById(@PathVariable Long tattooArtistId) {
        TattooArtistExtendedResponse tattoo_artist = tattooArtistService.getDetailedTattooArtistById(tattooArtistId);

        return ResponseEntity.status(HttpStatus.OK).body(tattoo_artist);
    }

    @GetMapping("/cities")
    @Operation(summary = "Get tattoo artists avaiable cities", description = "Get avaiable cities of tattoo artists.")
    public ResponseEntity<List<String>> tattooArtistCities() {
        List<String> cities = tattooArtistService.getTattooArtistCities();

        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @GetMapping
    @Operation(summary = "Get all tattoo artists", description = "Get details of all registered tattoo artist")
    public ResponseEntity<List<TattooArtistResponse>> allTattooArtists(
            @ModelAttribute TattooArtistFilter filter
    ) {
        List<TattooArtistResponse> tattoo_artists = tattooArtistService.findAll(filter);
        return ResponseEntity.ok(tattoo_artists);
    }

    @PostMapping(value="/register", consumes = {"multipart/form-data"})
    @Operation(summary = "Create a new tattoo artist", description = "Creates a new tattoo artist")
    public ResponseEntity<RegisterTattooArtistResponse> register(@RequestPart(value = "request") @Valid RegisterTattooArtistRequest request,
                                                                    @RequestPart(value = "profile_img", required = false) MultipartFile profilePicture
    ) {
        RegisterTattooArtistResponse registeredTattooArtist = tattooArtistService.register(request, profilePicture);

        return ResponseEntity.ok(registeredTattooArtist);
    }

    @PutMapping(value="/portifolio/{tattooArtistId}", consumes = {"multipart/form-data"})
    @Operation(summary = "Add image to tattoo artist portifolio", description = "Adds images to tattoo artist portifolio by tattoo artist id")
    public ResponseEntity<TattooArtistResponse> addPortifolioImages(@PathVariable Long tattooArtistId, @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        TattooArtistResponse updatedTattooArtist = tattooArtistService.addPortifolioImages(tattooArtistId, images);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTattooArtist);
    }

    @PutMapping(value="/{tattooArtistId}", consumes = {"multipart/form-data"})
    @Operation(summary = "Edit tattoo artist", description = "Updates tattoo artist details by id")
    public ResponseEntity<TattooArtistResponse> editTattooArtist(@PathVariable Long tattooArtistId, @Valid @RequestPart UpdateTattooArtistRequest request, 
                                                                    @RequestPart(value = "profile_img", required = false) MultipartFile profilePicture
    ) {
        TattooArtistResponse updatedTattooArtist = tattooArtistService.editTattooArtist(tattooArtistId, request, profilePicture);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTattooArtist);
    }

    @PutMapping(value="/rate/{tattooArtistId}")
    @Operation(summary = "Rate tattoo artist", description = "Rates a tattoo artist by id")
    public ResponseEntity<TattooArtistResponse> rateTattooArtist(@PathVariable Long tattooArtistId, @Valid @RequestBody RateTattooArtistRequest request) {

        TattooArtistResponse updatedTattooArtist = tattooArtistService.rateTattooArtist(tattooArtistId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTattooArtist);
    }

    @DeleteMapping("/{tattooArtistId}")
    @Operation(summary = "Delete tattoo artist", description = "Deletes a tattoo artist by id.")
    public ResponseEntity<Void> deleteTattooArtist(@PathVariable Long tattooArtistId) {
        tattooArtistService.deleteTattooArtist(tattooArtistId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
