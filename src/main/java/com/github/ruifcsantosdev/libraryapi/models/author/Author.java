package com.github.ruifcsantosdev.libraryapi.models.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ruifcsantosdev.libraryapi.models.Book;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private AuthorGender gender;

    private String resume;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public Author(String firstName, String lastName, AuthorGender gender, String resume) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.resume = resume;
        this.books = new ArrayList<>();
    }
}
