package com.github.ruifcsantosdev.libraryapi.repositories;

import com.github.ruifcsantosdev.libraryapi.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.author.id = :id")
    Page<Book> findBookByAuthor(@Param("id") Integer authorId, Pageable pageable);

    /*@Query(value = "SELECT book.* FROM Book book " +
            "INNER JOIN book_category bc on book.id = bc.book_id " +
            "WHERE bc.category_id = :categoryId "
            , nativeQuery = true)*/
    @Query(value = "SELECT b FROM Book b JOIN b.categories c WHERE c.id = :categoryId")
    Page<Book> findByCategories(@Param("categoryId") Integer categoryId, Pageable pageable);

    Optional<Book> findByTitleOrIsbn(String title, String isbn);
}
