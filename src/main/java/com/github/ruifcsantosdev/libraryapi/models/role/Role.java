package com.github.ruifcsantosdev.libraryapi.models.role;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }
}
