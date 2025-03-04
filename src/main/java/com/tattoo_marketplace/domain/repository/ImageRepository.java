package com.tattoo_marketplace.domain.repository;

import com.tattoo_marketplace.domain.entities.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByEntityId(Long entityId);

    List<Image> findAllByEntityType(String entityType);

    //List<Image> findByEntityIdAndEntityType(Long entityId, String entityType);

}
