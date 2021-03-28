package com.github.ruifcsantosdev.libraryapi.controllers;

import com.github.ruifcsantosdev.libraryapi.models.Book;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;
import com.github.ruifcsantosdev.libraryapi.services.BookService;
import com.github.ruifcsantosdev.libraryapi.utils.DefaultParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Book>> getBooks(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ){
        PagedResponse<Book> books = this.bookService.getAllBooks(page, size);
        return new ResponseEntity< >(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book book = this.bookService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<PagedResponse<Book>> getBooksByAuthor(
            @RequestParam(value = "page", required = false, defaultValue = DefaultParams.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DefaultParams.DEFAULT_SIZE) Integer size,
            @PathVariable Integer id
    ) {
        PagedResponse<Book> books = this.bookService.getBooksByAuthor(id, page, size);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<PagedResponse<Book>> getBooksByCategory(
            @RequestParam(value = "page", required = false, defaultValue = DefaultParams.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DefaultParams.DEFAULT_SIZE) Integer size,
            @PathVariable Integer id
    ) {
        PagedResponse<Book> books = this.bookService.getBooksByCategory(
                id,
                page,
                size
        );
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookCreateRequest bookCreateRequest) {
        Book newBook = this.bookService.addBook(bookCreateRequest);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBook(
            @PathVariable Integer id,
            @RequestBody BookUpdateRequest bookUpdateRequest
    ) {
        this.bookService.updateBook(id, bookUpdateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id) {
        this.bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
