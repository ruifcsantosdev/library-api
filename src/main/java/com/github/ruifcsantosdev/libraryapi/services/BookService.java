package com.github.ruifcsantosdev.libraryapi.services;

import com.github.ruifcsantosdev.libraryapi.models.Book;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;

public interface BookService {
    PagedResponse<Book> getAllBooks(int page, int size);

    PagedResponse<Book> getBooksByAuthor(int authorId, int page, int size);

    PagedResponse<Book> getBooksByCategory(int categoryId, int page, int size);

    Book getBook(int bookId);

    Book addBook(BookCreateRequest bookCreateRequest);

    void updateBook(int bookId, BookUpdateRequest bookUpdateRequest);

    void deleteBook(int bookId);
}
