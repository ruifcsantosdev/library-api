package com.github.ruifcsantosdev.libraryapi.services;

import com.github.ruifcsantosdev.libraryapi.models.author.Author;
import com.github.ruifcsantosdev.libraryapi.payload.input.AuthorCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.AuthorUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;

public interface AuthorService {
    PagedResponse<Author> getAllAuthors(int page, int size);

    Author getAuthor(int authorId);

    Author addAuthor(AuthorCreateRequest authorCreateRequest);

    void updateAuthor(int authorId, AuthorUpdateRequest authorUpdateRequest);

    void deleteAuthor(int authorId);
}
