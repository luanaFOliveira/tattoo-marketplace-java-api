package com.tattoo_marketplace.application.controllers;

import com.tattoo_marketplace.application.dto.category.CategoryRequest;
import com.tattoo_marketplace.application.dto.category.CategoryResponse;
import com.tattoo_marketplace.application.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Tag(name = "Category Controller")
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Get all categories.")
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        List<CategoryResponse> categories = categoryService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping("/register")
    @Operation(summary = "Create a new category", description = "Creates a new category")
    public ResponseEntity<CategoryResponse> register(@Valid @RequestBody CategoryRequest request) {

        CategoryResponse registeredCategory = categoryService.register(request);

        return ResponseEntity.ok(registeredCategory);
    }

}
