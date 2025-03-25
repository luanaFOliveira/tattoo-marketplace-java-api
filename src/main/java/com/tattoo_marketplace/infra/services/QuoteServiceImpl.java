package com.tattoo_marketplace.infra.services;

import com.tattoo_marketplace.application.dto.quote.QuoteExtendedResponse;
import com.tattoo_marketplace.application.dto.quote.QuoteResponse;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteRequest;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteResponse;
import com.tattoo_marketplace.application.dto.quote.UpdateQuoteRequest;
import com.tattoo_marketplace.domain.entities.models.Quote;
import com.tattoo_marketplace.domain.entities.models.QuoteImage;
import com.tattoo_marketplace.domain.repository.QuoteRepository;
import com.tattoo_marketplace.infra.mappers.QuoteMapper;
import com.tattoo_marketplace.infra.mappers.TattooArtistMapper;
import com.tattoo_marketplace.application.services.QuoteImageService;
import com.tattoo_marketplace.application.services.QuoteService;
import com.tattoo_marketplace.application.services.StatusService;
import com.tattoo_marketplace.application.services.TattooArtistService;
import com.tattoo_marketplace.application.services.UserService;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserService userService;
    private final TattooArtistService tattooArtistService;
    private final StatusService statusService;
    private final QuoteMapper quoteMapper;
    private final QuoteImageService quoteImageService;

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
    public RegisterQuoteResponse register(RegisterQuoteRequest request, List<MultipartFile> images) {

        Quote quote = quoteMapper.fromRegisterRequest(request);
        
        assignUser(quote, request.getUserId());

        assignTattooArtist(quote, request.getTattooArtistId());

        Long statusId = statusService.getByName("PENDING").getId();
        assignStatus(quote, statusId);

        Quote savedQuote = quoteRepository.save(quote);

        quoteImageService.uploadImages(images, savedQuote); 

        return quoteMapper.toRegisterResponse(savedQuote);
    }


    @Override
    public QuoteExtendedResponse getQuoteById(Long quoteId) {
        Quote quote = getQuote(quoteId);
        return mapToExtendedResponse(quote);
    }

    private QuoteExtendedResponse mapToExtendedResponse(Quote quote) {
        final var images = quoteImageService.findAllImageBytesByQuoteId(quote.getId());
        // final var images = quoteImageService.findAllByQuoteId(quote.getId())
        //         .stream()
        //         .map(QuoteImage::getUrl).toList();
        return quoteMapper.toExtendedResponse(quote, images);
    }

    @Override
    public QuoteResponse editQuote(Long quoteId, UpdateQuoteRequest request) {
        Quote quote = getQuote(quoteId);

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

    public Quote getQuote(Long quoteId) {
        return quoteRepository.findById(quoteId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find quote for id=%s", quoteId)));
    }
}
