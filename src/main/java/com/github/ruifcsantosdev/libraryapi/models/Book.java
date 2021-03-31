package com.github.ruifcsantosdev.libraryapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ruifcsantosdev.libraryapi.models.author.Author;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String resume;

    private String isbn;

    private int numberOfPages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    public Book(String title, String resume, String isbn, int numberOfPages) {
        this.title = title;
        this.resume = resume;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }

    @JsonIgnore
    public Author getAuthor() {
        return author;
    }
}
