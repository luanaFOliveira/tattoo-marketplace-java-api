package com.tattoo_marketplace.infra.mappers;

import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.RegisterTattooArtistResponse;
import com.tattoo_marketplace.application.dto.tattoo_artist.UpdateTattooArtistRequest;
import com.tattoo_marketplace.application.dto.tattoo_artist.TattooArtistResponse;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TattooArtistMapper {

    TattooArtistMapper INSTANCE = Mappers.getMapper(TattooArtistMapper.class);

    @Mapping(target = "password", ignore = true)
    TattooArtist fromRegisterRequest(RegisterTattooArtistRequest request);

    void updateTattooArtistPartial(@MappingTarget TattooArtist artist, UpdateTattooArtistRequest request);

    RegisterTattooArtistResponse toRegisterResponse(TattooArtist artist);

    @Mapping(target = "categories", expression = "java(mapCategories(artist))") 
    TattooArtistResponse toResponse(TattooArtist artist);

    List<TattooArtistResponse> toResponses(List<TattooArtist> artists);

    default Set<String> mapCategories(TattooArtist artist) {
        return artist.getCategories()
                .stream()
                .map(category -> category.getName())
                .collect(Collectors.toSet());
    }
}
