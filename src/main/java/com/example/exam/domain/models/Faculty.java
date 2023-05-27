package com.example.exam.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "faculties", indexes = {
        @Index(name = "name_index", columnList = "name", unique = true)
})
public class Faculty implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length = 64, nullable = false)
    private String name;

    @OneToMany(mappedBy = "faculty")
    private Set<Group> groups;
}


