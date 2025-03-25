package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.application.dto.quote.QuoteExtendedResponse;
import com.tattoo_marketplace.application.dto.quote.QuoteResponse;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteRequest;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteResponse;
import com.tattoo_marketplace.application.dto.quote.UpdateQuoteRequest;
import com.tattoo_marketplace.domain.entities.models.Quote;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface QuoteService {

    List<QuoteResponse> findAll();

    List<QuoteResponse> findAllByUserId(Long userId);

    List<QuoteResponse> findAllByTattooArtistId(Long tattooArtistId);

    RegisterQuoteResponse register(RegisterQuoteRequest request, List<MultipartFile> images);

    QuoteExtendedResponse getQuoteById(Long quoteId);

    QuoteResponse editQuote(Long quoteId, UpdateQuoteRequest request);

    void deleteQuote(Long quoteId);
}
