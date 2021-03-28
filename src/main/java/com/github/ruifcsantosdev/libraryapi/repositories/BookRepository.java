package com.github.ruifcsantosdev.libraryapi.repositories;

import com.github.ruifcsantosdev.libraryapi.models.Book;
import com.github.ruifcsantosdev.libraryapi.models.author.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findByAuthor(int authorId, Pageable pageable);
}
