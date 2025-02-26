package com.tattoo_marketplace.domain.repository;

import com.tattoo_marketplace.domain.entities.models.Quote;
import com.tattoo_marketplace.domain.entities.models.Status;
import com.tattoo_marketplace.domain.entities.models.User;
import com.tattoo_marketplace.domain.entities.models.TattooArtist;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Optional<Quote> findById(Long id);

    List<Quote> findByUser(User user);

    List<Quote> findByTattooArtist(TattooArtist tattooArtist);

    List<Status> findByStatus(Status status);

}
