package com.tattoo_marketplace.infra.services;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.tattoo_marketplace.application.services.TattooArtistImageService;
import com.tattoo_marketplace.domain.entities.models.TattooArtistImage;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.domain.repository.TattooArtistImageRepository;
import com.tattoo_marketplace.domain.repository.TattooArtistRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class TattooArtistImageServiceImpl implements TattooArtistImageService {

    private final TattooArtistImageRepository tattooArtistImageRepository;
    private final TattooArtistRepository tattooArtistRepository;
    private final String uploadDir = "/app/images/";

    private String saveImage(MultipartFile image) throws IOException {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);

        image.transferTo(path.toFile());
        return "/images/" + fileName;
    }

    private void saveImageToDatabase(String imageUrl, TattooArtist tattooArtist) {

        TattooArtistImage img = new TattooArtistImage();
        img.setUrl(imageUrl);
        img.setTattooArtist(tattooArtist);

        tattooArtistImageRepository.save(img);
    }

    @Override
    public void uploadImages(List<MultipartFile> images, TattooArtist tattooArtist) {
        images.forEach(image -> {
            try {
                String imageUrl = saveImage(image);
                saveImageToDatabase(imageUrl, tattooArtist);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar imagem: " + image.getOriginalFilename(), e);
            }
        });
    }

    @Override
    public List<TattooArtistImage> findAllByTattooArtistId(Long tattooArtistId){
        return tattooArtistImageRepository.findAllByTattooArtistId(tattooArtistId);
    }

    @Override
    public void deleteImage(Long imageId) {
        TattooArtistImage image = tattooArtistImageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Imagem não encontrada com ID: " + imageId));

        Path imagePath = Paths.get(uploadDir + new File(image.getUrl()).getName());

        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao excluir a imagem do sistema de arquivos.", e);
        }

        tattooArtistImageRepository.deleteById(imageId);
    }

    @Override
    public void deleteAllByTattooArtistId(Long tattooArtistId) {
        List<TattooArtistImage> images = tattooArtistImageRepository.findAllByTattooArtistId(tattooArtistId);

        for (TattooArtistImage image : images) {
            Path imagePath = Paths.get(uploadDir + new File(image.getUrl()).getName());

            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao excluir a imagem do sistema de arquivos: " + image.getUrl(), e);
            }

            tattooArtistImageRepository.delete(image);
        }
    }

}
