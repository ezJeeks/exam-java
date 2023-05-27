package com.example.exam.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Table(name = "departments", indexes = {
        @Index(name = "name_index", columnList = "name", unique = true)
})
public class Department implements Serializable {

    @Id
    @Column(name = "id")
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 64, nullable = false)
    @Setter
    private String name;

    @OneToMany(mappedBy = "department")
    @Setter
    private Set<Teacher> teachers;
}
