package com.github.ruifcsantosdev.libraryapi.payload.input;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class BookCreateRequest {

    private String title;

    private String resume;

    private String isbn;

    private int numberOfPages;

    private int authorId;

    private List<Integer> categories;

    public List<Integer> getCategories() {

        return categories == null ? Collections.emptyList() : new ArrayList<>(categories);
    }

    public void setCategories(List<Integer> categories) {

        if (categories == null) {
            this.categories = null;
        } else {
            this.categories = Collections.unmodifiableList(categories);
        }
    }
}
