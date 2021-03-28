package com.github.ruifcsantosdev.libraryapi.controllers;

import com.github.ruifcsantosdev.libraryapi.models.Category;
import com.github.ruifcsantosdev.libraryapi.payload.input.CategoryCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;
import com.github.ruifcsantosdev.libraryapi.services.CategoryService;
import com.github.ruifcsantosdev.libraryapi.utils.DefaultParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Category>> getAllCategories(
            @RequestParam(value = "page", required = false, defaultValue = DefaultParams.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DefaultParams.DEFAULT_SIZE) Integer size
    ) {

        PagedResponse<Category> categories = this.categoryService.getAllCategories(page, size);

        ResponseEntity<PagedResponse<Category>> response = new ResponseEntity<>(
            categories,
            HttpStatus.OK
        );

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Integer id
    ) {

        Category category = this.categoryService.getCategory(id);

        ResponseEntity<Category> response = new ResponseEntity<>(
                category,
                HttpStatus.OK
        );

        return response;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(
            @RequestBody CategoryCreateRequest categoryCreateRequest
    ) {
        Category newCategory = this.categoryService.addCategory(categoryCreateRequest);

        ResponseEntity<Category> response = new ResponseEntity<>(
                newCategory,
                HttpStatus.CREATED
        );

        return response;
    }
}
