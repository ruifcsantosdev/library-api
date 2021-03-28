package com.github.ruifcsantosdev.libraryapi.controllers;

import com.github.ruifcsantosdev.libraryapi.models.author.Author;
import com.github.ruifcsantosdev.libraryapi.payload.input.AuthorCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;
import com.github.ruifcsantosdev.libraryapi.services.AuthorService;
import com.github.ruifcsantosdev.libraryapi.utils.DefaultParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Author>> getAllAuthors(
            @RequestParam(value = "page", required = false, defaultValue = DefaultParams.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DefaultParams.DEFAULT_SIZE) Integer size
    ) {

        PagedResponse<Author> authors = this.authorService.getAllAuthors(page, size);

        ResponseEntity<PagedResponse<Author>> response = new ResponseEntity<>(
              authors,
              HttpStatus.OK
        );

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Author author = this.authorService.getAuthor(id);

        ResponseEntity<Author> response = new ResponseEntity<>(
                author,
                HttpStatus.OK
        );

        return response;
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(
            @RequestBody AuthorCreateRequest authorCreateRequest
    ) {

        Author newAuthor = this.authorService.addAuthor(authorCreateRequest);

        ResponseEntity<Author> response = new ResponseEntity<>(
                newAuthor,
                HttpStatus.CREATED
        );

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable Integer id) {
        this.authorService.deleteAuthor(id);

        ResponseEntity response = new ResponseEntity(
                HttpStatus.ACCEPTED
        );

        return response;
    }
}
