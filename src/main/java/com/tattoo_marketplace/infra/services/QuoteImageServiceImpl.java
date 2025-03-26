package com.tattoo_marketplace.infra.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.tattoo_marketplace.application.services.QuoteService;
import com.tattoo_marketplace.application.services.QuoteImageService;
import com.tattoo_marketplace.application.services.ImageService;
import com.tattoo_marketplace.domain.entities.models.Quote;
import com.tattoo_marketplace.domain.entities.models.QuoteImage;
import com.tattoo_marketplace.domain.repository.QuoteImageRepository;
import com.tattoo_marketplace.domain.repository.QuoteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;

import jakarta.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class QuoteImageServiceImpl implements QuoteImageService {

    private final QuoteImageRepository quoteImageRepository;
    private final QuoteRepository quoteRepository;
    private final String uploadDir = "/app/images/";
    private final ImageService imageService;


    private void saveImageToDatabase(String imageUrl, Quote quote) {

        QuoteImage img = new QuoteImage();
        img.setUrl(imageUrl);
        img.setQuote(quote);

        quoteImageRepository.save(img);
    }

    @Override
    public void uploadImages(List<MultipartFile> images, Quote quote) {
        images.forEach(image -> {
            try {
                String imageUrl = imageService.saveImage(image);
                saveImageToDatabase(imageUrl, quote);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image", e);
            }
        });
    }

    @Override
    public List<QuoteImage> findAllByQuoteId(Long quoteId){
        return quoteImageRepository.findAllByQuoteId(quoteId);
    }

    @Override
    public void deleteImage(Long imageId) {
        QuoteImage image = quoteImageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Imagem não encontrada com ID: " + imageId));

        Path imagePath = Paths.get(uploadDir + new File(image.getUrl()).getName());

        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao excluir a imagem do sistema de arquivos.", e);
        }

        quoteImageRepository.deleteById(imageId);
    }

    @Override
    public void deleteAllByQuoteId(Long quoteId) {
        List<QuoteImage> images = quoteImageRepository.findAllByQuoteId(quoteId);

        for (QuoteImage image : images) {
            Path imagePath = Paths.get(uploadDir + new File(image.getUrl()).getName());

            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao excluir a imagem do sistema de arquivos: " + image.getUrl(), e);
            }

            quoteImageRepository.delete(image);
        }
    }

    private QuoteImage getImageById(Long id) {
        return quoteImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imagem não encontrada com ID: " + id));
    }

    @Override
    public List<byte[]> findAllImageBytesByQuoteId(Long quoteId) {
        List<QuoteImage> images = quoteImageRepository.findAllByQuoteId(quoteId);

        return images.stream()
                    .map(image -> getImageBytes(image.getId())) 
                    .toList();
    }


    private byte[] getImageBytes(Long id) {
        try {
            QuoteImage image = getImageById(id);
            File file = new File(uploadDir + new File(image.getUrl()).getName());

            if (!file.exists()) {
                throw new RuntimeException("Imagem não encontrada no sistema de arquivos.");
            }

            return Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler a imagem do sistema de arquivos.", e);
        }
    }

}
