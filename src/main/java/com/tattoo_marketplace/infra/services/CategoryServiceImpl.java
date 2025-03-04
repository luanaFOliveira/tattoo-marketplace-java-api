package com.tattoo_marketplace.infra.services;

import com.tattoo_marketplace.application.dto.category.CategoryRequest;
import com.tattoo_marketplace.application.dto.category.CategoryResponse;
import com.tattoo_marketplace.application.services.CategoryService;
import com.tattoo_marketplace.domain.entities.models.Category;
import com.tattoo_marketplace.domain.repository.CategoryRepository;
import com.tattoo_marketplace.infra.mappers.CategoryMapper;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAll() {
        return categoryMapper.toResponses(categoryRepository.findAll());
    }

    @Override
    public Category getById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find category for id=%s", categoryId)));
    }

    @Override
    public Category getByName(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category for name=%s" + categoryName));
    }

    @Override
    public CategoryResponse register(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);

        return categoryMapper.toResponse(categoryRepository.save(category));
    }
}
