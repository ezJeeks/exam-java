package com.example.exam.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Table(name = "authors", indexes = {
        @Index(name = "first_name_index", columnList = "first_name"),
        @Index(name = "last_name_index", columnList = "last_name"),
        @Index(name = "full_name_index", columnList = "first_name, last_name"),
})
public class Author implements Serializable {

    @Id
    @Column(name = "id")
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "first_name", length = 64, nullable = false)
    @Setter
    private String FirstName;

    @Column(name = "last_name", length = 64, nullable = false)
    @Setter
    private String LastName;

    @ManyToMany(mappedBy = "authors")
    @Setter
    private Set<Book> books;

    public Author(){

    }
}
