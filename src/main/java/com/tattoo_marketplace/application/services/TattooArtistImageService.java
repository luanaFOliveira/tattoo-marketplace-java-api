package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.domain.entities.models.TattooArtistImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TattooArtistImageService {

    List<TattooArtistImage> findAllByTattooArtistId(Long tattooArtistId);

    void uploadImages(List<MultipartFile> images, TattooArtist tattooArtist);

    void deleteImage(Long imageId);

    void deleteAllByTattooArtistId(Long tattooArtistId);

    List<byte[]> findAllImageBytesByTattooArtistId(Long tattooArtistId);
}
