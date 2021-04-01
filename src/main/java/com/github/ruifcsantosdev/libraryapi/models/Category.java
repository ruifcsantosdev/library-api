package com.github.ruifcsantosdev.libraryapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String description;

    @Column
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.books = new ArrayList<>();
    }


}
