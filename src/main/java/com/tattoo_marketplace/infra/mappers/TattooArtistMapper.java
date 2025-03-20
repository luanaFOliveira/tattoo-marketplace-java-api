package com.tattoo_marketplace.infra.mappers;

import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistExtendedResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.UpdateTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistResponse;
import com.tattoo_marketplace.domain.entities.models.Category;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TattooArtistMapper {

    @Mapping(target = "password", ignore = true)
    TattooArtist fromRegisterRequest(RegisterTattooArtistRequest request);

    void updateTattooArtistPartial(@MappingTarget TattooArtist artist, UpdateTattooArtistRequest request);

    RegisterTattooArtistResponse toRegisterResponse(TattooArtist artist);

    @Mapping(target = "categories", source = "categories") 
    TattooArtistResponse toResponse(TattooArtist artist);

    TattooArtistExtendedResponse toExtendedResponse(TattooArtist artist, List<String> images);

    List<TattooArtistResponse> toResponses(List<TattooArtist> artists);

}
