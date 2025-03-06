package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.application.dto.quote.QuoteResponse;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteRequest;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteResponse;
import com.tattoo_marketplace.application.dto.quote.UpdateQuoteRequest;
import com.tattoo_marketplace.domain.entities.models.Quote;
import java.util.List;

public interface QuoteService {

    List<QuoteResponse> findAll();

    RegisterQuoteResponse register(RegisterQuoteRequest request);

    Quote getById(Long quoteId);

    QuoteResponse editQuote(Long quoteId, UpdateQuoteRequest request);

    void deleteQuote(Long quoteId);
}
