package com.tattoo_marketplace.domain.repository;

import com.tattoo_marketplace.domain.entities.models.TattooArtist;
import com.tattoo_marketplace.domain.entities.models.Category;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TattooArtistRepository extends JpaRepository<TattooArtist, Long>, JpaSpecificationExecutor<TattooArtist> {

    Optional<TattooArtist> findByEmail(String email);

    Optional<TattooArtist> findById(Long id);

    Optional<TattooArtist> findByRate(Long rate);

    Optional<TattooArtist> findByLocation(String location);

    List<TattooArtist> findByCategoriesContaining(Category category);

}
