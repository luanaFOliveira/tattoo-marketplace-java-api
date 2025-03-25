package com.tattoo_marketplace.infra.mappers;

import com.tattoo_marketplace.application.dto.quote.*;
import com.tattoo_marketplace.domain.entities.models.Quote;
import com.tattoo_marketplace.domain.entities.models.Status;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.domain.entities.models.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuoteMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "tattooArtist.id", source = "tattooArtistId")
    Quote fromRegisterRequest(RegisterQuoteRequest request);

    void updateQuotePartial(@MappingTarget Quote quote, UpdateQuoteRequest request);

    RegisterQuoteResponse toRegisterResponse(Quote quote);

    QuoteResponse toResponse(Quote quote);

    QuoteExtendedResponse toExtendedResponse(Quote quote, List<String> images);

    List<QuoteResponse> toResponses(List<Quote> quotes);

}
