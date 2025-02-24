package com.tattoo_marketplace.infra.mappers;

import com.tattoo_marketplace.application.dto.category.CategoryRequest;
import com.tattoo_marketplace.application.dto.category.CategoryResponse;
import com.tattoo_marketplace.domain.entities.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponses(List<Category> categoryList);

    void updateCategory(@MappingTarget Category category, CategoryRequest request);
}
