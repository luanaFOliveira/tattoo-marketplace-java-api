package com.tattoo_marketplace.domain.repository;

import com.tattoo_marketplace.domain.entities.models.TattooArtistImage;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TattooArtistImageRepository extends JpaRepository<TattooArtistImage, Long> {
    List<TattooArtistImage> findAllByTattooArtistId(Long tattooArtistId);
}
