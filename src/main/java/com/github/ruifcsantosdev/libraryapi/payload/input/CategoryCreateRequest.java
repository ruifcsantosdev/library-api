package com.github.ruifcsantosdev.libraryapi.payload.input;

import lombok.Data;

@Data
public class CategoryCreateRequest {
    private String name;
    private String description;
}
