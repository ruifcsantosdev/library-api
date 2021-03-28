package com.github.ruifcsantosdev.libraryapi.services;

import com.github.ruifcsantosdev.libraryapi.models.Category;
import com.github.ruifcsantosdev.libraryapi.payload.input.CategoryCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.CategoryUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;

public interface CategoryService {
    PagedResponse<Category> getAllCategories(int page, int size);

    Category getCategory(int categoryId);

    Category addCategory(CategoryCreateRequest categoryCreateRequest);

    void updateCategory(int categoryId, CategoryUpdateRequest categoryUpdateRequest);

    void deleteCategory(int categoryId);
}
