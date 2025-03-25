package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.domain.entities.models.Quote;
import com.tattoo_marketplace.domain.entities.models.QuoteImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuoteImageService {

    List<QuoteImage> findAllByQuoteId(Long quoteId);

    void uploadImages(List<MultipartFile> images, Quote quote);

    void deleteImage(Long imageId);

    void deleteAllByQuoteId(Long quoteId);

    List<byte[]> findAllImageBytesByQuoteId(Long quoteId);
}
