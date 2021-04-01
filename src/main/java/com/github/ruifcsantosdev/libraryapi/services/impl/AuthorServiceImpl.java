package com.github.ruifcsantosdev.libraryapi.services.impl;

import com.github.ruifcsantosdev.libraryapi.exception.BadRequestException;
import com.github.ruifcsantosdev.libraryapi.exception.ResourceNotFoundException;
import com.github.ruifcsantosdev.libraryapi.models.author.Author;
import com.github.ruifcsantosdev.libraryapi.payload.input.AuthorCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.AuthorUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;
import com.github.ruifcsantosdev.libraryapi.repositories.AuthorRepository;
import com.github.ruifcsantosdev.libraryapi.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public PagedResponse<Author> getAllAuthors(int page, int size) {
        validatePagination(page, size);

        Pageable pageable = PageRequest.of(page, size);

        Page<Author> authors = this.authorRepository.findAll(pageable);

        List<Author> content = authors.getNumberOfElements() == 0 ? Collections.emptyList() : authors.getContent();

        PagedResponse response = new PagedResponse<>(
              content,
              authors.getNumber(),
              authors.getSize(),
              authors.getTotalElements(),
              authors.getTotalPages(),
              authors.isLast()
        );

        return response;
    }

    @Override
    public Author getAuthor(int authorId) {
        return this.authorRepository
                .findById(authorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Author doesn't exist", "Id", authorId)
                );
    }

    // TODO
    @Override
    public Author addAuthor(AuthorCreateRequest authorCreateRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Author author = modelMapper.map(authorCreateRequest, Author.class);

        Author newAuthor = this.authorRepository.save(author);
        return newAuthor;
    }

    // TODO
    @Override
    public void updateAuthor(int authorId, AuthorUpdateRequest authorUpdateRequest) {

    }

    @Override
    public void deleteAuthor(int authorId) {
        Author author = this.authorRepository
                .findById(authorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Author doesn't exist", "Id", authorId)
                );

        this.authorRepository.delete(author);
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
