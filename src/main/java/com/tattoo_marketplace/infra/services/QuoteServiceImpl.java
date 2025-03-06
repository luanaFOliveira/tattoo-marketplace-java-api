package com.tattoo_marketplace.infra.services;

import com.tattoo_marketplace.application.dto.quote.QuoteResponse;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteRequest;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteResponse;
import com.tattoo_marketplace.application.dto.quote.UpdateQuoteRequest;
import com.tattoo_marketplace.domain.entities.models.Quote;
import com.tattoo_marketplace.domain.repository.QuoteRepository;
import com.tattoo_marketplace.infra.mappers.QuoteMapper;
import com.tattoo_marketplace.infra.mappers.TattooArtistMapper;
import com.tattoo_marketplace.application.services.QuoteService;
import com.tattoo_marketplace.application.services.StatusService;
import com.tattoo_marketplace.application.services.TattooArtistService;
import com.tattoo_marketplace.application.services.UserService;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserService userService;
    private final TattooArtistService tattooArtistService;
    private final StatusService statusService;
    private final QuoteMapper quoteMapper;

    private void assignUser(Quote quote, Long userId) {
        quote.setUser(userService.getUserById(userId));
    }

    private void assignTattooArtist(Quote quote, Long tattooArtistId) {
        quote.setTattooArtist(tattooArtistService.getTattooArtistById(tattooArtistId));
    }

    private void assignStatus(Quote quote, Long statusId) {
        quote.setStatus(statusService.getById(statusId));
    }


    @Override
    public RegisterQuoteResponse register(RegisterQuoteRequest request) {

        Quote quote = quoteMapper.fromRegisterRequest(request);
        
        assignUser(quote, request.getUserId());

        assignTattooArtist(quote, request.getTattooArtistId());
        //TODO: mudar para nao precisar vir status id, todo quote criado comeca como pendente
        //TODO: talvez seja melhor criar os enums e deixar para que o user possa criar outros tambem mas ja ter uns default
        assignStatus(quote, request.getStatusId());

        Quote savedQuote = quoteRepository.save(quote);

        return quoteMapper.toRegisterResponse(savedQuote);
    }


    @Override
    public Quote getById(Long quoteId) {
        return quoteRepository.findById(quoteId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find quote for id=%s", quoteId)));
    }

    @Override
    public QuoteResponse editQuote(Long quoteId, UpdateQuoteRequest request) {
        Quote quote =quoteRepository.findById(quoteId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find quote for id=%s", quoteId)));

        quoteMapper.updateQuotePartial(quote, request);

        if (request.getStatusId() != null) {
            assignStatus(quote, request.getStatusId());
        }

        Quote updatedQuote = quoteRepository.save(quote);

        return quoteMapper.toResponse(updatedQuote);
    }


    @Override
    public void deleteQuote(Long quoteId) {
        if (!quoteRepository.existsById(quoteId)) {
            throw new RuntimeException("Quote not found");
        }

        quoteRepository.deleteById(quoteId);
    }

    @Override
    public List<QuoteResponse> findAll() {
        return quoteMapper.toResponses(quoteRepository.findAll());
    }

}
