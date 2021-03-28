package com.github.ruifcsantosdev.libraryapi.services.impl;

import com.github.ruifcsantosdev.libraryapi.exception.BadRequestException;
import com.github.ruifcsantosdev.libraryapi.exception.ResourceNotFoundException;
import com.github.ruifcsantosdev.libraryapi.models.Category;
import com.github.ruifcsantosdev.libraryapi.payload.input.CategoryCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.CategoryUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;
import com.github.ruifcsantosdev.libraryapi.repositories.CategoryRepository;
import com.github.ruifcsantosdev.libraryapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PagedResponse<Category> getAllCategories(int page, int size) {
        validatePagination(page, size);

        Pageable pageable = PageRequest.of(page, size);

        Page<Category> categories = this.categoryRepository.findAll(pageable);

        List<Category> content = categories.getNumberOfElements() == 0 ? Collections.emptyList() : categories.getContent();

        PagedResponse<Category> pagedResponse = new PagedResponse<Category>(
                content,
                categories.getNumber(),
                categories.getSize(),
                categories.getNumberOfElements(),
                categories.getTotalPages(),
                categories.isLast()
        );

        return pagedResponse;
    }

    @Override
    public Category getCategory(int categoryId) {
        return this.categoryRepository
                .findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category doesn't exists!", "Id", categoryId)
                );
    }

    @Override
    public Category addCategory(CategoryCreateRequest categoryCreateRequest) {
        Optional<Category> existsCategory = this.categoryRepository.findByName(categoryCreateRequest.getName());

        if(!(existsCategory.isPresent())) {
            Category category = new Category();
            category.setName(categoryCreateRequest.getName());
            category.setDescription(categoryCreateRequest.getDescription());
            category.setBooks(new ArrayList<>());

            Category newCategory = this.categoryRepository.save(category);
            return newCategory;
        }else {
            // TODO
            return null;
        }

    }

    @Override
    public void updateCategory(int categoryId, CategoryUpdateRequest categoryUpdateRequest) {
        Optional<Category> category = this.categoryRepository.findById(categoryId);

        if(category.isPresent()) {

        }else {
            // TODO
        }

    }

    @Override
    public void deleteCategory(int categoryId) {
        Optional<Category> category = this.categoryRepository.findById(categoryId);

        if(category.isPresent()) {
            this.categoryRepository.delete(category.get());
        }else {
            // TODO
        }
    }

    private void validatePagination(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("");
        }
        if (size < 0) {
            throw new BadRequestException("");
        }
    }
}
