package com.example.exam.domain.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "students", indexes = {
        @Index(name = "first_name_index", columnList = "first_name"),
        @Index(name = "last_name_index", columnList = "last_name"),
        @Index(name = "full_name_index", columnList = "first_name, last_name"),
})
public class Student implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "first_name", length = 64, nullable = false)
    private String FirstName;

    @Column(name = "last_name", length = 64, nullable = false)
    private String LastName;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;

    @OneToMany(mappedBy = "student")
    private Set<SCard> sCards;
}