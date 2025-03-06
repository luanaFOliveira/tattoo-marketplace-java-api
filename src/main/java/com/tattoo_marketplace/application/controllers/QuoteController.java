package com.tattoo_marketplace.application.controllers;

import java.util.List;

import com.tattoo_marketplace.application.dto.quote.RegisterQuoteRequest;
import com.tattoo_marketplace.application.dto.quote.RegisterQuoteResponse;
import com.tattoo_marketplace.application.dto.quote.UpdateQuoteRequest;
import com.tattoo_marketplace.application.dto.quote.QuoteResponse;
import com.tattoo_marketplace.domain.entities.models.Quote;
import com.tattoo_marketplace.application.services.QuoteService;
import com.tattoo_marketplace.infra.mappers.QuoteMapper;

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

@RequiredArgsConstructor
@Tag(name = "Quote Controller")
@RequestMapping("/quote")
@RestController
public class QuoteController {

    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;

    @GetMapping("/{quoteId}")
    @Operation(summary = "Get quote by id", description = "Get details of the quote by id.")
    public ResponseEntity<QuoteResponse> quoteById(@PathVariable Long quoteId) {
        Quote quote = quoteService.getById(quoteId);

        return ResponseEntity.status(HttpStatus.OK).body(quoteMapper.toResponse(quote));
    }

    @GetMapping
    @Operation(summary = "Get all quotes", description = "Get details of all registered quotes")
    public ResponseEntity<List<QuoteResponse>> allQuotes() {
        List<QuoteResponse> quote = quoteService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(quote);
    }

    @PostMapping("/register")
    @Operation(summary = "Create a new quote", description = "Creates a new quote")
    public ResponseEntity<RegisterQuoteResponse> register(@Valid @RequestBody RegisterQuoteRequest request) {

        RegisterQuoteResponse registeredQuote = quoteService.register(request);

        return ResponseEntity.ok(registeredQuote);
    }

    @PutMapping("/{quoteId}")
    @Operation(summary = "Edit quote", description = "Updates quote details by id")
    public ResponseEntity<QuoteResponse> editQuote(@PathVariable Long quoteId, @Valid @RequestBody UpdateQuoteRequest request) {
        QuoteResponse updatedQuote = quoteService.editQuote(quoteId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuote);
    }

    @DeleteMapping("/{quoteId}")
    @Operation(summary = "Delete quote", description = "Deletes a quote by id.")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long quoteId) {
        quoteService.deleteQuote(quoteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
