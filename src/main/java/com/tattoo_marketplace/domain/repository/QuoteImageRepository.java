package com.tattoo_marketplace.domain.repository;

import com.tattoo_marketplace.domain.entities.models.QuoteImage;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface QuoteImageRepository extends JpaRepository<QuoteImage, Long> {
    List<QuoteImage> findAllByQuoteId(Long quoteId);
}
