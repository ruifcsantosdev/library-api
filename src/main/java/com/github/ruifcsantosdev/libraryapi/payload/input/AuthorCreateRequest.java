package com.github.ruifcsantosdev.libraryapi.payload.input;

import com.github.ruifcsantosdev.libraryapi.models.author.AuthorGender;
import lombok.Data;

@Data
public class AuthorCreateRequest {
    private String firstName;

    private String lastName;

    private String resume;

    private AuthorGender gender;
}
