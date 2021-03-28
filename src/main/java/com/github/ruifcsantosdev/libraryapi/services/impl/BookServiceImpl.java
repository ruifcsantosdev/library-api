package com.github.ruifcsantosdev.libraryapi.services.impl;

import com.github.ruifcsantosdev.libraryapi.exception.BadRequestException;
import com.github.ruifcsantosdev.libraryapi.exception.ResourceNotFoundException;
import com.github.ruifcsantosdev.libraryapi.models.Book;
import com.github.ruifcsantosdev.libraryapi.models.Category;
import com.github.ruifcsantosdev.libraryapi.models.author.Author;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookCreateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.input.BookUpdateRequest;
import com.github.ruifcsantosdev.libraryapi.payload.output.PagedResponse;
import com.github.ruifcsantosdev.libraryapi.repositories.AuthorRepository;
import com.github.ruifcsantosdev.libraryapi.repositories.BookRepository;
import com.github.ruifcsantosdev.libraryapi.repositories.CategoryRepository;
import com.github.ruifcsantosdev.libraryapi.services.BookService;
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
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PagedResponse<Book> getAllBooks(int page, int size) {
        validatePagination(page, size);

        Pageable pageable = PageRequest.of(page, size);

        Page<Book> books = this.bookRepository.findAll(pageable);

        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        PagedResponse response = new PagedResponse(
                content,
                books.getNumber(),
                books.getSize(),
                books.getNumberOfElements(),
                books.getTotalPages(),
                books.isLast()
        );
        return response;
    }

    @Override
    public PagedResponse<Book> getBooksByAuthor(int authorId, int page, int size) {
        validatePagination(page, size);

        Author author = this.authorRepository
                .findById(authorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Authors doesn't exists!", "Author Id", authorId)
                );

        Pageable pageable = PageRequest.of(page, size);

        Page<Book> books = this.bookRepository.findByAuthor(author.getId(), pageable);

        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        PagedResponse response = new PagedResponse(
                content,
                books.getNumber(),
                books.getSize(),
                books.getNumberOfElements(),
                books.getTotalPages(),
                books.isLast()
        );

        return response;
    }

    // TODO
    @Override
    public PagedResponse<Book> getBooksByCategory(int categoryId, int page, int size) {
        return null;
    }

    @Override
    public Book getBook(int bookId) {
        return this.bookRepository.
                findById(bookId).
                orElseThrow(
                        () -> new ResourceNotFoundException("Book", "Id", bookId)
                );
    }

    @Override
    public Book addBook(BookCreateRequest bookCreateRequest) {

        Author author = this.authorRepository
                .findById(bookCreateRequest.getAuthorId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("The author doesn't exists!", "Author Id", bookCreateRequest.getAuthorId())
                );

        List<Category> categories = new ArrayList<>();

        for (Integer categoryId : bookCreateRequest.getCategories()){
            Optional<Category> category = categoryRepository.findById(categoryId);

            if(category.isPresent()) {
                categories.add(category.get());
            }
        }

        // TODO -> Implement AutoMapper
        Book book = new Book();
        book.setAuthor(author);
        book.setCategories(categories);
        book.setIsbn(bookCreateRequest.getIsbn());
        book.setResume(bookCreateRequest.getResume());
        book.setTitle(bookCreateRequest.getTitle());
        book.setNumberOfPages(bookCreateRequest.getNumberOfPages());

        Book newBook = this.bookRepository.save(book);

        // TODO -> Change Response
        return newBook;
    }

    // TODO
    @Override
    public void updateBook(int bookId, BookUpdateRequest bookUpdateRequest) {
        Book book = this.bookRepository
                .findById(bookId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Book doesn't exists!", "Id", bookId)
                );

    }

    @Override
    public void deleteBook(int bookId) {
        Book book = this.bookRepository
                .findById(bookId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Book doesn't exists!", "Id", bookId)
                );

        this.bookRepository.delete(book);
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
