package com.tattoo_marketplace.domain.repository;

import com.tattoo_marketplace.domain.entities.models.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
}
