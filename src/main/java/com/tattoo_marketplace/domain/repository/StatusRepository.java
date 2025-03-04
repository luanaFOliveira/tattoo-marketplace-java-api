package com.tattoo_marketplace.domain.repository;

import com.tattoo_marketplace.domain.entities.models.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findById(Long id);

    Optional<Status> findByName(String statusName);

}
