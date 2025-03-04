package com.tattoo_marketplace.application.services;

import com.tattoo_marketplace.application.dto.category.CategoryRequest;
import com.tattoo_marketplace.application.dto.category.CategoryResponse;
import com.tattoo_marketplace.domain.entities.models.Category;
import java.util.List;

public interface CategoryService {

    Category getById(Long id);

    Category getByName(String categoryName);

    List<CategoryResponse> findAll();

    CategoryResponse register(CategoryRequest request);

}
